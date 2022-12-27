package coffee.shop.queueservice.repository;

import coffee.shop.queueservice.dao.QueueDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<QueueDao, Long> {
    List<QueueDao> findByShopId(long shopId);
}
