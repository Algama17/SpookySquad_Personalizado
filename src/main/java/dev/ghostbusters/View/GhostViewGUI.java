package dev.ghostbusters.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dev.ghostbusters.Controller.GhostController;
import dev.ghostbusters.Model.DangerLevel;
import dev.ghostbusters.Model.Ghost;
import dev.ghostbusters.Model.GhostClass;

public class GhostViewGUI extends JFrame {
    private final GhostController ghostController;

    public GhostViewGUI(GhostController ghostController) {
        this.ghostController = ghostController;

        setTitle("Ghostbusters Asturias");
        setSize(1600, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel() {
            private Image backgroundImage;

            {
                backgroundImage = new ImageIcon("resources/images/foto1.jpg").getImage();
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        Dimension buttonSize = new Dimension(300, 40);
        Color buttonBg = new Color(30, 30, 30);
        Color buttonFg = Color.WHITE;

        RoundedButton captureButton = createStyledButton("Capturar Fantasma", buttonSize, buttonBg, buttonFg);
        RoundedButton listaCapturados = createStyledButton("Ver lista de fantasmas capturados", buttonSize, buttonBg, buttonFg);
        RoundedButton liberarFantasma = createStyledButton("Liberar un fantasma", buttonSize, buttonBg, buttonFg);
        RoundedButton filtrarClase = createStyledButton("Filtrar fantasmas por clase", buttonSize, buttonBg, buttonFg);
        RoundedButton capturadosMes = createStyledButton("Ver fantasmas capturados en un mes", buttonSize, buttonBg, buttonFg);
        RoundedButton salir = createStyledButton("Salir", buttonSize, buttonBg, buttonFg);

        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setOpaque(false);
        resultArea.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(GhostViewGUI.this, "Capturar Fantasma", true);
                dialog.setSize(1024, 600);
                dialog.setLayout(new BorderLayout());

                JPanel dialogPanel = new JPanel() {
                    private Image backgroundImage;

                    {
                        backgroundImage = new ImageIcon("resources/images/foto3.jpg").getImage();
                    }

                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    }
                };
                dialogPanel.setLayout(new BorderLayout());
                dialogPanel.setOpaque(false);

                JTextField nameField = new JTextField();
                nameField.setPreferredSize(new Dimension(200, 25));

                JTextField abilityField = new JTextField();
                abilityField.setPreferredSize(new Dimension(200, 25));

                JComboBox<String> classDropdown = new JComboBox<>(GhostClass.getDisplayNames());
                classDropdown.setPreferredSize(new Dimension(200, 25));

                JComboBox<String> dangerDropdown = new JComboBox<>(DangerLevel.getDisplayNames());
                dangerDropdown.setPreferredSize(new Dimension(200, 25));

                JPanel formPanel = new JPanel(new GridBagLayout());
                formPanel.setOpaque(false);
                formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.anchor = GridBagConstraints.WEST;
                gbc.insets = new Insets(5, 10, 5, 10);

                gbc.gridx = 0;
                gbc.gridy = 0;
                formPanel.add(createStyledLabel("Nombre:"), gbc);

                gbc.gridx = 1;
                formPanel.add(nameField, gbc);


                gbc.gridx = 0;
                gbc.gridy = 1;
                formPanel.add(createStyledLabel("Clase:"), gbc);

                gbc.gridx = 1;
                formPanel.add(classDropdown, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                formPanel.add(createStyledLabel("Nivel de Peligro:"), gbc);

                gbc.gridx = 1;
                formPanel.add(dangerDropdown, gbc);

                gbc.gridx = 0;
                gbc.gridy = 3;
                formPanel.add(createStyledLabel("Habilidad Especial:"), gbc);

                gbc.gridx = 1;
                formPanel.add(abilityField, gbc);

                JPanel formContainer = new JPanel(new BorderLayout());
                formContainer.setOpaque(false);
                formContainer.add(formPanel, BorderLayout.WEST);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setOpaque(false);
                RoundedButton okButton = createStyledButton("Aceptar", new Dimension(120, 35), buttonBg, buttonFg);
                RoundedButton cancelButton = createStyledButton("Cancelar", new Dimension(120, 35), buttonBg, buttonFg);

                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText().trim();
                        String selectedClass = (String) classDropdown.getSelectedItem();
                        GhostClass classType = GhostClass.fromDisplayName(selectedClass);

                        String selectedDanger = (String) dangerDropdown.getSelectedItem();
                        DangerLevel dangerLevel = DangerLevel.fromDisplayName(selectedDanger);

                        String specialAbility = abilityField.getText().trim();

                        if (name.isEmpty()) {
                            JOptionPane.showMessageDialog(dialog, "El campo 'Nombre' es obligatorio.");
                            return;
                        }
                        if (specialAbility.isEmpty()) {
                            JOptionPane.showMessageDialog(dialog, "El campo 'Habilidad Especial' es obligatorio.");
                            return;
                        }

                        ghostController.captureGhost(name, classType, dangerLevel, specialAbility);
                        resultArea.setText("Fantasma \"" + name + "\" capturado exitosamente.\n");

                        List<Ghost> ghosts = ghostController.getGhosts();
                        StringBuilder allGhosts = new StringBuilder("Lista de Fantasmas Capturados:\n");
                        for (Ghost ghost : ghosts) {
                            allGhosts.append("ID: ").append(ghost.getId())
                                    .append(", Nombre: ").append(ghost.getName())
                                    .append(", Clase: ").append(ghost.getClassType().getDisplayName())
                                    .append(", Nivel de Peligro: ").append(ghost.getDangerLevel().getDisplayName())
                                    .append(", Habilidad Especial: ").append(ghost.getSpecialAbility())
                                    .append("\n");
                        }
                        resultArea.append(allGhosts.toString());
                        dialog.dispose();
                    }
                });


                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });

                buttonPanel.add(okButton);
                buttonPanel.add(cancelButton);

                dialogPanel.add(formContainer, BorderLayout.CENTER);
                dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

                dialog.add(dialogPanel);

                dialog.setLocationRelativeTo(null);

                dialog.setVisible(true);
            }
        });

        listaCapturados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog ghostListDialog = new JDialog(GhostViewGUI.this, "Lista de Fantasmas Capturados", true);
                ghostListDialog.setSize(1000, 500);
                ghostListDialog.setLayout(new BorderLayout());

                JPanel dialogPanel = new JPanel() {
                    private Image backgroundImage;

                    {
                        backgroundImage = new ImageIcon("resources/images/foto2.jpg").getImage();
                    }

                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    }
                };
                dialogPanel.setLayout(new BorderLayout());
                dialogPanel.setOpaque(false);

                JTextArea ghostListArea = new JTextArea();
                ghostListArea.setEditable(false);
                ghostListArea.setOpaque(false);
                ghostListArea.setForeground(Color.WHITE);

                JScrollPane scrollPane = new JScrollPane(ghostListArea);
                scrollPane.setOpaque(false);
                scrollPane.getViewport().setOpaque(false);
                scrollPane.setBorder(null);

                List<Ghost> ghosts = ghostController.getGhosts();
                StringBuilder allGhosts = new StringBuilder("Lista de Fantasmas Capturados:\n");
                if (ghosts.isEmpty()) {
                    allGhosts.append("No hay fantasmas capturados aún.\n");
                } else {
                    for (Ghost ghost : ghosts) {
                        allGhosts.append("ID: ").append(ghost.getId())
                                .append(", Nombre: ").append(ghost.getName())
                                .append(", Clase: ").append(ghost.getClassType().getDisplayName())
                                .append(", Nivel de Peligro: ").append(ghost.getDangerLevel().getDisplayName())
                                .append(", Habilidad Especial: ").append(ghost.getSpecialAbility())
                                .append("\n");
                    }
                }

                ghostListArea.setText(allGhosts.toString());

                RoundedButton closeButton = createStyledButton("Cerrar", new Dimension(120, 35), buttonBg, buttonFg);
                closeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ghostListDialog.dispose();
                    }
                });

                JPanel buttonPanel = new JPanel();
                buttonPanel.setOpaque(false);
                buttonPanel.add(closeButton);

                dialogPanel.add(scrollPane, BorderLayout.CENTER);
                dialogPanel.add(buttonPanel, BorderLayout.SOUTH);
                ghostListDialog.add(dialogPanel);

                ghostListDialog.setLocationRelativeTo(null);
                ghostListDialog.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.add(captureButton);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(listaCapturados);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(liberarFantasma);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(filtrarClase);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(capturadosMes);
        buttonPanel.add(Box.createVerticalStrut(15));
        buttonPanel.add(salir);

        JPanel centeredButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centeredButtonPanel.setOpaque(false);
        centeredButtonPanel.add(buttonPanel);

        JPanel bottomPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bottomPanel.setOpaque(false);
        bottomPanel.setPreferredSize(new Dimension(getWidth(), getHeight() / 5));
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centeredButtonPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    class RoundedButton extends JButton {
        private final int arcWidth = 20;
        private final int arcHeight = 20;

        public RoundedButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 25));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

            g2.setColor(getForeground());
            FontMetrics fm = g2.getFontMetrics();
            Rectangle stringBounds = fm.getStringBounds(this.getText(), g2).getBounds();
            int textX = (getWidth() - stringBounds.width) / 2;
            int textY = (getHeight() - stringBounds.height) / 2 + fm.getAscent();
            g2.drawString(getText(), textX, textY);

            g2.dispose();
        }
    }

    private RoundedButton createStyledButton(String text, Dimension size, Color bg, Color fg) {
        RoundedButton btn = new RoundedButton(text);
        btn.setPreferredSize(size);
        btn.setMaximumSize(size);
        btn.setMinimumSize(size);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg);
            }
        });
        
        return btn;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }
}