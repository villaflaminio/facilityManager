package it.bruffa.facilitymanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationFilter {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "1")
    private Long structureId;

    @Schema(description = "Date of arrival", example = "2021-05-05")
    private java.time.LocalDateTime arrival;


    @Schema(description = "Date of departure", example = "2021-06-05")
    private java.time.LocalDateTime departure;

    @Schema(description = "Check in time", example = "10:00")
    private LocalTime checkIn;
    @Schema(description = "Check out time", example = "10:00")
    private LocalTime checkOut;

    @Schema(example = "1")
    private Integer guests;

    @Schema(example = "Mario")
    private String guestName;

    @Schema(example = "Rossi")
    private String guestSurname;

}
