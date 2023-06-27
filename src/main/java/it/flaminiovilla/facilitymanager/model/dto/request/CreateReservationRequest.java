package it.flaminiovilla.facilitymanager.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationRequest {

    @Schema(example = "1")
    @NotNull
    private Long structureId;

    @Schema(description = "Date of arrival", example = "2023-01-01")
    @NotNull
    private LocalDate arrival;

    @NotNull
    @Schema(description = "Date of departure", example = "2023-02-01")
    private LocalDate departure;

    @NotNull
    @Schema(description = "Check in time", example = "10:00")
    private LocalTime checkIn;

    @NotNull
    @Schema(description = "Check out time", example = "10:00")
    private LocalTime checkOut;

    @NotNull
    @Schema(example = "1")
    private Integer guests;

    @NotNull
    @Schema(example = "Mario")
    private String guestName;

    @NotNull
    @Schema(example = "Rossi")
    private String guestSurname;

}
