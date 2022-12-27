package coffee.shop.shopservice.mapper;

import coffee.shop.shopservice.dao.ShopDao;
import coffee.shop.shopservice.dto.Shop;
import org.mapstruct.Mapper;

/**
 * Map Shop Dao to Dto
 */
@Mapper(componentModel = "spring")
public abstract class ShopMapper {

    public abstract Shop mapShop(ShopDao shopDao);
}
