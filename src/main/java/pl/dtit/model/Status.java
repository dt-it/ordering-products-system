package pl.dtit.model;

public enum Status {
    NEW("New"), FINALIZED("Finalized");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
