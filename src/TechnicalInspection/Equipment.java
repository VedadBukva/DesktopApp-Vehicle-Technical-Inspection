package TechnicalInspection;

public class Equipment {
    private int id;
    private String equipmentName;
    private Boolean availability;

    public Equipment() {
    }

    public Equipment(int id, String equipmentName, Boolean availability) {
        this.id = id;
        this.equipmentName = equipmentName;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
