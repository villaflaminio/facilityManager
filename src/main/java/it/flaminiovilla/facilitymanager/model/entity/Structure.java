package it.flaminiovilla.facilitymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "structure")
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "cap")
    private String cap;

    @NotNull
    @Column(name = "province")
    private String province;

    @NotNull
    @Column(name = "country")
    private String country;

    @NotNull
    @Column(name = "latitude")
    private Double latitude;

    @NotNull
    @Column(name = "longitude")
    private Double longitude;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "is_active")
    private Boolean isActive;


    @Column(name = "cleaning_duration")
    @Max(24)
    @Min(1)
    private Integer cleaningDuration;

    @OneToOne
    @JoinColumn(name = "gate_id")
    @JsonManagedReference(value = "gate-structure")
    private Gate gate;


    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "structure_id")
    @JsonManagedReference(value = "structure-cleaningAction")
    private List<CleaningAction> cleaningActions = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "structure_id")
    @JsonManagedReference(value = "structure-maintenance")
    private List<Maintenance> maintenances = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "structure_quote",
            joinColumns = @JoinColumn(name = "structure_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id"))
    @JsonManagedReference(value = "structure-quote")
    private Quote quote;

}
