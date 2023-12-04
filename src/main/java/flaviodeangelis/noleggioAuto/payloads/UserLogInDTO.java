package flaviodeangelis.noleggioAuto.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record UserLogInDTO(@NotEmpty(message = "The email field cannot be empty")
                           @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email is not valid")
                           String email,
                           @NotEmpty(message = "The password field cannot be empty")
                           String password) {
}
