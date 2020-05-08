package TechnicalInspection;

import Enum.*;

public class TechnicalInspection {
    private int id;
    private InspectionType inspectionType;
    private User responsibleEmployee;
    private Vehicle vehicle;
    private WarrantState warrantState;

    public TechnicalInspection() {
    }

    public TechnicalInspection(int id, InspectionType inspectionType, User responsibleEmployee, Vehicle vehicle, WarrantState warrantState) {
        this.id = id;
        this.inspectionType = inspectionType;
        this.responsibleEmployee = responsibleEmployee;
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

    public User getResponsibleEmployee() {
        return responsibleEmployee;
    }

    public void setResponsibleEmployee(User responsibleEmployee) {
        this.responsibleEmployee = responsibleEmployee;
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
