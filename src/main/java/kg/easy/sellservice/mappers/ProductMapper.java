package kg.easy.sellservice.mappers;

import kg.easy.sellservice.models.dtos.ProductDto;
import kg.easy.sellservice.models.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDto productDto);
    ProductDto toProductDto(Product product);

}
