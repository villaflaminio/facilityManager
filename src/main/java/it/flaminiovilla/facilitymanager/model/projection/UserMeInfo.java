package it.flaminiovilla.facilitymanager.model.projection;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * A Projection for the {@link User} entity
 */
public interface UserMeInfo {
    Long getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getUsername();

    boolean isEnable();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();


    Collection<RoleInfo> getRoles();


    /**
     * A Projection for the {@link Role} entity
     */
    interface RoleInfo {
        Long getId();

        String getName();
    }


}