package coffee.shop.shopservice.service;

import coffee.shop.shopservice.dto.Shop;
import coffee.shop.shopservice.exception.NotFoundException;

public interface ShopService {
    Shop getShopById(long shopId) throws NotFoundException;
}
