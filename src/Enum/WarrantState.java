package Enum;

public enum WarrantState {
    ON_HOLD, IN_PROGRESS, DONE, IN_ARCHIVE;

    WarrantState() {
    }

    public static WarrantState getWarrantState(String type) {
        switch (type) {
            case "NA PREGLEDU": return IN_PROGRESS;
            case "ZAVRSEN": return DONE;
            case "NA CEKANJU": return ON_HOLD;
            case "U ARHIVI": return IN_ARCHIVE;
            case "ON_HOLD": return ON_HOLD;
            case "IN PROGRESS": return IN_PROGRESS;
            case "DONE": return DONE;
            case "IN ARCHIVE": return IN_ARCHIVE;
        }
        return null;
    }

}
