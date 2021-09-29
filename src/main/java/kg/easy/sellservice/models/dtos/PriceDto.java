package kg.easy.sellservice.models.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceDto {

    Long id;
    Double price;
    Date startDate;
    Date endDate;
    ProductDto product;

}
