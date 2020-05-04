package TechnicalInspection;

import java.time.LocalDate;

public class Malfunction {
    private String malfunctionName;
    private LocalDate emergenceDate;
    private LocalDate repairDate;

    public Malfunction() {
    }

    public Malfunction(String malfunctionName) { // TODO: Change database data in failure table
        this.malfunctionName = malfunctionName;
    }

    public Malfunction(String malfunctionName, LocalDate emergenceDate, LocalDate repairDate) {
        this.malfunctionName = malfunctionName;
        this.emergenceDate = emergenceDate;
        this.repairDate = repairDate;
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
