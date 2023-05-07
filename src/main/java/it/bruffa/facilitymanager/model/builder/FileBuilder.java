package it.bruffa.facilitymanager.model.builder;

import it.bruffa.facilitymanager.model.entity.CleaningAction;
import it.bruffa.facilitymanager.model.entity.File;
import it.bruffa.facilitymanager.model.entity.Maintenance;
import it.bruffa.facilitymanager.model.entity.Quote;

public final class FileBuilder {
    private Long id;
    private String name;
    private String type;
    private byte[] imageData;
    private CleaningAction cleaningActions;
    private Maintenance maintenance;
    private Quote quote;

    private FileBuilder() {
    }

    public static FileBuilder builder() {
        return new FileBuilder();
    }

    public FileBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public FileBuilder name(String name) {
        this.name = name;
        return this;
    }

    public FileBuilder type(String type) {
        this.type = type;
        return this;
    }

    public FileBuilder imageData(byte[] imageData) {
        this.imageData = imageData;
        return this;
    }

    public FileBuilder cleaningActions(CleaningAction cleaningActions) {
        this.cleaningActions = cleaningActions;
        return this;
    }

    public FileBuilder maintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
        return this;
    }

    public FileBuilder quote(Quote quote) {
        this.quote = quote;
        return this;
    }

    public File build() {
        File file = new File();
        file.setId(id);
        file.setName(name);
        file.setType(type);
        file.setImageData(imageData);
        file.setCleaningActions(cleaningActions);
        file.setMaintenance(maintenance);
        file.setQuote(quote);
        return file;
    }
}
