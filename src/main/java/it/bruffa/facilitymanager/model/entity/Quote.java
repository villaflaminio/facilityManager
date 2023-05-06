package it.bruffa.facilitymanager.model.entity;

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

    @OneToOne
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenance;

    @OneToOne
    @JoinColumn(name = "structure_id")
    private Structure structure;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "time")
    @Min(1)
    private Integer time;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;

    @NotNull
    @Column(name = "accepted")
    private Boolean accepted;

}
