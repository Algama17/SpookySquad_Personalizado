package dev.ghostbusters.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dev.ghostbusters.Model.Ghost;
import dev.ghostbusters.Model.GhostClass;
import dev.ghostbusters.Model.DangerLevel;

public class GhostController {
    private final List<Ghost> ghosts = new ArrayList<>();
    private int nextId = 1;

    // Método para capturar un fantasma
    public void captureGhost(String name, GhostClass classType, DangerLevel dangerLevel, String specialAbility) {
        Ghost ghost = new Ghost(nextId++, name, classType, dangerLevel, specialAbility, LocalDate.now());
        ghosts.add(ghost);
    }

    // Método para liberar un fantasma por ID
    public boolean releaseGhostById(int id) {
        boolean removed = ghosts.removeIf(ghost -> ghost.getId() == id);
        return removed;
    }

    // Método para filtrar fantasmas por clase
    public List<Ghost> filterGhostsByClass(GhostClass classType) {
        return ghosts.stream()
                .filter(ghost -> ghost.getClassType() == classType)
                .collect(Collectors.toList());
    }

    // Método para filtrar fantasmas por mes y año
    public List<Ghost> filterGhostsByMonthAndYear(int month, int year) {
        return ghosts.stream()
                .filter(ghost -> ghost.getCaptureDate().getMonthValue() == month && ghost.getCaptureDate().getYear() == year)
                .collect(Collectors.toList());
    }

    // Método para obtener todos los fantasmas
    public List<Ghost> getGhosts() {
        return ghosts;
    }
}