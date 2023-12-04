package flaviodeangelis.noleggioAuto.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewUserDTO(@NotEmpty(message = "The username field cannot be empty")
                         @Size(min = 3, max = 20, message = "The username must be between 3 and 20 characters")
                         String username,
                         @NotEmpty(message = "The name field cannot be empty")
                         @Size(min = 3, max = 20, message = "The name must be between 3 and 20 characters")
                         String nome,
                         @NotEmpty(message = "The surname field cannot be empty")
                         @Size(min = 3, max = 20, message = "The surname must be between 3 and 20 characters")
                         String cognome,
                         @NotEmpty(message = "The email field cannot be empty")
                         @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email is invalid")
                         String email,
                         @NotEmpty(message = "The password field cannot be empty")
                         @Size(min = 8, max = 20, message = "The password must contain between 8 and 20 characters")
                         String password) {
}
