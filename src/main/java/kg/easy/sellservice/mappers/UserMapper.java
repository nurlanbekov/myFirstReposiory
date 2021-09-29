package kg.easy.sellservice.mappers;

import kg.easy.sellservice.models.entities.User;
import kg.easy.sellservice.models.dtos.LoginDto;
import kg.easy.sellservice.models.dtos.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserDto userDto);
    UserDto toUserDto(User user);

    LoginDto toLoginDto(User user);

}
