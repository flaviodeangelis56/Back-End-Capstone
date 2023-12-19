package flaviodeangelis.noleggioAuto.services;

import flaviodeangelis.noleggioAuto.Enum.VehicleAvailability;
import flaviodeangelis.noleggioAuto.entities.Reservations;
import flaviodeangelis.noleggioAuto.entities.User;
import flaviodeangelis.noleggioAuto.entities.Vehicles;
import flaviodeangelis.noleggioAuto.exceptions.NotFoundException;
import flaviodeangelis.noleggioAuto.repositories.ReservationRepository;
import flaviodeangelis.noleggioAuto.repositories.UserRepository;
import flaviodeangelis.noleggioAuto.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    public Page<Reservations> findAll(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return reservationRepository.findAll(pageable);
    }

    public Reservations findById(int id) throws NotFoundException {
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelate(int id) throws NotFoundException {
        Reservations found = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        reservationRepository.delete(found);
    }

    public Reservations saveReservation(int userId, int vehicleId) throws IOException {
        User userFound = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
        Vehicles vehicleFound = vehicleRepository.findById(vehicleId).orElseThrow(() -> new NotFoundException(vehicleId));
        Reservations newReserv = new Reservations();
        newReserv.setDataInizioPrestito(LocalDate.now());
        newReserv.setDataRestituzionePrevista(newReserv.getDataInizioPrestito().plusMonths(1));
        newReserv.setVehicles(vehicleFound);
        newReserv.setUser(userFound);
        vehicleFound.setVehicleAvailability(VehicleAvailability.OCCUPATO);
        vehicleRepository.save(vehicleFound);
        return reservationRepository.save(newReserv);
    }

    public void restituisciVeicolo(int id) {
        Reservations found = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        Vehicles test = found.getVehicles();
        found.setDataRestituzione(LocalDate.now());
        test.setVehicleAvailability(VehicleAvailability.DISPONIBILE);
        vehicleRepository.save(test);
        reservationRepository.save(found);
    }

    public List<Reservations> findByUser(int id) {
        return reservationRepository.findByUserId(id);
    }
}
