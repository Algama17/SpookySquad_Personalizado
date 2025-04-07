package dev.ghostbusters.Model;

import java.util.HashMap;
import java.util.Map;

public enum GhostClass {
    CLASE_I(" Clase I - Manifestación menor"),
    CLASE_II("Clase II - Aparición móvil"),
    CLASE_III("Clase III - Entidad inteligente"),
    CLASE_IV("Clase IV - Fantasma histórico"),
    CLASE_V("Clase V - Espíritu antropomorfo"),
    CLASE_VI("Clase VI - Espíritu demoníaco"),
    CLASE_VII("Clase VII - Entidad ultraterrena");

    private final String displayName;

    // Constructor del enum
    GhostClass(String displayName) {
        this.displayName = displayName;
    }

    // Método para obtener el nombre para mostrar
    public String getDisplayName() {
        return displayName;
    }

    // Mapa estático para asociar nombres completos con valores del enum
    private static final Map<String, GhostClass> displayNameMap = new HashMap<>();

    static {
        for (GhostClass ghostClass : GhostClass.values()) {
            displayNameMap.put(ghostClass.getDisplayName(), ghostClass);
        }
    }

    // Método estático para obtener un valor del enum a partir del nombre completo
    public static GhostClass fromDisplayName(String displayName) {
        return displayNameMap.get(displayName);
    }

    // Método estático para obtener todas las opciones como un arreglo de cadenas
    public static String[] getDisplayNames() {
        GhostClass[] values = GhostClass.values();
        String[] displayNames = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            displayNames[i] = values[i].getDisplayName();
        }
        return displayNames;
    }
}