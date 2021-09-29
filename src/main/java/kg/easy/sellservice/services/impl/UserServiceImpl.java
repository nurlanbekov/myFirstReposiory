package kg.easy.sellservice.services.impl;

import io.jsonwebtoken.*;
import kg.easy.sellservice.mappers.UserMapper;
import kg.easy.sellservice.models.dtos.LoginDto;
import kg.easy.sellservice.models.dtos.UserDto;
import kg.easy.sellservice.models.entities.Code;
import kg.easy.sellservice.models.entities.Request;
import kg.easy.sellservice.models.entities.User;
import kg.easy.sellservice.models.enums.CodeStatus;
import kg.easy.sellservice.models.responses.ErrorResponse;
import kg.easy.sellservice.services.CodeService;
import kg.easy.sellservice.services.EmailService;
import kg.easy.sellservice.services.RequestService;
import kg.easy.sellservice.services.UserService;
import kg.easy.sellservice.dao.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CodeService codeService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RequestService requestService;

    @Value("${jwtSecret}")
    private String secretKey;

    @Override
    public ResponseEntity<?> saveUser(UserDto userDto) {

        User user = UserMapper.INSTANCE.toUser(userDto);

        if (Objects.isNull(userRepo.findByLogin(user.getLogin()))) {
            userRepo.save(user);
        } else {
            return new ResponseEntity<>(new ErrorResponse(
                    "Этот логин занят.",
                    "Логин должен быть уникальным!"),
                    HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(UserMapper.INSTANCE.toUserDto(user));
    }

    @Override
    public ResponseEntity<?> sendCode(String login) {

        User user = userRepo.findByLogin(login);

        if (Objects.isNull(user)) {

            return ResponseEntity.ok(new ErrorResponse("Проверьте корректность логина",
                    "Либо обратитесь к администрацию"));

        }

        if (Objects.nonNull(user.getBlockDate())) {
            if (new Date().before(user.getBlockDate())) {

                SimpleDateFormat toBlockDate = new SimpleDateFormat("HH:mm:ss");

                return new ResponseEntity<>(new ErrorResponse(
                        "Вы заблокированы за превышение лимита попытки входа.",
                        "Запросить код повторно можно после " +
                                toBlockDate
                                        .format(user.getBlockDate())
                ),
                        HttpStatus.LOCKED
                );
            }
        }


        Code lastCode = codeService.findUserCode(user);
        if (Objects.nonNull(lastCode)) {
            lastCode.setStatus(CodeStatus.CANCELLED);
            codeService.saveCode(lastCode);
        }

        int randomCode = codeService.randomCode();

        String hashingCode = BCrypt.hashpw(String.valueOf(randomCode), BCrypt.gensalt());

        Calendar endOfCodeAction = Calendar.getInstance();
        endOfCodeAction.add(Calendar.MINUTE, 3);

        Code code = new Code();
        code.setCode(hashingCode);
        code.setEndDate(endOfCodeAction.getTime());
        code.setStatus(CodeStatus.NEW);
        code.setUser(user);
        codeService.saveCode(code);

        emailService.sendMessage(
                user.getEmail(),
                "Код подтверждения",
                "Ваш код подтверждения:" + randomCode);

        return ResponseEntity.ok(new ErrorResponse(
                "Код подтверждения отправлен.",
                "Проверьте почту!"
        ));
    }

    @Override
    public ResponseEntity<?> getToken(LoginDto login) {

        User user = userRepo.findByLogin(login.getLogin());

        if (Objects.isNull(user)) {
            return new ResponseEntity<>(new ErrorResponse(
                    "Неправильный логин или вы не зарегистрированы.",
                    "Либо обратитесь к администрацию"
            ),
                    HttpStatus.UNAUTHORIZED
            );
        }

        if (Objects.nonNull(user.getBlockDate())) {
            if (new Date().before(user.getBlockDate())) {

                SimpleDateFormat toBlockDate = new SimpleDateFormat("HH:mm:ss");

                return new ResponseEntity<>(new ErrorResponse(
                        "Вы заблокированы за превышение лимита попытки входа.",
                        "Запросить код повторно можно после " +
                                toBlockDate
                                        .format(user.getBlockDate())
                ),
                        HttpStatus.LOCKED
                );
            }
        }

        Code checkUserCode = codeService.findUserCode(user);

        if (new Date().after(checkUserCode.getEndDate())) {
            return new ResponseEntity<>(new ErrorResponse(
                    "Время кода подтверждения истек!",
                    "Получите код подтверждения повторно!"
            ),
                    HttpStatus.CONFLICT
            );
        }

        Request request = new Request();
        request.setCode(checkUserCode);

        if (!BCrypt.checkpw(login.getCode(), checkUserCode.getCode())) {

            request.setSuccess(false);
            requestService.saveRequest(request);

            if (requestService.countFailedAttempts(checkUserCode) >= 3) {

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, 1);

                user.setBlockDate(calendar.getTime());
                userRepo.save(user);

                checkUserCode.setStatus(CodeStatus.FAILED);
                codeService.saveCode(checkUserCode);

            }

            return new ResponseEntity<>(new ErrorResponse(
                    "Вход не выполнен.",
                    "Неправильный код"
            ),
                    HttpStatus.UNAUTHORIZED
            );

        }

        request.setSuccess(true);
        requestService.saveRequest(request);

        Calendar tokensTimeLive = Calendar.getInstance();
        tokensTimeLive.add(Calendar.MINUTE, 5);

        String token = Jwts.builder()
                .claim("login", login.getLogin())
                .setExpiration(tokensTimeLive.getTime())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        checkUserCode.setStatus(CodeStatus.APPROVED);
        codeService.saveCode(checkUserCode);


        return ResponseEntity.ok(token);
    }

    @Override
    public ResponseEntity<?> verifyLogin(String token) {

        try {

            Jws<Claims> jwt = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return ResponseEntity.ok(jwt.getBody().get("login"));

        } catch (ExpiredJwtException jwtException) {
            return new ResponseEntity<>("Время действия токена истек.", HttpStatus.REQUEST_TIMEOUT);

        } catch (UnsupportedJwtException jwtException) {
            return new ResponseEntity<>("Неподерживаемый токен.", HttpStatus.CONFLICT);

        } catch (MalformedJwtException jwtException) {
            return new ResponseEntity<>("Некорректный токен.", HttpStatus.CONFLICT);

        } catch (Exception exception) {
            return new ResponseEntity<>("Неавторизованный токен", HttpStatus.UNAUTHORIZED);
        }
    }

}
