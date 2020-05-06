package TechnicalInspection;
import Enum.VehicleType;

import java.time.LocalDate;
import java.util.ArrayList;

public class Vehicle {
    private String vehicleOwner;
    private String brand;
    private VehicleType type;
    private String serialNumber;
    private Integer productionYear;
    private LocalDate releaseDate;
    private LocalDate previousInspection;
    private ArrayList<Malfunction> malfunctions;

    public Vehicle() {
    }

    public Vehicle(String vehicleOwner, String brand, VehicleType type, String serialNumber, Integer productionYear, LocalDate releaseDate, LocalDate previousInspection, ArrayList<Malfunction> malfunctions) {
        this.vehicleOwner = vehicleOwner;
        this.brand = brand;
        this.type = type;
        this.serialNumber = serialNumber;
        this.productionYear = productionYear;
        this.releaseDate = releaseDate;
        this.previousInspection = previousInspection;
        this.malfunctions = malfunctions;
    }

    public String getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getPreviousInspection() {
        return previousInspection;
    }

    public void setPreviousInspection(LocalDate previousInspection) {
        this.previousInspection = previousInspection;
    }

    public ArrayList<Malfunction> getMalfunctions() {
        return malfunctions;
    }

    public void setMalfunctions(ArrayList<Malfunction> malfunctions) {
        this.malfunctions = malfunctions;
    }
}
