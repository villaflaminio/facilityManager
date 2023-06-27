package it.flaminiovilla.facilitymanager.model.projection;

import it.flaminiovilla.facilitymanager.model.entity.Role;
import it.flaminiovilla.facilitymanager.model.entity.User;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * A Projection for the {@link User} entity
 */
public interface UserDetailInfo {
    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    Long getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getUsername();

    String getPassword();

    Boolean getEnable();

    Boolean getCredentialExpired();

    Boolean getAccountNonExpired();

    Boolean getAccountNonLocked();

    Collection<RoleInfo> getRoles();

    /**
     * A Projection for the {@link Role} entity
     */
    interface RoleInfo {
        Long getId();

        String getName();
    }
}