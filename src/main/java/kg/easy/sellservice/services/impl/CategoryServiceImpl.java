package kg.easy.sellservice.services.impl;

import kg.easy.sellservice.models.dtos.CategoryDto;
import kg.easy.sellservice.models.responses.ErrorResponse;
import kg.easy.sellservice.dao.CategoryRepo;
import kg.easy.sellservice.mappers.CategoryMapper;
import kg.easy.sellservice.models.entities.Category;
import kg.easy.sellservice.services.CategoryService;
import kg.easy.sellservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    private final UserService userService;

    public CategoryServiceImpl(CategoryRepo categoryRepo, UserService userService) {
        this.categoryRepo = categoryRepo;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> create(String token, CategoryDto categoryDto) {

        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)){
            return responseEntity;
        }

        Category category = CategoryMapper.INSTANCE.toCategory(categoryDto);

        if (Objects.isNull(categoryRepo.findByName(category.getName()))){
            category = categoryRepo.save(category);
        } else {
            return new ResponseEntity<>( new ErrorResponse(
                    "Категория с таким именем существует!", null),
                    HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(CategoryMapper.INSTANCE.toCategoryDto(category));

    }

    @Override
    public ResponseEntity<?> editCategory(CategoryDto categoryDto) {
        return null;
    }


}
