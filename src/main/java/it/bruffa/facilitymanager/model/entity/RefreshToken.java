package it.bruffa.facilitymanager.model.entity;

import it.bruffa.facilitymanager.model.modelbase.DateAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.time.Instant;

@EnableAutoConfiguration
@Getter
@Setter
@ToString
@Entity
@Table(name = "refresh_token")
public class RefreshToken extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;
}
