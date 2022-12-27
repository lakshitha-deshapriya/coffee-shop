package coffee.shop.shopservice.controller;

import coffee.shop.shopservice.dto.Shop;
import coffee.shop.shopservice.exception.NotFoundException;
import coffee.shop.shopservice.exception.NotImplementedException;
import coffee.shop.shopservice.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle shop functionalities
 */
@RestController
@RequestMapping("shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping(value = "/{shopId}")
    public ResponseEntity<Shop> getShop(@PathVariable long shopId) throws NotFoundException {
        return ResponseEntity.ok(shopService.getShopById(shopId));
    }

    @PostMapping(value = "/")
    public ResponseEntity<Shop> createShop() throws NotImplementedException {
        throw new NotImplementedException("Endpoint not yet Implemented");
    }

    @PutMapping( value = "/{shopId}")
    public ResponseEntity<Shop> updateShop(@PathVariable long shopId) throws NotImplementedException {
        throw new NotImplementedException("Endpoint not yet Implemented");
    }

    @DeleteMapping(value = "/{shopId}")
    public ResponseEntity<Void> deleteShop(@PathVariable int shopId) throws NotImplementedException {
        throw new NotImplementedException("Endpoint not yet Implemented");
    }
}
