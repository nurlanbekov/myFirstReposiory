package kg.easy.sellservice.models.dtos;

import kg.easy.sellservice.models.entities.Code;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestDto {

    Long id;
    boolean success;
    Date addDate;
    CodeDto code;

}
