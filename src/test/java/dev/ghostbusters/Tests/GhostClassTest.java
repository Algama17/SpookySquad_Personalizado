package dev.ghostbusters.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dev.ghostbusters.Model.Ghost;

public class GhostClassTest {

    
        @Test
        void testGhostClass() {
            int id = 1;
            String name = "Espíritu del Pescador";
            String classType = "Clase IV";
            String dangerLevel = "Bajo";
            String specialAbility = "Aparece durante tormentas";
            LocalDate captureDate= LocalDate.now();
        Ghost ghost = new Ghost(id, name, classType, dangerLevel, specialAbility, captureDate);

        assertEquals(id, ghost.getId());
        assertEquals(name, ghost.getName());
        assertEquals(classType, ghost.getClassType());
        assertEquals(dangerLevel, ghost.getDangerLevel());
        assertEquals(specialAbility, ghost.getSpecialAbility());
        assertEquals(captureDate, ghost.getCaptureDate());
    }
}

