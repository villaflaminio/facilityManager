package it.bruffa.facilitymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "cleaning_action")
public class CleaningAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "check_list_id")
    @JsonManagedReference(value = "checkList-cleaningAction")
    private CheckList checkList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-cleaningAction")
    private User user;

    @ManyToOne
    @JoinColumn(name = "structure_id")
    @JsonBackReference(value = "structure-cleaningAction")
    private Structure structure;

    @Column(name = "date")
    private LocalDate date;

    @Lob
    @Column(name = "note")
    private String note;

    @Lob
    @Column(name = "report")
    private String report;

    @OneToMany(mappedBy = "cleaningActions")
    @JsonManagedReference(value = "cleaningAction-picture")
    private List<File> pictures;

    @OneToOne
    @JoinColumn(name = "feedback_id")
    @JsonManagedReference(value = "feedback-cleaningAction")
    private Feedback feedback;

    @Column(name = "cleaning_duration")
    @Max(24)
    @Min(1)
    private Integer cleaningDuration;

    public Long getStructureId() {
        return structure.getId();
    }
    public Long getUserId() {
        return user.getId();
    }
}
