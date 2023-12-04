package flaviodeangelis.noleggioAuto.repositories;

import flaviodeangelis.noleggioAuto.entities.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservations, Integer> {
}
