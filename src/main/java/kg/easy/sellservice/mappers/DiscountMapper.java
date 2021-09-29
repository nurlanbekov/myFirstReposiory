package kg.easy.sellservice.mappers;

import kg.easy.sellservice.models.dtos.DiscountDto;
import kg.easy.sellservice.models.entities.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DiscountMapper {

    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);

    Discount toDiscount(DiscountDto discountDto);
    DiscountDto toDiscountDto(Discount discount);

}
