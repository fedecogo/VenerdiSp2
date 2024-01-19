package fedeCapiz.spV2.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;


public record NewUserDTO (
    @NotEmpty(message = "Il nome è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome deve avere minimo 3 caratteri, massimo 30")
    String name,
    @NotEmpty(message = "Il cognome è obbligatorio")
    String surname,
    @NotEmpty(message = "L'email è obbligatoria")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
    String email,
    @NotNull(message = "Lo username è obbligatoria")
    String userName
            ) {
}
