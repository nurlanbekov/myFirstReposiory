package kg.easy.sellservice.controllers;

import kg.easy.sellservice.models.dtos.CategoryDto;
import kg.easy.sellservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestHeader String token,@RequestBody CategoryDto categoryDto){
        return categoryService.create(token, categoryDto);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestHeader String token, @RequestBody CategoryDto categoryDto){
        return categoryService.editCategory(categoryDto);
    }





}
