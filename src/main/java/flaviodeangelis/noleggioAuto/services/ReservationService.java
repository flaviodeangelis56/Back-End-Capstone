package flaviodeangelis.noleggioAuto.services;

import flaviodeangelis.noleggioAuto.Enum.VehicleAvailability;
import flaviodeangelis.noleggioAuto.entities.Reservations;
import flaviodeangelis.noleggioAuto.entities.Vehicles;
import flaviodeangelis.noleggioAuto.exceptions.NotFoundException;
import flaviodeangelis.noleggioAuto.payloads.NewVehicleDTO;
import flaviodeangelis.noleggioAuto.repositories.ReservationRepository;
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

    public Vehicles saveVehicles(NewVehicleDTO body) throws IOException {

        Vehicles newVehicle = new Vehicles();
        newVehicle.setImgVehicles("...");
        newVehicle.setName(body.nome());
        newVehicle.setAltezza(body.altezza());
        newVehicle.setCilindrata(body.cilindrata());
        newVehicle.setPasso(body.passo());
        newVehicle.setMassa(body.massa());
        newVehicle.setLarghezza(body.larghezza());
        newVehicle.setLunghezza(body.lunghezza());
        newVehicle.setConsumiMisto(body.ConsumiMisto());
        newVehicle.setAnnoProduzione(body.annoProduzione());
        newVehicle.setMaxPower(body.maxPower());
        newVehicle.setVehicleAvailability(VehicleAvailability.DISPONIBILE);
        return vehicleRepository.save(newVehicle);
    }
}
