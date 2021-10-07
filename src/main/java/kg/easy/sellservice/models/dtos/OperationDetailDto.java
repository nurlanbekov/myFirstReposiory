package kg.easy.sellservice.models.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OperationDetailDto {

    Long id;
    ProductDto product;
    OperationDto operation;
    Integer quantity;
    Double amount;

}
