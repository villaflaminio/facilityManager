package it.flaminiovilla.facilitymanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data

public class CheckListFilter {

    @Schema(description = "The id of the CheckList", example = "1")
    private Long id;

    @Schema(description = "The name of the CheckList", example = "CheckList1")
    private String name;

    @Schema(description = "The description of the CheckList", example = "CheckList1")
    private String description;

}
