package flaviodeangelis.noleggioAuto.payloads;

import jakarta.validation.constraints.NotEmpty;

public record UserLogInSuccessDTO(@NotEmpty
                                  String accessToken) {
}
