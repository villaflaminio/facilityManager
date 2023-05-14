package it.bruffa.facilitymanager.model.builder;

import it.bruffa.facilitymanager.model.entity.*;

import java.time.LocalDateTime;
import java.util.List;

public final class MaintenanceBuilder {
    private Long id;
    private Structure structure;
    private User user;
    private CheckList checkList;
    private Feedback feedback;
    private MaintenanceStatus status;
    private String description;
    private LocalDateTime date;
    private Integer duration;
    private List<File> pictures;
    private List<File> documents;
    private Double cost;
    private Quote quote;

    private MaintenanceBuilder() {
    }

    public static MaintenanceBuilder builder() {
        return new MaintenanceBuilder();
    }

    public MaintenanceBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public MaintenanceBuilder structure(Structure structure) {
        this.structure = structure;
        return this;
    }

    public MaintenanceBuilder user(User user) {
        this.user = user;
        return this;
    }

    public MaintenanceBuilder checkList(CheckList checkList) {
        this.checkList = checkList;
        return this;
    }

    public MaintenanceBuilder feedback(Feedback feedback) {
        this.feedback = feedback;
        return this;
    }

    public MaintenanceBuilder status(MaintenanceStatus status) {
        this.status = status;
        return this;
    }

    public MaintenanceBuilder description(String description) {
        this.description = description;
        return this;
    }

    public MaintenanceBuilder date(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public MaintenanceBuilder duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public MaintenanceBuilder pictures(List<File> pictures) {
        this.pictures = pictures;
        return this;
    }

    public MaintenanceBuilder documents(List<File> documents) {
        this.documents = documents;
        return this;
    }

    public MaintenanceBuilder cost(Double cost) {
        this.cost = cost;
        return this;
    }

    public MaintenanceBuilder quote(Quote quote) {
        this.quote = quote;
        return this;
    }

    public Maintenance build() {
        Maintenance maintenance = new Maintenance();
        maintenance.setId(id);
        maintenance.setStructure(structure);
        maintenance.setUser(user);
        maintenance.setCheckList(checkList);
        maintenance.setFeedback(feedback);
        maintenance.setStatus(status);
        maintenance.setDescription(description);
        maintenance.setDate(date);
        maintenance.setDuration(duration);
        maintenance.setPictures(pictures);
        maintenance.setDocuments(documents);
        maintenance.setCost(cost);
        maintenance.setQuote(quote);
        return maintenance;
    }
}
