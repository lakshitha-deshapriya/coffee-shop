package coffee.shop.shopservice.service.impl;

import coffee.shop.shopservice.dao.ShopDao;
import coffee.shop.shopservice.dto.Shop;
import coffee.shop.shopservice.exception.NotFoundException;
import coffee.shop.shopservice.mapper.ShopMapper;
import coffee.shop.shopservice.repository.ShopRepository;
import coffee.shop.shopservice.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handle Shop functionalities
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopMapper shopMapper;

    /**
     * Get shop using shop Id
     * @param shopId Id of the shop
     * @return Shop Dto
     * @throws NotFoundException when the shop is not found
     */
    @Override
    public Shop getShopById(long shopId) throws NotFoundException {
        ShopDao shopDao = shopRepository.findById(shopId).orElseThrow(() -> new NotFoundException("Shop by shop Id not found"));
        return shopMapper.mapShop(shopDao);
    }
}
