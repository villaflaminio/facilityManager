package it.flaminiovilla.facilitymanager.controller.impl;

import it.flaminiovilla.facilitymanager.controller.UserController;
import it.flaminiovilla.facilitymanager.model.dto.request.ChangePassworRequest;
import it.flaminiovilla.facilitymanager.model.dto.request.SignUpRequestDto;
import it.flaminiovilla.facilitymanager.model.entity.User;
import it.flaminiovilla.facilitymanager.model.entity.UserPrincipal;
import it.flaminiovilla.facilitymanager.model.exception.ItemNotFoundException;
import it.flaminiovilla.facilitymanager.model.projection.UserDetailInfo;
import it.flaminiovilla.facilitymanager.service.UserService;
import it.flaminiovilla.facilitymanager.constants.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(Endpoints.API + Endpoints.USER)
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
    public User disableUser(Long userId) throws ItemNotFoundException {
       return userService.disableUser(userId);
    }

    @Override
    public User enableUser(Long userId) throws ItemNotFoundException {
        return userService.enableUser(userId);
    }

    @Override
    public List<UserDetailInfo> getUsers() {
        return  userService.getUsers();
    }

}
