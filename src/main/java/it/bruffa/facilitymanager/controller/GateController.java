package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.model.dto.request.CreateGateRequest;
import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.UserPrincipal;
import it.bruffa.facilitymanager.model.exception.ApiError;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.model.projection.GateInfo;
import it.bruffa.facilitymanager.security.CurrentUser;
import jakarta.validation.Valid;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface GateController {


    @Operation(summary = "Get gate by id", description = "Get gate by id", tags = {"gate"})
    @GetMapping("/{gateId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<GateInfo> getGateById(@PathVariable @Schema(example = "1") Long gateId) throws ItemNotFoundException;

    @Operation(summary = "Get gate by name", description = "Get gate by name", tags = {"gate"})
    @GetMapping("/name/{gateName}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<GateInfo> getGateByName(@PathVariable @Schema(example = "name") String gateName) throws ItemNotFoundException;

    @Operation(summary = "Create gate", description = "Create gate", tags = {"gate"})
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Gate> createGate(@RequestBody @Valid CreateGateRequest createGateRequest) throws Exception;

    @Operation(summary = "Update gate", description = "Update gate", tags = {"gate"})
    @PutMapping("/{gateId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Gate> updateGate(@PathVariable @Schema(example = "1") Long gateId, @RequestBody @Valid CreateGateRequest createGateRequest) throws ItemNotFoundException, Exception;

    @Operation(summary = "Delete gate", description = "Delete gate", tags = {"gate"})
    @DeleteMapping("/{gateId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> deleteGate(@PathVariable @Schema(example = "1") Long gateId) throws ItemNotFoundException;

    @Operation(summary = "Open gate", description = "Open gate", tags = {"gate"})
    @PutMapping("/{gateId}/open")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully opened"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> openGate(@CurrentUser UserPrincipal userPrincipal, @PathVariable @Schema(example = "1") Long gateId) throws ItemNotFoundException, MqttException;

}
