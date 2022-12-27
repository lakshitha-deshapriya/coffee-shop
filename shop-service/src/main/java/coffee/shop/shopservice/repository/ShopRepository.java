package coffee.shop.shopservice.repository;

import coffee.shop.shopservice.dao.ShopDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<ShopDao, Long> {
}
