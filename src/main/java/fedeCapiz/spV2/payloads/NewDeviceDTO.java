package fedeCapiz.spV2.payloads;

import jakarta.validation.constraints.NotEmpty;

public record NewDeviceDTO (
    @NotEmpty(message = "Il nome è obbligatorio")
    String type,

    @NotEmpty(message = "lo status è obbligatorio")
    String status
    ){

}
