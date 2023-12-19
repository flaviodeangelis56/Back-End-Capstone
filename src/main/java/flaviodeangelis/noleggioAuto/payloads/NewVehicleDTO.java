package flaviodeangelis.noleggioAuto.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewVehicleDTO(@NotEmpty(message = "The name field cannot be empty")
                            @Size(min = 3, max = 20, message = "The name must be between 3 and 20 characters")
                            String nome,
                            @NotEmpty(message = "The brand field cannot be empty")
                            @Size(min = 2, max = 20, message = "The brand must be between 2 and 20 characters")
                            String marca,
                            @NotEmpty(message = "The image field cannot be empty")
                            String imgUrl,
                            @NotNull(message = "The production year field cannot be empty")
                            int annoProduzione,
                            @NotNull(message = "the displacement field cannot be empty")
                            int cilindrata,
                            @NotNull(message = "the maximum power field cannot be empty")
                            int maxPower,
                            @NotNull(message = "the length field cannot be empty")
                            double lunghezza,
                            @NotNull(message = "the width field cannot be empty")
                            double larghezza,
                            @NotNull(message = "the height field cannot be empty")
                            double altezza,
                            @NotNull(message = "the step field cannot be empty")
                            double passo,
                            @NotNull(message = "the mass field cannot be empty")
                            int massa,
                            @NotNull(message = "the price field cannot be empty")
                            int prezzo,
                            @NotEmpty(message = "the consumption field in the mixed cannot be empty")
                            String ConsumiMisto,
                            @NotEmpty(message = "the consumption field in the mixed cannot be empty")
                            String descrizione) {
}
