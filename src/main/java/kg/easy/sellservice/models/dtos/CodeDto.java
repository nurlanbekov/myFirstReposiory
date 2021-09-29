package kg.easy.sellservice.models.dtos;

import kg.easy.sellservice.models.enums.CodeStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CodeDto {

    Long id;
    Integer code;
    Date startDate;
    Date endDate;
    CodeStatus status;
    UserDto user;

}
