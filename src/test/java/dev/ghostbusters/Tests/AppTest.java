package dev.ghostbusters.Tests;

import dev.ghostbusters.Controller.GhostController;
import dev.ghostbusters.View.GhostView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {
    private GhostController ghostController;
    private GhostView ghostView;

    @BeforeEach
    void setUp() {
        ghostController = new GhostController();
        ghostView = new GhostView(ghostController);
    }

    @Test
    void testAppMain() {
        assertNotNull(ghostController);
        assertNotNull(ghostView);
    }
}
