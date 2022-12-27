package coffee.shop.reservationservice.repository;

import coffee.shop.reservationservice.dao.ReservationDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationDao, Long> {
}
