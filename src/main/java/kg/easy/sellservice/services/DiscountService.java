package kg.easy.sellservice.services;

import kg.easy.sellservice.models.dtos.DiscountDto;
import org.springframework.http.ResponseEntity;

public interface DiscountService {
    ResponseEntity<?> save(String token, DiscountDto discountDto);
}
