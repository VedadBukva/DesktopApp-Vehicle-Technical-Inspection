package TechnicalInspection;

import Enum.*;

public class TechnicalInspection {
    private int id;
    private InspectionType inspectionType;
    private User user;
    private Vehicle vehicle;
    private WarrantState warrantState;

    public TechnicalInspection() {
    }

    public TechnicalInspection(InspectionType inspectionType, User user, Vehicle vehicle, WarrantState warrantState) {
        this.inspectionType = inspectionType;
        this.user = user;
        this.vehicle = vehicle;
        this.warrantState = warrantState;
    }

    public TechnicalInspection(int id, InspectionType inspectionType, User user, Vehicle vehicle, WarrantState warrantState) {
        this.id = id;
        this.inspectionType = inspectionType;
        this.user = user;
        this.vehicle = vehicle;
        this.warrantState = warrantState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InspectionType getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(InspectionType inspectionType) {
        this.inspectionType = inspectionType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User employee) {
        this.user = employee;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public WarrantState getWarrantState() {
        return warrantState;
    }

    public void setWarrantState(WarrantState warrantState) {
        this.warrantState = warrantState;
    }
}
