/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class BackToLoginConfirmDialog extends JDialog {

    public enum Result { BACK, CANCEL }

    // Colors aligned with your dashboard
    private static final Color BG = new Color(0x2C3E50);
    private static final Color BG2 = new Color(0x32475D);
    private static final Color BORDER = new Color(0x223040);
    private static final Color TEXT = new Color(0xE6EDF3);
    private static final Color SUBTEXT = new Color(0xB8C3CE);
    private static final Color ACCENT_BLUE = new Color(0x3B82F6);

    private Result result = Result.CANCEL;
    private Point dragOffset;

    public BackToLoginConfirmDialog(Window parent, String appName) {
        super(parent);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        JPanel shell = new RoundedPanel(16, BG, BORDER);
        shell.setLayout(new BorderLayout());
        shell.setBorder(new EmptyBorder(16, 16, 16, 16));

        // Header (draggable)
        JPanel header = new RoundedPanel(12, BG2, null);
        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(12, 12, 12, 12));

        String titleText = "Back to Login?";
        if (appName != null && !appName.isEmpty()) titleText = "Back to Login — " + appName;
        JLabel title = new JLabel(titleText);
        title.setForeground(TEXT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        header.add(title, BorderLayout.WEST);

        MouseAdapter dragger = new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                dragOffset = e.getPoint();
                SwingUtilities.convertPointToScreen(dragOffset, header);
                Point wLoc = getLocationOnScreen();
                dragOffset.translate(-wLoc.x, -wLoc.y);
            }
            @Override public void mouseDragged(MouseEvent e) {
                Point p = e.getPoint();
                SwingUtilities.convertPointToScreen(p, header);
                setLocation(p.x - dragOffset.x, p.y - dragOffset.y);
            }
        };
        header.addMouseListener(dragger);
        header.addMouseMotionListener(dragger);

        // Message
        JLabel message = new JLabel("You’ll be returned to the login screen. Unsaved changes may be lost.");
        message.setForeground(SUBTEXT);
        message.setBorder(new EmptyBorder(16, 4, 0, 4));

        // Actions
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actions.setOpaque(false);

        JButton cancel = ghostButton("Cancel");
        JButton back = primaryButton("Back to Login", ACCENT_BLUE);

        cancel.addActionListener(e -> {
            result = Result.CANCEL;
            dispose();
        });
        back.addActionListener(e -> {
            result = Result.BACK;
            dispose();
        });

        actions.add(cancel);
        actions.add(back);

        // Layout
        shell.add(header, BorderLayout.NORTH);
        shell.add(message, BorderLayout.CENTER);
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        bottom.setBorder(new EmptyBorder(20, 0, 0, 0));
        bottom.add(actions, BorderLayout.EAST);
        shell.add(bottom, BorderLayout.SOUTH);

        setContentPane(shell);
        pack();
        setSize(new Dimension(440, getPreferredSize().height + 10));
        setLocationRelativeTo(parent);

        // Keys
        getRootPane().registerKeyboardAction(e -> cancel.doClick(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        getRootPane().setDefaultButton(back);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                result = Result.CANCEL;
                dispose();
            }
        });
    }

    public static Result showDialog(Window parent, String appName) {
        BackToLoginConfirmDialog d = new BackToLoginConfirmDialog(parent, appName);
        d.setVisible(true);
        return d.result;
    }

    public static Result showDialog(Window parent) {
        return showDialog(parent, null);
    }

    // --- Styled components ---

    private static JButton primaryButton(String text, Color base) {
        return new FlatButton(text,
                base,
                base.darker(),
                base.darker().darker(),
                TEXT,
                null,
                true);
    }

    private static JButton ghostButton(String text) {
        Color hover = new Color(ACCENT_BLUE.getRed(), ACCENT_BLUE.getGreen(), ACCENT_BLUE.getBlue(), 50);
        Color press = new Color(ACCENT_BLUE.getRed(), ACCENT_BLUE.getGreen(), ACCENT_BLUE.getBlue(), 80);
        return new FlatButton(text,
                new Color(0,0,0,0),
                hover,
                press,
                TEXT,
                new Color(0x3A4B5E),
                false);
    }

    private static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color fill;
        private final Color border;

        RoundedPanel(int radius, Color fill, Color border) {
            this.radius = radius;
            this.fill = fill;
            this.border = border;
            setOpaque(false);
        }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, w - 1, h - 1, radius, radius);
            if (border != null) {
                g2.setColor(border);
                g2.drawRoundRect(0, 0, w - 1, h - 1, radius, radius);
            }
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class FlatButton extends JButton {
        private final Color base, hover, press, text, border;
        private final boolean filled;

        FlatButton(String label, Color base, Color hover, Color press, Color text, Color border, boolean filled) {
            super(label);
            this.base = base; this.hover = hover; this.press = press; this.text = text; this.border = border; this.filled = filled;
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setForeground(text);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setFont(getFont().deriveFont(Font.BOLD, 13f));
            setMargin(new Insets(10, 16, 10, 16));
        }

        @Override public Dimension getPreferredSize() {
            Dimension d = super.getPreferredSize();
            d.height = Math.max(d.height, 36);
            return d;
        }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            ButtonModel m = getModel();

            Color bg = base;
            if (m.isPressed()) bg = press;
            else if (m.isRollover()) bg = hover;

            if (filled || bg.getAlpha() > 0) {
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, w - 1, h - 1, 10, 10);
            }

            if (border != null) {
                g2.setColor(border);
                g2.drawRoundRect(0, 0, w - 1, h - 1, 10, 10);
            }

            FontMetrics fm = g2.getFontMetrics();
            int tx = (w - fm.stringWidth(getText())) / 2;
            int ty = (h - fm.getHeight()) / 2 + fm.getAscent();
            g2.setColor(text);
            g2.drawString(getText(), tx, ty);

            g2.dispose();
        }
    }
}
