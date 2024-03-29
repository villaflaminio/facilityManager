package it.flaminiovilla.facilitymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String description;

    @Column(name = "date")
    private java.time.LocalDateTime date;

    @Column(name = "duration")
    private Integer duration;

    @OneToMany(mappedBy = "maintenance")
    @JsonManagedReference(value = "maintenance")
    @JsonIgnore
    private List<File> pictures;

    @OneToMany(mappedBy = "maintenance")
    @JsonManagedReference(value = "maintenance")
    @JsonIgnore
    private List<File> documents;

    @Column(name = "cost")
    private Double cost;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quote_id", referencedColumnName = "id")
    @JsonManagedReference(value = "maintenance-quote")
    private Quote quote;
    public Long getStructureId() {
        return structure.getId();
    }
    public Long getUserId() {
        return user.getId();
    }
}
