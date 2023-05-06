package it.bruffa.facilitymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "check_list")
public class CheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "description")
    private String description;

    @ElementCollection
    @CollectionTable(name = "items", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "items")
    private List<String> items;

    @OneToMany(mappedBy = "checkList")
    @JsonBackReference(value = "checkList-cleaningAction")
    private List<CleaningAction> cleaningAction;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "check_list_id")
    @JsonBackReference(value = "checkList-maintenance")
    private List<Maintenance> maintenances = new ArrayList<>();

}
