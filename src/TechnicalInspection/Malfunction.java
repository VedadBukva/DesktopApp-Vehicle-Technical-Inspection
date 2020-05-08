package TechnicalInspection;

import java.time.LocalDate;

public class Malfunction {
    private String malfunctionName;
    private LocalDate emergenceDate;
    private LocalDate repairDate;
    private int vehicleId;

    public Malfunction() {
    }

    public Malfunction(String malfunctionName, int id, LocalDate emergenceDate, LocalDate repairDate) {
        this.malfunctionName = malfunctionName;
        this.vehicleId = id;
        this.emergenceDate = emergenceDate;
        this.repairDate = repairDate;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
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
