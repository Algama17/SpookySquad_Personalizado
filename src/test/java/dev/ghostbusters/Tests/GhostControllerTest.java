package dev.ghostbusters.Tests;

import dev.ghostbusters.Controller.GhostController;
import dev.ghostbusters.Model.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GhostControllerTest {

    private GhostController ghostController;

    @BeforeEach
    public void setUp() {
        ghostController = new GhostController();

        ghostController.getGhosts().addAll(List.of(
            new Ghost(1, "Ghost 1", "Class IV", "High", "Invisibility", LocalDate.now()),
            new Ghost(2, "Ghost 2", "Class IV", "Low", "Screaming", LocalDate.now())
        ));
    }

    @Test
    public void testCaptureGhost() {
        List<Ghost> capturedGhosts = ghostController.getGhosts();
        assertEquals(2, capturedGhosts.size()); 
    }

    @Test
    public void testReleaseGhostById() {
        assertTrue(ghostController.releaseGhostById(1)); 
        assertFalse(ghostController.releaseGhostById(99)); 
    }

    @Test
    public void testFilterGhostsByClass() {
        List<Ghost> filteredGhosts = ghostController.filterGhostsByClass("Class IV");

        assertEquals(2, filteredGhosts.size());
        assertEquals("Ghost 1", filteredGhosts.get(0).getName());
        assertEquals("Ghost 2", filteredGhosts.get(1).getName());
    }

    @Test
    public void testFilterGhostsByDate() {
        List<Ghost> ghostsOnToday = ghostController.filterGhostsByDate(LocalDate.now());

        assertEquals(2, ghostsOnToday.size());
        assertEquals("Ghost 1", ghostsOnToday.get(0).getName());
        assertEquals("Ghost 2", ghostsOnToday.get(1).getName());
    }
}