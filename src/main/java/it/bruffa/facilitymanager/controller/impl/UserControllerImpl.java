package it.bruffa.facilitymanager.controller.impl;

import com.lowagie.text.DocumentException;
import it.bruffa.facilitymanager.controller.UserController;
import it.bruffa.facilitymanager.model.dto.request.ChangePassworRequest;
import it.bruffa.facilitymanager.model.dto.request.SignUpRequestDto;
import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.model.entity.UserPrincipal;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.model.projection.UserDetailInfo;
import it.bruffa.facilitymanager.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static it.bruffa.facilitymanager.constants.Endpoints.API;
import static it.bruffa.facilitymanager.constants.Endpoints.USER;


@RestController
@RequestMapping(API + USER)
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    /**
     * @param userId
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public UserDetailInfo getUserById(Long userId) throws ItemNotFoundException {
        return userService.getUserById(userId);
    }

    /**
     * Change password
     * @param userPrincipal
     * @param body
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public User changePassword(UserPrincipal userPrincipal, ChangePassworRequest body) throws ItemNotFoundException {
        return userService.changePassword(userPrincipal, body);
    }


    @Override
    public Object recoveryPassword(String userEmail) throws ItemNotFoundException {
        return userService.recoveryPassword(userEmail);
    }

    @Override
    public ResponseEntity<Boolean> changeRole(Long userId, String role) throws ItemNotFoundException {
        return  userService.changeRole(userId, role);
    }

    @Override
    public User updateUser(Long userId, SignUpRequestDto user) throws ItemNotFoundException {
        return  userService.updateUser(userId, user);
    }

    @Override
    public List<UserDetailInfo> getUsers() {
        return  userService.getUsers();
    }

}
