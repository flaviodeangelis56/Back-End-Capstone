package flaviodeangelis.noleggioAuto.controller;

import flaviodeangelis.noleggioAuto.entities.User;
import flaviodeangelis.noleggioAuto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getUser(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String orderBy) {
        return userService.findAll(page, size > 20 ? 5 : size, orderBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable int id) {
        return userService.findUtenteById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        userService.findByIdAndDelate(id);
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findByEmail(@PathVariable String email) {
        return userService.findUtenteByEmail(email);
    }
}
