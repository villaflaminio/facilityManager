package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quotes")
@Tag(name = "QuoteController", description = "The quotes APIs")
public interface QuoteController {
}
