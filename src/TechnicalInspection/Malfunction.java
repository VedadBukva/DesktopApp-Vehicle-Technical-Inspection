package TechnicalInspection;

import java.time.LocalDate;

public class Malfunction {
    private String malfunctionName;
    private LocalDate emergenceDate;
    private LocalDate repairDate;
    private Vehicle vehicle;

    public Malfunction() {
    }

    public Malfunction(String malfunctionName, Vehicle id, LocalDate emergenceDate, LocalDate repairDate) {
        this.malfunctionName = malfunctionName;
        this.vehicle = id;
        this.emergenceDate = emergenceDate;
        this.repairDate = repairDate;
    }

    public Malfunction(String name, LocalDate emergenceDate, LocalDate repairDate) {
        this.malfunctionName = malfunctionName;
        this.emergenceDate = emergenceDate;
        this.repairDate = repairDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicleId) {
        this.vehicle = vehicleId;
    }

    public String getMalfunctionName() {
        return malfunctionName;
    }

    public void setMalfunctionName(String malfunctionName) {
        this.malfunctionName = malfunctionName;
    }

    public LocalDate getEmergenceDate() {
        return emergenceDate;
    }

    public void setEmergenceDate(LocalDate emergenceDate) {
        this.emergenceDate = emergenceDate;
    }

    public LocalDate getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDate repairDate) {
        this.repairDate = repairDate;
    }
}
