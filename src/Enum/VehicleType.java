package Enum;

public enum VehicleType {
    TRAILER_VEHICLE, PASSENGER_VEHICLE, TRUCK;

    VehicleType() {
    }

    public static VehicleType getVehicleType(String type) {
        switch (type) {
            case "TERETNO": return TRUCK;
            case "PUTNICKO": return PASSENGER_VEHICLE;
            case "PRIKLJUCNO": return TRAILER_VEHICLE;
            case "TRUCK": return TRUCK;
            case "PASSENGER_VEHICLE": return PASSENGER_VEHICLE;
            case "TRAILER_VEHICLE": return TRAILER_VEHICLE;
        }
        return null;
    }
}
