package fedeCapiz.spV2.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewDeviceDTO (
    @NotEmpty(message = "metti id dello user")
    int user_id,

    @NotEmpty(message = "Il nome è obbligatorio")
    String type,

    @NotEmpty(message = "lo status è obbligatorio")
    String status
    ){


}
