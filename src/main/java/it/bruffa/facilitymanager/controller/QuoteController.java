package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.model.dto.request.CreateGateRequest;
import it.bruffa.facilitymanager.model.dto.request.UpdateQuoteRequest;
import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.Quote;
import it.bruffa.facilitymanager.model.exception.ApiError;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/quotes")
@Tag(name = "QuoteController", description = "The quotes APIs")
public interface QuoteController {


    @Operation(summary = "Update quote", description = "Update quote", tags = {"quote"})
    @PutMapping("/{quoteId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Quote> updateQuote(@PathVariable @Schema(example = "1") Long quoteId, @RequestBody @Valid UpdateQuoteRequest updateQuoteRequest) throws ItemNotFoundException, Exception;

    @Operation(summary = "Add file to quote", description = "Add file", tags = {"quote"})
    @PostMapping("/{quoteId}/file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added"),
            @ApiResponse(responseCode = "404", description = "Not found - The item was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> addFileToQuote(@PathVariable @Schema(example = "1") Long quoteId, @RequestParam("file") MultipartFile file) throws ItemNotFoundException;



}
