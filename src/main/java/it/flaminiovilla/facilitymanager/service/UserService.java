package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.dto.MailResponse;
import it.flaminiovilla.facilitymanager.model.dto.request.ChangePassworRequest;
import it.flaminiovilla.facilitymanager.model.dto.request.SignUpRequestDto;
import it.flaminiovilla.facilitymanager.model.entity.User;
import it.flaminiovilla.facilitymanager.model.entity.UserPrincipal;
import it.flaminiovilla.facilitymanager.model.projection.UserDetailInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    User changePassword(UserPrincipal userPrincipal, ChangePassworRequest body);

    Object recoveryPassword(String userEmail);

    UserDetailInfo getUserById(Long userId);

    MailResponse requestResetPassword(User user);

    ResponseEntity<Boolean> changeRole(Long userId, String role);

    User updateUser(Long userId, SignUpRequestDto user);

    List<UserDetailInfo> getUsers();

    User disableUser(Long userId);

    User enableUser(Long userId);
}
