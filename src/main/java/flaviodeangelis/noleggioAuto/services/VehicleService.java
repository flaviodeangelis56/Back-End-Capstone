package flaviodeangelis.noleggioAuto.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import flaviodeangelis.noleggioAuto.Enum.VehicleAvailability;
import flaviodeangelis.noleggioAuto.entities.Vehicles;
import flaviodeangelis.noleggioAuto.exceptions.NotFoundException;
import flaviodeangelis.noleggioAuto.payloads.NewVehicleDTO;
import flaviodeangelis.noleggioAuto.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private Cloudinary cloudinary;

    /*public Page<Vehicles> findAll(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 20;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return vehicleRepository.findAll(pageable);
    }*/
    public List<Vehicles> findAll() {


        return vehicleRepository.findAll();
    }

    public Vehicles findById(int id) throws NotFoundException {
        return vehicleRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public List<Vehicles> findByMarca(String marca) {
        return vehicleRepository.findByMarcaIgnoreCaseContaining(marca);
    }

    public void findByIdAndDelate(int id) throws NotFoundException {
        Vehicles found = vehicleRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        vehicleRepository.delete(found);
    }

    public Vehicles saveVehicles(NewVehicleDTO body) throws IOException {
        Vehicles newVehicle = new Vehicles();
        newVehicle.setImgVehicles(body.imgUrl());
        newVehicle.setName(body.nome());
        newVehicle.setMarca(body.marca());
        newVehicle.setAltezza(body.altezza());
        newVehicle.setCilindrata(body.cilindrata());
        newVehicle.setPasso(body.passo());
        newVehicle.setMassa(body.massa());
        newVehicle.setLarghezza(body.larghezza());
        newVehicle.setLunghezza(body.lunghezza());
        newVehicle.setConsumiMisto(body.ConsumiMisto());
        newVehicle.setAnnoProduzione(body.annoProduzione());
        newVehicle.setMaxPower(body.maxPower());
        newVehicle.setPrezzo(body.prezzo());
        newVehicle.setDescrizione(body.descrizione());
        newVehicle.setVehicleAvailability(VehicleAvailability.DISPONIBILE);
        return vehicleRepository.save(newVehicle);
    }

    public Vehicles uploadImg(MultipartFile file, int id, Vehicles body) throws IOException {
        Vehicles found = vehicleRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        String imgUrl = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setImgVehicles(imgUrl);
        found.setName(body.getName());
        found.setMarca(body.getMarca());
        found.setAltezza(body.getAltezza());
        found.setCilindrata(body.getCilindrata());
        found.setPasso(body.getPasso());
        found.setMassa(body.getMassa());
        found.setLarghezza(body.getLarghezza());
        found.setLunghezza(body.getLunghezza());
        found.setConsumiMisto(body.getConsumiMisto());
        found.setAnnoProduzione(body.getAnnoProduzione());
        found.setMaxPower(body.getMaxPower());
        found.setPrezzo(body.getPrezzo());
        found.setDescrizione(body.getDescrizione());
        found.setVehicleAvailability(VehicleAvailability.DISPONIBILE);
        return vehicleRepository.save(found);
    }
}
