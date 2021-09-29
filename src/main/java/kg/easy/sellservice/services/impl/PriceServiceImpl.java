package kg.easy.sellservice.services.impl;

import kg.easy.sellservice.dao.PriceRepo;
import kg.easy.sellservice.mappers.PriceMapper;
import kg.easy.sellservice.mappers.ProductMapper;
import kg.easy.sellservice.models.dtos.PriceDto;
import kg.easy.sellservice.models.entities.Price;
import kg.easy.sellservice.services.PriceService;
import kg.easy.sellservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepo priceRepo;

    private final UserService userService;

    public PriceServiceImpl(PriceRepo priceRepo, UserService userService) {
        this.priceRepo = priceRepo;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> save(String token, PriceDto priceDto) {

        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }

        List<Price> priceList = priceRepo.findAllByProduct(
                ProductMapper.INSTANCE.toProduct(priceDto.getProduct()));
        if (Objects.nonNull(priceList) && !priceList.isEmpty()) {
            priceList.stream().filter(x ->
                    x.getStartDate().before(priceDto.getStartDate())
                            || x.getStartDate().after(priceDto.getStartDate())
                       && x.getEndDate().before(priceDto.getEndDate())
                            || x.getEndDate().after(priceDto.getEndDate())
            ).forEach(x -> {
                x.setEndDate(new Date());
                priceRepo.save(x);
            });
        }
        Price price = PriceMapper.INSTANCE.toPrice(priceDto);
        price = priceRepo.save(price);

        return ResponseEntity.ok(PriceMapper.INSTANCE.toPriceDto(price));
    }
}
