package flaviodeangelis.noleggioAuto.controller;

import flaviodeangelis.noleggioAuto.entities.Reservations;
import flaviodeangelis.noleggioAuto.exceptions.BadRequestException;
import flaviodeangelis.noleggioAuto.payloads.NewReservationsDTO;
import flaviodeangelis.noleggioAuto.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;


    @PostMapping("/{userId}/{vehicleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservations saveUser(@RequestBody @Validated NewReservationsDTO body, BindingResult validation, @PathVariable int userId, @PathVariable int vehicleId) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return reservationService.saveReservation(body, userId, vehicleId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Reservations> getUser(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy) {
        return reservationService.findAll(page, size > 20 ? 5 : size, orderBy);
    }

    @GetMapping("/{id}")
    public Reservations findById(@PathVariable int id) {
        return reservationService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        reservationService.findByIdAndDelate(id);
    }
}
