package it.bruffa.facilitymanager.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.bruffa.facilitymanager.model.modelbase.DateAudit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User  extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email",nullable = false, unique = true)
    private String email;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;


    @NotNull
    @Column(name = "latitude")
    private Double latitude;

    @NotNull
    @Column(name = "longitude")
    private Double longitude;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private Collection<Role> roles ;

    @Transient
    @JsonIgnore
    private Set<GrantedAuthority> authorities ;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @JsonManagedReference(value = "user-cleaningAction")
    private List<CleaningAction> cleaningActions = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @JsonManagedReference(value = "feedback-user")
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    @JsonManagedReference(value = "user-maintenance")
    private List<Maintenance> maintenances = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "users_quote",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id"))
    @JsonManagedReference(value = "user-quote")
    private Quote quote;


    /**
     * Method to get the authorities of the user.
     * @return
     */
    public List<SimpleGrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role: roles) {
            list.add(new SimpleGrantedAuthority(role.getName()));
        }
        return list;
    }
    @Column(name="enable")
    private Boolean enable;

    @Column(name="credential_expired")
    private Boolean credentialExpired;

    @Column(name="account_non_expired")
    private Boolean accountNonExpired;

    @Column(name="account_non_locked")
    private Boolean accountNonLocked;

}
