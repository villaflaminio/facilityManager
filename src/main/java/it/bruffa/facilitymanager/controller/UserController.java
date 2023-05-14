package it.bruffa.facilitymanager.controller;

import com.lowagie.text.DocumentException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.bruffa.facilitymanager.model.dto.request.ChangePassworRequest;
import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.model.entity.UserPrincipal;
import it.bruffa.facilitymanager.model.exception.ApiError;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.model.projection.UserDetailInfo;
import it.bruffa.facilitymanager.security.CurrentUser;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

public interface UserController {


    @Operation(summary = "Get user by id", description = "Get user by id", tags = {"user"})
    @GetMapping("/{userId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    UserDetailInfo getUserById(@PathVariable @Schema(example = "1") Long userId) throws ItemNotFoundException;

    //changePassword
    @Operation(summary = "Change password", description = "Change password", tags = {"user"})
    @PostMapping("/changePassword")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    User changePassword(@CurrentUser UserPrincipal userPrincipal, @RequestBody ChangePassworRequest body) throws ItemNotFoundException;

    //recoveryPassword
    @Operation(summary = "Recovery password", description = "Recovery password", tags = {"user"})
    @PostMapping("/recoveryPassword")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    Object recoveryPassword(@RequestParam("email") String userEmail) throws ItemNotFoundException;

    @Operation(summary = "Change role", description = "Change role", tags = {"user"})
    @PostMapping("/changeRole/{userId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request - The request was invalid or cannot be served",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))})
    })
    ResponseEntity<Boolean> changeRole(@PathVariable @Schema(example = "1") Long userId, @RequestParam @Schema(example = "ROLE_ADMIN") String role) throws ItemNotFoundException;

}
