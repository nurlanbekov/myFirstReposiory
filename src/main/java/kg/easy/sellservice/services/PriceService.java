package kg.easy.sellservice.services;

import kg.easy.sellservice.models.dtos.PriceDto;
import org.springframework.http.ResponseEntity;

public interface PriceService {
    ResponseEntity<?> save(String token, PriceDto priceDto);
}
