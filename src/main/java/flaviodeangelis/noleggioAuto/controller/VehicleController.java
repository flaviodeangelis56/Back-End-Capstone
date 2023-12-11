package flaviodeangelis.noleggioAuto.controller;

import flaviodeangelis.noleggioAuto.entities.Vehicles;
import flaviodeangelis.noleggioAuto.exceptions.BadRequestException;
import flaviodeangelis.noleggioAuto.payloads.NewVehicleDTO;
import flaviodeangelis.noleggioAuto.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Vehicles saveUser(@RequestBody @Validated NewVehicleDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return vehicleService.saveVehicles(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("")
    public Page<Vehicles> getUser(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String orderBy) {
        return vehicleService.findAll(page, size > 20 ? 5 : size, orderBy);
    }

    @GetMapping("/{id}")
    public Vehicles findById(@PathVariable int id) {
        return vehicleService.findById(id);
    }

    @GetMapping("/byMarca/{marca}")
    public List<Vehicles> findByMarca(@PathVariable String marca) {
        return vehicleService.findByMarca(marca);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        vehicleService.findByIdAndDelate(id);
    }

    @PutMapping("/update/img/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Vehicles uploadImg(@RequestParam("avatar") MultipartFile file, @PathVariable int id) throws IOException {
        Vehicles found = vehicleService.findById(id);
        return vehicleService.uploadImg(file, id, found);
    }
}

