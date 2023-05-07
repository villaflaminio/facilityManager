package it.bruffa.facilitymanager.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCheckListRequest {

    @NotNull
    @Schema(example = "name")
    private String name;

    @NotNull
    @Schema(example = "description")
    private String description;

    @Schema(example = "[\"item1\", \"item2\"]")
    private List<String> items;
}
