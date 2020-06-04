package Enum;

public enum RoleType {
    RADNIK, MENADZER, ADMINISTRATOR;

    RoleType() {
    }

    public static RoleType getRoleType(String type) {
        switch (type) {
            case "EMPLOYEE": return RADNIK;
            case "MENAGER": return MENADZER;
            case "ADMIN": return ADMINISTRATOR;
            case "RADNIK": return RADNIK;
            case "MENADZER": return MENADZER;
            case "ADMINISTRATOR": return ADMINISTRATOR;
        }
        return null;
    }
}
