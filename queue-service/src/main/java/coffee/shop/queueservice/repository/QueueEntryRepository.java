package coffee.shop.queueservice.repository;

import coffee.shop.queueservice.dao.QueueEntryDao;
import coffee.shop.queueservice.dao.QueueEntryIdClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueEntryRepository extends JpaRepository<QueueEntryDao, QueueEntryIdClass> {
    List<QueueEntryDao> findByReservationId(long reservationId);

    long deleteByReservationId(long reservationId);
}
