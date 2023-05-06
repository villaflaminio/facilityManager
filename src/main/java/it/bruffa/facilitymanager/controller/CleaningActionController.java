package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cleaningActions")
@Tag(name = "CleaningActionController", description = "The cleaningActions APIs")
public interface CleaningActionController {
}
