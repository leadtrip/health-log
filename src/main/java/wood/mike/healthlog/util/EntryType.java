package wood.mike.healthlog.util;

public enum EntryType {
    GENERAL("General"),
    EXERCISE("Exercise"),
    FOOD("Food"),
    SLEEP("Sleep");

    private final String label;

    EntryType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
