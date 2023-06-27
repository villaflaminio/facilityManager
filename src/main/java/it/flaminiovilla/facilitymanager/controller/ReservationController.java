package it.flaminiovilla.facilitymanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.flaminiovilla.facilitymanager.model.dto.ReservationFilter;
import it.flaminiovilla.facilitymanager.model.dto.request.CreateReservationRequest;
import it.flaminiovilla.facilitymanager.model.entity.Reservation;
import it.flaminiovilla.facilitymanager.model.exception.ApiError;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface ReservationController {

    @Operation(summary = "filter", description = "Filter Reservation", tags = {"reservation"})
    @PostMapping("/filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Error not found - EmptyArray Exception",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Page<Reservation>> filter(
            @RequestBody(required = false) ReservationFilter probe,
            @RequestParam(required = false, defaultValue = "0", name = "page") Integer page,
            @RequestParam(required = false, defaultValue = "10", name = "size") Integer size,
            @RequestParam(required = false, name = "sortField") String sortField,
            @RequestParam(required = false, name = "sortDirection") String sortDirection);


    @Operation(summary = "Get reservation by id", description = "Get reservation by id", tags = {"reservation"})
    @GetMapping("/{reservationId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Reservation> getReservationById(@PathVariable @Schema(example = "1") Long reservationId);

    @Operation(summary = "Create reservation", description = "Create reservation", tags = {"reservation"})
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad request - The request was invalid or cannot be served",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Reservation> createReservation(@RequestBody CreateReservationRequest createReservationRequest);


    //delete
    @Operation(summary = "Delete reservation", description = "Delete reservation", tags = {"reservation"})
    @DeleteMapping("/{reservationId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> deleteReservation(@PathVariable @Schema(example = "1") Long reservationId);

}


