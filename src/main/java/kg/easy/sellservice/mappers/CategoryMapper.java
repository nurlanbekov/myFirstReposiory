package kg.easy.sellservice.mappers;

import kg.easy.sellservice.models.dtos.CategoryDto;
import kg.easy.sellservice.models.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryDto categoryDto);
    CategoryDto toCategoryDto(Category category);

}
