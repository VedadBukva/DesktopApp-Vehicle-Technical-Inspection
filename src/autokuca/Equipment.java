package autokuca;

public class Equipment {
    private String equipmentName;
    private Boolean availability;

    public Equipment() {
    }

    public Equipment(String equipmentName, Boolean availability) {
        this.equipmentName = equipmentName;
        this.availability = availability;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
