package TechnicalInspection;

public class Equipment {
    private int id;
    private String name;
    private Boolean availability;

    public Equipment() {
    }

    public Equipment(int id, String equipmentName, Boolean availability) {
        this.id = id;
        this.name = equipmentName;
        this.availability = availability;
    }

    public Equipment(String equipmentName, Boolean availability) {
        this.name = equipmentName;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String equipmentName) {
        this.name = equipmentName;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
