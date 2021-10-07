package kg.easy.sellservice.models.dtos;

import kg.easy.sellservice.models.entities.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationDetailDto {

    Long id;
    Product product;
    OperationDto operation;
    Integer amount;
    Double price;

}
