package it.bruffa.facilitymanager.model.builder;

import it.bruffa.facilitymanager.model.entity.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public final class StructureBuilder {
    private Long id;
    private @NotNull String name;
    private @NotNull String address;
    private @NotNull String city;
    private @NotNull String cap;
    private @NotNull String province;
    private @NotNull String country;
    private @NotNull Double latitude;
    private @NotNull Double longitude;
    private @NotNull String description;
    private @NotNull Boolean isActive;
    private @Max(24) @Min(1) Integer cleaningDuration;
    private Gate gate;
    private List<CleaningAction> cleaningActions;
    private List<Maintenance> maintenances;
    private Quote quote;

    private StructureBuilder() {
    }

    public static StructureBuilder builder() {
        return new StructureBuilder();
    }

    public StructureBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public StructureBuilder name(String name) {
        this.name = name;
        return this;
    }

    public StructureBuilder address(String address) {
        this.address = address;
        return this;
    }

    public StructureBuilder city(String city) {
        this.city = city;
        return this;
    }

    public StructureBuilder cap(String cap) {
        this.cap = cap;
        return this;
    }

    public StructureBuilder province(String province) {
        this.province = province;
        return this;
    }

    public StructureBuilder country(String country) {
        this.country = country;
        return this;
    }

    public StructureBuilder latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public StructureBuilder longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public StructureBuilder description(String description) {
        this.description = description;
        return this;
    }

    public StructureBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public StructureBuilder cleaningDuration(Integer cleaningDuration) {
        this.cleaningDuration = cleaningDuration;
        return this;
    }

    public StructureBuilder gate(Gate gate) {
        this.gate = gate;
        return this;
    }

    public StructureBuilder cleaningActions(List<CleaningAction> cleaningActions) {
        this.cleaningActions = cleaningActions;
        return this;
    }

    public StructureBuilder maintenances(List<Maintenance> maintenances) {
        this.maintenances = maintenances;
        return this;
    }

    public StructureBuilder quote(Quote quote) {
        this.quote = quote;
        return this;
    }

    public Structure build() {
        Structure structure = new Structure();
        structure.setId(id);
        structure.setName(name);
        structure.setAddress(address);
        structure.setCity(city);
        structure.setCap(cap);
        structure.setProvince(province);
        structure.setCountry(country);
        structure.setLatitude(latitude);
        structure.setLongitude(longitude);
        structure.setDescription(description);
        structure.setIsActive(isActive);
        structure.setCleaningDuration(cleaningDuration);
        structure.setGate(gate);
        structure.setCleaningActions(cleaningActions);
        structure.setMaintenances(maintenances);
        structure.setQuote(quote);
        return structure;
    }
}
