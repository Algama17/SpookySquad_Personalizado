package dev.ghostbusters.Tests;

import dev.ghostbusters.Controller.GhostController;
import dev.ghostbusters.View.GhostView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class GhostViewGUITest {

    private GhostController ghostController;
    private GhostView ghostView;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        ghostController = new GhostController();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testCaptureGhostInteraction() {
        String input = "1\nFantasma 1\n1\nBajo\nInvisible\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ghostView = new GhostView(ghostController);
        ghostView.start();

        assertTrue(outputStream.toString().contains("Fantasma \"Fantasma 1\" capturado exitosamente"));
    }

    @Test
    void testCaptureGhostInvalidClass() {
        String input = "1\nFantasma 1\n99\n1\nBajo\nInvisible\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ghostView = new GhostView(ghostController);
        ghostView.start();

        assertTrue(outputStream.toString().contains("Clase Desconocida. Usando Clase I por defecto."));
    }

    @Test
    void testShowCapturedGhosts() {
        ghostController.captureGhost("Fantasma 1", "Clase I", "Bajo", "Invisible");

        String input = "2\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ghostView = new GhostView(ghostController);
        ghostView.start();

        assertTrue(outputStream.toString().contains("Fantasma 1"));
    }

    @Test
    void testReleaseGhost() {
        ghostController.captureGhost("Fantasma 1", "Clase I", "Bajo", "Invisible");

        String input = "3\n1\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ghostView = new GhostView(ghostController);
        ghostView.start();

        assertTrue(outputStream.toString().contains("Fantasma liberado exitosamente."));
    }

    @Test
    void testReleaseGhostInvalidId() {
        String input = "3\n99\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ghostView = new GhostView(ghostController);
        ghostView.start();

        assertTrue(outputStream.toString().contains("No se encontró un fantasma con el ID proporcionado."));
    }

    @Test
    void testFilterGhostsByClass() {
        ghostController.captureGhost("Fantasma 1", "Clase I", "Bajo", "Invisible");
        ghostController.captureGhost("Fantasma 2", "Clase II", "Medio", "Gritos");

        String input = "4\nClase I\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ghostView = new GhostView(ghostController);
        ghostView.start();

        assertTrue(outputStream.toString().contains("Fantasma 1"));
    }

    @Test
    void testFilterGhostsByClassNoResults() {
        String input = "4\nClase III\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ghostView = new GhostView(ghostController);
        ghostView.start();

        assertTrue(outputStream.toString().contains("No se encontraron fantasmas de la clase Clase III."));
    }

    @Test
    void testFilterGhostsByMonth() {
        ghostController.captureGhost("Fantasma 1", "Clase I", "Bajo", "Invisible");

        String input = "5\n10/2023\n6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ghostView = new GhostView(ghostController);
        ghostView.start();

        assertTrue(outputStream.toString().contains("No se encontraron fantasmas capturados en 10/2023."));
    }

    @Test
    void testExitProgram() {
        String input = "6\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ghostView = new GhostView(ghostController);
        ghostView.start();

        assertTrue(outputStream.toString().contains("¡Gracias por proteger Asturias!"));
    }
}