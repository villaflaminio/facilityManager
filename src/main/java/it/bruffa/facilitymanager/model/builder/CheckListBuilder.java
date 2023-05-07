package it.bruffa.facilitymanager.model.builder;

import it.bruffa.facilitymanager.model.entity.CheckList;
import it.bruffa.facilitymanager.model.entity.CleaningAction;
import it.bruffa.facilitymanager.model.entity.Maintenance;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public final class CheckListBuilder {
    private Long id;
    private @NotNull String name;
    private @NotNull String description;
    private List<String> items;
    private List<CleaningAction> cleaningAction;
    private List<Maintenance> maintenances;

    private CheckListBuilder() {
    }

    public static CheckListBuilder builder() {
        return new CheckListBuilder();
    }

    public CheckListBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public CheckListBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CheckListBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CheckListBuilder items(List<String> items) {
        this.items = items;
        return this;
    }

    public CheckListBuilder cleaningAction(List<CleaningAction> cleaningAction) {
        this.cleaningAction = cleaningAction;
        return this;
    }

    public CheckListBuilder maintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
        return this;
    }

    public CheckList build() {
        CheckList checkList = new CheckList();
        checkList.setId(id);
        checkList.setName(name);
        checkList.setDescription(description);
        checkList.setItems(items);
        checkList.setCleaningAction(cleaningAction);
        checkList.setMaintenances(maintenances);
        return checkList;
    }
}
