package it.bruffa.facilitymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "structure_id")
    @JsonBackReference(value = "structure-maintenance")
    private Structure structure;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-maintenance")
    private User user;

    @ManyToOne
    @JoinColumn(name = "check_list_id")
    @JsonManagedReference(value = "checkList-maintenance")
    private CheckList checkList;

    @OneToOne
    @JoinColumn(name = "feedback_id")
    @JsonManagedReference(value = "feedback-maintenance")
    private Feedback feedback;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MaintenanceStatus status;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "date")
    private java.time.LocalDateTime date;

    @Column(name = "duration")
    private Integer duration;

    @OneToMany(mappedBy = "maintenance")
    @JsonManagedReference(value = "maintenance")
    private List<File> picturesBefore;

    @OneToMany(mappedBy = "maintenance")
    @JsonManagedReference(value = "maintenance")
    private List<File> picturesAfter;

    @OneToMany(mappedBy = "maintenance")
    @JsonManagedReference(value = "maintenance")
    private List<File> documents;

    @Column(name = "cost")
    private Double cost;

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "maintenance_quote",
            joinColumns = @JoinColumn(name = "maintenance_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id"))
    @JsonManagedReference(value = "maintenance-quote")
    private Quote quote;

}
