package flaviodeangelis.noleggioAuto.payloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewReservationsDTO(@NotNull(message = "The loan start date field cannot be empty")
                                 LocalDate dataInizioPrestito,
                                 @NotNull(message = "The user field cannot be empty")
                                 int user,
                                 @NotNull(message = "The vehicle field cannot be empty")
                                 int vehicles) {
}
