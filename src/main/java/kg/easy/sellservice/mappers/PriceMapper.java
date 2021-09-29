package kg.easy.sellservice.mappers;

import kg.easy.sellservice.models.dtos.PriceDto;
import kg.easy.sellservice.models.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    Price toPrice(PriceDto priceDto);
    PriceDto toPriceDto(Price price);

}
