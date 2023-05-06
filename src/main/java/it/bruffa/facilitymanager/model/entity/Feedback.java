package it.bruffa.facilitymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    @Max(5)
    @Min(1)
    private Integer rating;

    @Column(name = "rating_before_intervention")
    @Max(5)
    @Min(1)
    private Integer ratingBeforeIntervention;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "feedback-user")
    private User user;



    @Column(name = "date")
    @NotNull
    private LocalDateTime date;

    @Lob
    private String note;


    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "feedback_maintenance",
            joinColumns = @JoinColumn(name = "feedback_id"),
            inverseJoinColumns = @JoinColumn(name = "maintenance_id"))
    @JsonBackReference(value = "feedback-maintenance")
    private Maintenance maintenance;

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "feedback_cleaning_action",
            joinColumns = @JoinColumn(name = "feedback_id"),
            inverseJoinColumns = @JoinColumn(name = "cleaning_action_id"))
    @JsonBackReference(value = "feedback-cleaningAction")
    private CleaningAction cleaningAction;

}
