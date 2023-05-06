package it.bruffa.facilitymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gate")
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "is_active")
    private Boolean isActive;


    @NotNull
    @Column(name = "mqtt_topic")
    private String mqttTopic;

    @OneToOne(mappedBy = "gate", optional = false)
    @JsonBackReference(value = "gate-structure")
    private Structure structure;


    @OneToMany(mappedBy = "gate", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "gate-accessLog")
    private List<AccessLog> accessLogs;


}
