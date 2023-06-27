package it.flaminiovilla.facilitymanager.repository;

import it.flaminiovilla.facilitymanager.model.entity.Role;
import it.flaminiovilla.facilitymanager.model.entity.User;
import it.flaminiovilla.facilitymanager.model.projection.UserDetailInfo;
import it.flaminiovilla.facilitymanager.model.projection.UserMeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<UserDetailInfo> getUserById(Long userId);
    Optional<UserMeInfo> getUserProjectedMeById(Long userId);

    List<User> findByRoles(Role role);

    boolean existsByEmail(String email);

    List<UserDetailInfo> findAllProjectedBy();
}


