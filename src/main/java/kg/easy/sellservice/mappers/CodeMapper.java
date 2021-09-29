package kg.easy.sellservice.mappers;

import kg.easy.sellservice.models.dtos.CodeDto;
import kg.easy.sellservice.models.entities.Code;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CodeMapper {

    CodeMapper INSTANCE = Mappers.getMapper(CodeMapper.class);

    Code toCode(CodeDto codeDto);
    CodeDto toCodeDto(Code code);

}
