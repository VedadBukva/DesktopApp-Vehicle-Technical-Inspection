package Enum;

public enum VehicleType {
    TRAILER_VEHICLE, PASSENGER_VEHICLE, TRUCK;

    VehicleType() {
    }

    public static VehicleType getVehicleType(String type) {
        switch (type) {
            case "teretno": return TRUCK;
            case "putnicko": return PASSENGER_VEHICLE;
            case "prikljucno": return TRAILER_VEHICLE;
            case "truck": return TRUCK;
            case "passenger": return PASSENGER_VEHICLE;
            case "trailer": return TRAILER_VEHICLE;
        }
        return null;
    }
}
