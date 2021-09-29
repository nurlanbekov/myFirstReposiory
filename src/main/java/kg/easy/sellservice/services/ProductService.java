package kg.easy.sellservice.services;

import kg.easy.sellservice.models.dtos.ProductDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    ResponseEntity<?> save(String token, ProductDto productDto);
}
