package it.bruffa.facilitymanager.model.builder;

import it.bruffa.facilitymanager.model.entity.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public final class QuoteBuilder {
    private Long id;
    private @NotNull String description;
    private Double price;
    private Maintenance maintenance;
    private Structure structure;
    private User user;
    private @Min(1) Integer time;
    private File file;
    private @NotNull Boolean accepted;

    private QuoteBuilder() {
    }

    public static QuoteBuilder builder() {
        return new QuoteBuilder();
    }

    public QuoteBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public QuoteBuilder description(String description) {
        this.description = description;
        return this;
    }

    public QuoteBuilder price(Double price) {
        this.price = price;
        return this;
    }

    public QuoteBuilder maintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
        return this;
    }

    public QuoteBuilder structure(Structure structure) {
        this.structure = structure;
        return this;
    }

    public QuoteBuilder user(User user) {
        this.user = user;
        return this;
    }

    public QuoteBuilder time(Integer time) {
        this.time = time;
        return this;
    }

    public QuoteBuilder file(File file) {
        this.file = file;
        return this;
    }

    public QuoteBuilder accepted(Boolean accepted) {
        this.accepted = accepted;
        return this;
    }

    public Quote build() {
        Quote quote = new Quote();
        quote.setId(id);
        quote.setDescription(description);
        quote.setPrice(price);
        quote.setMaintenance(maintenance);
        quote.setStructure(structure);
        quote.setUser(user);
        quote.setTime(time);
        quote.setFile(file);
        quote.setAccepted(accepted);
        return quote;
    }
}
