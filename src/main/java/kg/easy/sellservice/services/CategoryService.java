package kg.easy.sellservice.services;

import kg.easy.sellservice.models.dtos.CategoryDto;
import org.springframework.http.ResponseEntity;

public interface CategoryService {

    ResponseEntity<?> create(String token, CategoryDto categoryDto);

    ResponseEntity<?> editCategory(CategoryDto categoryDto);
}
