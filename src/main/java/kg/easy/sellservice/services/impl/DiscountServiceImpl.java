package kg.easy.sellservice.services.impl;

import kg.easy.sellservice.dao.DiscountRepo;
import kg.easy.sellservice.mappers.DiscountMapper;
import kg.easy.sellservice.mappers.ProductMapper;
import kg.easy.sellservice.models.dtos.DiscountDto;
import kg.easy.sellservice.models.entities.Discount;
import kg.easy.sellservice.services.DiscountService;
import kg.easy.sellservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepo discountRepo;

    private final UserService userService;

    public DiscountServiceImpl(DiscountRepo discountRepo, UserService userService) {
        this.discountRepo = discountRepo;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> save(String token, DiscountDto discountDto) {
        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }

        List<Discount> discountList = discountRepo.findAllByProduct(
                ProductMapper.INSTANCE.toProduct(discountDto.getProduct()));
        if (Objects.nonNull(discountList) && !discountList.isEmpty()){
            discountList.stream().filter(x->
                    x.getStartDate().before(discountDto.getStartDate())
            || x.getStartDate().after(discountDto.getStartDate())
            && x.getEndDate().before(discountDto.getEndDate())
            || x.getEndDate().after(discountDto.getEndDate())
            ).forEach(x -> {
                x.setEndDate(new Date());
                discountRepo.save(x);
            });
        }


    }
}
