package dev.ghostbusters;

import dev.ghostbusters.Controller.GhostController;
import dev.ghostbusters.View.GhostViewGUI;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        GhostController controller = new GhostController();
        SwingUtilities.invokeLater(() -> new GhostViewGUI(controller));
    }
}