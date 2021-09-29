package kg.easy.sellservice.services.impl;

import kg.easy.sellservice.dao.ProductRepo;
import kg.easy.sellservice.mappers.ProductMapper;
import kg.easy.sellservice.models.dtos.ProductDto;
import kg.easy.sellservice.models.entities.Product;
import kg.easy.sellservice.models.responses.ErrorResponse;
import kg.easy.sellservice.services.ProductService;
import kg.easy.sellservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    private final UserService userService;

    public ProductServiceImpl(ProductRepo productRepo, UserService userService) {
        this.productRepo = productRepo;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> save(String token, ProductDto productDto) {

        ResponseEntity<?> responseEntity = userService.verifyLogin(token);
        if (!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return responseEntity;
        }

        Product product = ProductMapper.INSTANCE.toProduct(productDto);

        if (Objects.isNull(productRepo.findByName(product.getName()))) {
            product = productRepo.save(product);
        } else {
            return new ResponseEntity<>(new ErrorResponse(
                    "Этот продукт существует в базе", null),
                    HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok(ProductMapper.INSTANCE.toProductDto(product));
    }
}
