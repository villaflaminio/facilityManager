package it.flaminiovilla.facilitymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quote")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;


    @OneToOne(mappedBy = "quote")
    @JsonBackReference(value = "maintenance-quote")
    private Maintenance maintenance;

    @OneToOne
    @JoinColumn(name = "structure_id")
    @JsonBackReference(value = "structure-quote")
    private Structure structure;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-quote")
    private User user;

    @Column(name = "time")
    @Min(1)
    @Schema(description = "Time in days")
    private Integer time;

    @OneToOne
    @JoinColumn(name = "file_id")
    @JsonManagedReference(value = "file-quote")
    private File file;

    @NotNull
    @Column(name = "accepted")
    private Boolean accepted;

}
