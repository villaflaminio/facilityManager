package it.bruffa.facilitymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "access_log")
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private Long timestamp;


    @ManyToOne()
    @JoinColumn(name = "gate_id", referencedColumnName = "id" , nullable = false )
    @JsonBackReference(value = "gate-accessLog")
    private Gate gate;

}