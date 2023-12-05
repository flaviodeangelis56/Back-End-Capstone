package flaviodeangelis.noleggioAuto.controller;

import flaviodeangelis.noleggioAuto.entities.User;
import flaviodeangelis.noleggioAuto.exceptions.BadRequestException;
import flaviodeangelis.noleggioAuto.payloads.NewUserDTO;
import flaviodeangelis.noleggioAuto.payloads.UserLogInDTO;
import flaviodeangelis.noleggioAuto.payloads.UserLogInSuccessDTO;
import flaviodeangelis.noleggioAuto.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.saveUser(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PostMapping("/login")
    public UserLogInSuccessDTO login(@RequestBody UserLogInDTO body) {
        return new UserLogInSuccessDTO(authService.authenticateUser(body));
    }
}
