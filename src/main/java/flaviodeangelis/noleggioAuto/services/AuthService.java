package flaviodeangelis.noleggioAuto.services;

import flaviodeangelis.noleggioAuto.Enum.UserType;
import flaviodeangelis.noleggioAuto.entities.User;
import flaviodeangelis.noleggioAuto.exceptions.BadRequestException;
import flaviodeangelis.noleggioAuto.exceptions.UnauthorizedException;
import flaviodeangelis.noleggioAuto.payloads.NewUserDTO;
import flaviodeangelis.noleggioAuto.payloads.UserLogInDTO;
import flaviodeangelis.noleggioAuto.repositories.UserRepository;
import flaviodeangelis.noleggioAuto.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLogInDTO body) {
        User user = userService.findUtenteByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);

        } else {
            throw new UnauthorizedException("Invalid credentials");
        }
    }

    public User saveUser(NewUserDTO body) throws IOException {

        userRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " é giá stata utilizzata!");
        });

        User newUser = new User();
        newUser.setAvatar("https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());
        newUser.setUsername(body.username());
        newUser.setNome(body.nome());
        newUser.setCognome(body.cognome());
        newUser.setRuolo(UserType.USER);
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));

        return userRepository.save(newUser);
    }
}
