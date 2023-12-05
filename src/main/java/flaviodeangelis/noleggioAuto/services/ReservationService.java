package flaviodeangelis.noleggioAuto.services;

import flaviodeangelis.noleggioAuto.entities.Reservations;
import flaviodeangelis.noleggioAuto.entities.User;
import flaviodeangelis.noleggioAuto.entities.Vehicles;
import flaviodeangelis.noleggioAuto.exceptions.NotFoundException;
import flaviodeangelis.noleggioAuto.payloads.NewReservationsDTO;
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

    public Reservations saveReservation(NewReservationsDTO body, int userId, int vehicleId) throws IOException {
        User userFound = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
        Vehicles vehicleFound = vehicleRepository.findById(vehicleId).orElseThrow(() -> new NotFoundException(vehicleId));
        Reservations newReserv = new Reservations();
        newReserv.setDataInizioPrestito(body.dataInizioPrestito());
        newReserv.setDataRestituzionePrevista(body.dataInizioPrestito().plusMonths(1));
        newReserv.setVehicles(vehicleFound);
        newReserv.setUser(userFound);
        return reservationRepository.save(newReserv);
    }
}
