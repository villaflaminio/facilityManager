package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.Role;
import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.model.projection.UserDetailInfo;
import it.bruffa.facilitymanager.model.projection.UserMeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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


