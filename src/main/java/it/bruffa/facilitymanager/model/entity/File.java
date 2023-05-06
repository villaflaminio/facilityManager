package it.bruffa.facilitymanager.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @ManyToOne()
    private CleaningAction cleaningActions;


    @Lob
    @Column(name = "imagedata")
    private byte[] imageData;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenance;

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "file_quote",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id"))
    private Quote quote;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public byte[] getImageData() {
        return imageData;
    }
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }



    public CleaningAction getCleaningActions() {
        return cleaningActions;
    }

    public void setCleaningActions(CleaningAction cleaningActions) {
        this.cleaningActions = cleaningActions;
    }
}
