package it.bruffa.facilitymanager.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import it.bruffa.facilitymanager.model.entity.CleaningAction;
import it.bruffa.facilitymanager.model.entity.Maintenance;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data

public class CheckListFilter {

    @Schema(description = "The id of the CheckList", example = "1")
    private Long id;

    @Schema(description = "The name of the CheckList", example = "CheckList1")
    private String name;

    @Schema(description = "The description of the CheckList", example = "CheckList1")
    private String description;

}
