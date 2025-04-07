package dev.ghostbusters.Model;

public enum DangerLevel {
    BAJO("Bajo"),
    MEDIO("Medio"),
    ALTO("Alto"),
    CRITICO("Crítico"); // Nota: Aquí usamos "CRITICO" como nombre interno y "Crítico" como nombre para mostrar

    private final String displayName;

    DangerLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String[] getDisplayNames() {
        DangerLevel[] values = DangerLevel.values();
        String[] displayNames = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            displayNames[i] = values[i].displayName;
        }
        return displayNames;
    }

    public static DangerLevel fromDisplayName(String displayName) {
        for (DangerLevel level : DangerLevel.values()) {
            if (level.displayName.equals(displayName)) {
                return level;
            }
        }
        throw new IllegalArgumentException("No enum constant for display name: " + displayName);
    }
}