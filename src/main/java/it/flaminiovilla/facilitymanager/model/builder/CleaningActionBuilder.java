package it.flaminiovilla.facilitymanager.model.builder;

import it.flaminiovilla.facilitymanager.model.entity.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;
import java.util.List;

public final class CleaningActionBuilder {
    private Long id;
    private CheckList checkList;
    private User user;
    private Structure structure;
    private LocalDate date;
    private String note;
    private String report;
    private List<File> pictures;
    private Feedback feedback;
    private @Max(24) @Min(1) Integer cleaningDuration;

    private CleaningActionBuilder() {
    }

    public static CleaningActionBuilder builder() {
        return new CleaningActionBuilder();
    }

    public CleaningActionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public CleaningActionBuilder checkList(CheckList checkList) {
        this.checkList = checkList;
        return this;
    }

    public CleaningActionBuilder user(User user) {
        this.user = user;
        return this;
    }

    public CleaningActionBuilder structure(Structure structure) {
        this.structure = structure;
        return this;
    }

    public CleaningActionBuilder date(LocalDate date) {
        this.date = date;
        return this;
    }

    public CleaningActionBuilder note(String note) {
        this.note = note;
        return this;
    }

    public CleaningActionBuilder report(String report) {
        this.report = report;
        return this;
    }

    public CleaningActionBuilder pictures(List<File> pictures) {
        this.pictures = pictures;
        return this;
    }

    public CleaningActionBuilder feedback(Feedback feedback) {
        this.feedback = feedback;
        return this;
    }

    public CleaningActionBuilder cleaningDuration(Integer cleaningDuration) {
        this.cleaningDuration = cleaningDuration;
        return this;
    }

    public CleaningAction build() {
        CleaningAction cleaningAction = new CleaningAction();
        cleaningAction.setId(id);
        cleaningAction.setCheckList(checkList);
        cleaningAction.setUser(user);
        cleaningAction.setStructure(structure);
        cleaningAction.setDate(date);
        cleaningAction.setNote(note);
        cleaningAction.setReport(report);
        cleaningAction.setPictures(pictures);
        cleaningAction.setFeedback(feedback);
        cleaningAction.setCleaningDuration(cleaningDuration);
        return cleaningAction;
    }
}
