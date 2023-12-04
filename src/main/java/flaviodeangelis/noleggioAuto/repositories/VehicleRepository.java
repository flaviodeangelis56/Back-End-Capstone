package flaviodeangelis.noleggioAuto.repositories;

import flaviodeangelis.noleggioAuto.entities.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicles, Integer> {
}
