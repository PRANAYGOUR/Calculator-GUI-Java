import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorApp extends JFrame implements ActionListener {
    // Components
    private JTextField display;
    private JButton[] numberButtons = new JButton[10];
    private JButton addBtn, subBtn, mulBtn, divBtn, decBtn, eqBtn, delBtn, clrBtn;
    private JPanel panel;

// Theme colors for modern dark styling
private static final Color BG = new Color(18, 18, 18);
private static final Color PANEL_BG = new Color(28, 28, 28);
private static final Color DISPLAY_BG = new Color(24, 24, 24);
private static final Color TEXT = new Color(245, 245, 245);
private static final Color BTN_BG = new Color(45, 45, 45);
private static final Color BTN_BORDER = new Color(65, 65, 65);
private static final Color OP_BG = new Color(33, 150, 243);
private static final Color EQ_BG = new Color(255, 87, 34);
private static final Color CLEAR_BG = new Color(90, 90, 90);

    private Font myFont = new Font("Segoe UI", Font.PLAIN, 20);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public CalculatorApp() {
        // Frame setup
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG);

        // Display field in rounded card
        RoundedPanel displayCard = new RoundedPanel(18);
        displayCard.setBounds(50, 25, 300, 60);
        displayCard.setBackground(PANEL_BG);
        displayCard.setLayout(new BorderLayout(8, 8));

        display = new JTextField();
        display.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setForeground(TEXT);
        display.setBackground(DISPLAY_BG);
        display.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));

        displayCard.add(display, BorderLayout.CENTER);
        add(displayCard);

        // Buttons
        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        decBtn = new JButton(".");
        eqBtn = new JButton("=");
        delBtn = new JButton("Del");
        clrBtn = new JButton("Clr");

        JButton[] funcButtons = {addBtn, subBtn, mulBtn, divBtn, decBtn, eqBtn, delBtn, clrBtn};

        for (JButton btn : funcButtons) {
            btn.addActionListener(this);
            btn.setFont(myFont);
            btn.setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }
        // Style number buttons
        for (int i = 0; i < 10; i++) {
            styleBaseButton(numberButtons[i], BTN_BG);
        }
        // Style functional buttons
        styleAccentButton(addBtn, OP_BG);
        styleAccentButton(subBtn, OP_BG);
        styleAccentButton(mulBtn, OP_BG);
        styleAccentButton(divBtn, OP_BG);
        styleBaseButton(decBtn, BTN_BG);
        styleAccentButton(eqBtn, EQ_BG);
        styleNeutralButton(delBtn, CLEAR_BG);
        styleNeutralButton(clrBtn, CLEAR_BG);

        // Position functional buttons
        delBtn.setBounds(50, 430, 145, 50);
        clrBtn.setBounds(205, 430, 145, 50);
        add(delBtn);
        add(clrBtn);

        // Panel for numbers and operations
        panel = new JPanel();
panel.setBounds(50, 100, 300, 300);
panel.setLayout(new GridLayout(4, 4, 10, 10));
panel.setOpaque(false);

RoundedPanel keypadCard = new RoundedPanel(18);
keypadCard.setBounds(50, 100, 300, 300);
keypadCard.setBackground(PANEL_BG);
keypadCard.setLayout(new BorderLayout());
keypadCard.add(panel, BorderLayout.CENTER);

        // Add buttons to panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addBtn);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subBtn);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulBtn);
        panel.add(decBtn);
        panel.add(numberButtons[0]);
        panel.add(eqBtn);
        panel.add(divBtn);

        add(keypadCard);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.setText(display.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decBtn) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText().concat("."));
            }
        }

        if (e.getSource() == addBtn) {
            num1 = Double.parseDouble(display.getText());
            operator = '+';
            display.setText("");
        }

        if (e.getSource() == subBtn) {
            num1 = Double.parseDouble(display.getText());
            operator = '-';
            display.setText("");
        }

        if (e.getSource() == mulBtn) {
            num1 = Double.parseDouble(display.getText());
            operator = '*';
            display.setText("");
        }

        if (e.getSource() == divBtn) {
            num1 = Double.parseDouble(display.getText());
            operator = '/';
            display.setText("");
        }

        if (e.getSource() == eqBtn) {
            num2 = Double.parseDouble(display.getText());

            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 == 0) {
                        display.setText("Error");
                        return;
                    }
                    result = num1 / num2; 
                    break;
            }

            display.setText(String.valueOf(result));
            num1 = result;
        }

        if (e.getSource() == clrBtn) {
            display.setText("");
        }

        if (e.getSource() == delBtn) {
            String text = display.getText();
            if (text.length() > 0) {
                display.setText(text.substring(0, text.length() - 1));
            }
        }
    }

    private void styleBaseButton(JButton b, Color bg) {
        b.setOpaque(true);
        b.setBackground(bg);
        b.setForeground(TEXT);
        b.setBorder(new javax.swing.border.LineBorder(BTN_BORDER, 1, true));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        installHoverEffect(b, bg, brighten(bg, 12));
    }

    private void styleAccentButton(JButton b, Color bg) {
        b.setOpaque(true);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setBorder(new javax.swing.border.LineBorder(darken(bg, 20), 1, true));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        installHoverEffect(b, bg, brighten(bg, 18));
    }

    private void styleNeutralButton(JButton b, Color bg) {
        b.setOpaque(true);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setBorder(new javax.swing.border.LineBorder(darken(bg, 25), 1, true));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        installHoverEffect(b, bg, brighten(bg, 12));
    }

    private void installHoverEffect(JButton b, Color base, Color hover) {
        b.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { b.setBackground(hover); }
            @Override public void mouseExited(MouseEvent e) { b.setBackground(base); }
            @Override public void mousePressed(MouseEvent e) { b.setBackground(brighten(hover, 10)); }
            @Override public void mouseReleased(MouseEvent e) {
                Point p = e.getPoint();
                if (p.x >= 0 && p.x <= b.getWidth() && p.y >= 0 && p.y <= b.getHeight()) {
                    b.setBackground(hover);
                } else {
                    b.setBackground(base);
                }
            }
        });
    }

    private Color brighten(Color c, int amount) {
        return new Color(
            Math.min(255, c.getRed() + amount),
            Math.min(255, c.getGreen() + amount),
            Math.min(255, c.getBlue() + amount)
        );
    }

    private Color darken(Color c, int amount) {
        return new Color(
            Math.max(0, c.getRed() - amount),
            Math.max(0, c.getGreen() - amount),
            Math.max(0, c.getBlue() - amount)
        );
    }

    static class RoundedPanel extends JPanel {
        private final int radius;
        RoundedPanel(int radius) { this.radius = radius; setOpaque(false); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            UIManager.put("control", BG);
            UIManager.put("text", TEXT);
            UIManager.put("nimbusBase", BG);
            UIManager.put("nimbusBlueGrey", PANEL_BG);
            UIManager.put("background", BG);
            UIManager.put("Panel.background", BG);
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(CalculatorApp::new);
    }
}
