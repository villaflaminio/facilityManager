package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.MailResponse;
import it.bruffa.facilitymanager.model.dto.request.ChangePassworRequest;
import it.bruffa.facilitymanager.model.dto.request.SignUpRequestDto;
import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.model.entity.UserPrincipal;
import it.bruffa.facilitymanager.model.projection.UserDetailInfo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
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
