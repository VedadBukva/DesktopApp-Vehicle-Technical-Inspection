package Enum;

public enum InspectionType {
    REGULAR, PREVENTIVE, EXTRAORDINARY;

    InspectionType() {
    }

    public static InspectionType getInspectionType(String type) {
        switch (type) {
            case "REGULARNI": return REGULAR;
            case "PREVENTIVNI": return PREVENTIVE;
            case "VANREDNI": return EXTRAORDINARY;
            case "REGULAR": return REGULAR;
            case "PREVENTIVE": return PREVENTIVE;
            case "EXTRAORDINARY": return EXTRAORDINARY;
        }
        return null;
    }
}
