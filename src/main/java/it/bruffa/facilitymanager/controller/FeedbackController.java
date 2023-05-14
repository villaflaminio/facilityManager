package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.model.dto.request.CreateFeedbackRequest;
import it.bruffa.facilitymanager.model.dto.request.CreateGateRequest;
import it.bruffa.facilitymanager.model.entity.Feedback;
import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.exception.ApiError;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedbacks")
@Tag(name = "FeedbackController", description = "The feedbacks APIs")
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
