package it.flaminiovilla.facilitymanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.flaminiovilla.facilitymanager.model.dto.request.CreateFeedbackRequest;
import it.flaminiovilla.facilitymanager.model.entity.Feedback;
import it.flaminiovilla.facilitymanager.model.exception.ApiError;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface FeedbackController {

    //create
    @Operation(summary = "Create feedbacks", description = "Create feedbacks", tags = {"feedbacks"})
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Feedback> createFeedback(@RequestBody @Valid CreateFeedbackRequest createFeedbackRequest) throws Exception;

    //delete
    @Operation(summary = "Delete feedbacks", description = "Delete feedbacks", tags = {"feedbacks"})
    @DeleteMapping("/{feedbackId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> deleteFeedback(@PathVariable @Schema(example = "1") Long feedbackId) throws Exception;


}
