package TechnicalInspection;

import Enum.*;

public class TechnicalInspection {
    private int id;
    private InspectionType inspectionType;
    private int employeeID;
    private int vehicleID;
    private WarrantState warrantState;

    public TechnicalInspection() {
    }

    public TechnicalInspection(int id, InspectionType inspectionType, int employeeID, int vehicle, WarrantState warrantState) {
        this.id = id;
        this.inspectionType = inspectionType;
        this.employeeID = employeeID;
        this.vehicleID = vehicle;
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

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public WarrantState getWarrantState() {
        return warrantState;
    }

    public void setWarrantState(WarrantState warrantState) {
        this.warrantState = warrantState;
    }
}
