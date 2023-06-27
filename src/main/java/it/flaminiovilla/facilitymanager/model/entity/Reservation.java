package it.flaminiovilla.facilitymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "structure_id")
    @JsonBackReference(value = "structure-reservation")
    private Structure structure;

    @NotNull
    @Column(name = "arrival")
    private LocalDate arrival;

    @NotNull
    @Column(name = "departure")
    private LocalDate departure;

    @NotNull
    @Column(name = "check_in")
    private LocalTime checkIn;

    @NotNull
    @Column(name = "check_out")
    private LocalTime checkOut;

    @NotNull
    @Column(name = "guests")
    private Integer guests;

    @NotNull
    @Column(name = "guest_name")
    private String guestName;

    @NotNull
    @Column(name = "guest_surname")
    private String guestSurname;

    public Long getStructureId() {
        return (long) structure.getId();
    }
}
