package kg.easy.sellservice.models.dtos;

import kg.easy.sellservice.models.entities.Category;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {

    Long id;
    String name;
    Long barcode;
    Category category;
    boolean active;

}
