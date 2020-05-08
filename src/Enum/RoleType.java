package Enum;

public enum RoleType {
    EMPLOYEE, MENAGER, ADMIN;

    RoleType() {
    }

    public static RoleType getRoleType(String type) {
        switch (type) {
            case "EMPLOYEE": return EMPLOYEE;
            case "MENAGER": return MENAGER;
            case "ADMIN": return ADMIN;
            case "RADNIK": return EMPLOYEE;
            case "MENADZER": return MENAGER;
            case "ADMINISTRATOR": return ADMIN;
        }
        return null;
    }
}
