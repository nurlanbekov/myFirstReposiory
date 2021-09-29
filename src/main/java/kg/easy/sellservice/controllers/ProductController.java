package kg.easy.sellservice.controllers;

import kg.easy.sellservice.models.dtos.ProductDto;
import kg.easy.sellservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestHeader String token, @RequestBody ProductDto productDto) {
        return productService.save(token, productDto);
    }

}
