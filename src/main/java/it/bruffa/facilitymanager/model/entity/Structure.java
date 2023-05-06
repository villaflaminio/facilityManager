package it.bruffa.facilitymanager.model.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
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


    @OneToOne
    @JoinColumn(name = "gate_id")
    private Gate gate;


    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "structure_id")
    private List<CleaningAction> cleaningActions = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "structure_id")
    private List<Maintenance> maintenances = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "structure_quote",
            joinColumns = @JoinColumn(name = "structure_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id"))
    private Quote quote;

}
