package dev.ghostbusters.Model;

import java.time.LocalDate;

public class Ghost {
    private int id;
    private String name;
    private GhostClass classType;
    private DangerLevel dangerLevel;
    private String specialAbility;
    private LocalDate captureDate;

    public Ghost(int id, String name, GhostClass classType, DangerLevel dangerLevel, String specialAbility, Object captureDate) {
        this.id = id;
        this.name = name;
        this.classType = classType;
        this.dangerLevel = dangerLevel;
        this.specialAbility = specialAbility;
        this.captureDate = (LocalDate) captureDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GhostClass getClassType() {
        return classType;
    }

    public DangerLevel getDangerLevel() {
        return dangerLevel;
    }

    public String getSpecialAbility() {
        return specialAbility;
    }

    public LocalDate getCaptureDate() {
        return captureDate;
    }
}