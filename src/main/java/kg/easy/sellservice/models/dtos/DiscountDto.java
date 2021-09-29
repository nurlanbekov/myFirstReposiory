package kg.easy.sellservice.models.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DiscountDto {

    Long id;
    Double discount;
    Date startDate;
    Date endDate;
    ProductDto product;


}
