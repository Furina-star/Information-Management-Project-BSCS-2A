/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

public class AboutUsDialog extends JDialog {

    public enum Result { CLOSED }

    // Theme (aligned with your dashboard)
    private static final Color BG = new Color(0x2C3E50);
    private static final Color BG2 = new Color(0x32475D);
    private static final Color BORDER = new Color(0x223040);
    private static final Color TEXT = new Color(0xE6EDF3);
    private static final Color SUBTEXT = new Color(0xB8C3CE);
    private static final Color ACCENT_BLUE = new Color(0x3B82F6);

    private Point dragOffset;
    private Result result = Result.CLOSED;

    public AboutUsDialog(Window parent, String appName, String description) {
        super(parent);
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // rounded corners support

        JPanel shell = new RoundedPanel(16, BG, BORDER);
        shell.setLayout(new BorderLayout());
        shell.setBorder(new EmptyBorder(16, 16, 16, 16));

        // Header (draggable)
        JPanel header = new RoundedPanel(12, BG2, null);
        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(12, 12, 12, 12));
        String titleText = (appName == null || appName.isEmpty()) ? "About" : "About — " + appName;
        JLabel title = new JLabel(titleText);
        title.setForeground(TEXT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        title.setHorizontalAlignment(SwingConstants.LEFT);
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

        // Content (precise LEFT alignment everywhere)
        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(16, 16, 0, 16));
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0; gc.gridy = 0;
        gc.weightx = 1;
        gc.insets = new Insets(0, 0, 10, 0);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.HORIZONTAL;

        // Description header (LEFT)
        JLabel descHeader = sectionHeader("Description of the System");
        content.add(descHeader, gc);

        // Description text (JUSTIFIED + LEFT)
        gc.gridy++;
        JLabel descText = wrapHtmlLabel(
                (description == null || description.isEmpty())
                        ? "Student Information System is a lightweight desktop tool for managing student records, subjects, and assessments. It streamlines routine tasks with a clean, modern interface and built‑in confirmations to prevent mistakes. View, add, update, and export data in just a few clicks."
                        : description,
                560,
                true
        );
        content.add(descText, gc);

        // Contacts header (LEFT)
        gc.gridy++;
        JLabel contactsHeader = sectionHeader("Contacts");
        contactsHeader.setBorder(new EmptyBorder(14, 0, 2, 0));
        content.add(contactsHeader, gc);

        // Contacts panel using GridBag (ensures full-width, LEFT)
        gc.gridy++;
        JPanel contactsPanel = new JPanel(new GridBagLayout());
        contactsPanel.setOpaque(false);
        contactsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        GridBagConstraints cc = new GridBagConstraints();
        cc.gridx = 0; cc.gridy = 0;
        cc.weightx = 1;
        cc.fill = GridBagConstraints.HORIZONTAL;
        cc.anchor = GridBagConstraints.WEST;
        cc.insets = new Insets(0, 0, 12, 0);

        JPanel p1 = contactBlock(
                "Bello, Michael Lorenz",
                "bellomichael964@gmail.com",
                "+639283831883"
        );
        forceFullWidth(p1);
        contactsPanel.add(p1, cc);

        cc.gridy++;
        JPanel p2 = contactBlock(
                "Dominguez, Laurenc Jhon",
                "dominguez@gmail.com",
                "+639917319392"
        );
        forceFullWidth(p2);
        contactsPanel.add(p2, cc);

        content.add(contactsPanel, gc);

        // Actions
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actions.setOpaque(false);
        JButton close = primaryButton("Close", ACCENT_BLUE);
        close.addActionListener(e -> {
            result = Result.CLOSED;
            dispose();
        });
        actions.add(close);

        // Layout assembly
        shell.add(header, BorderLayout.NORTH);
        shell.add(content, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        bottom.setBorder(new EmptyBorder(18, 0, 0, 0));
        bottom.add(actions, BorderLayout.EAST);
        shell.add(bottom, BorderLayout.SOUTH);

        // Size tuned to fit content; adjust if you add more lines
        setContentPane(shell);
        pack();
        setSize(new Dimension(Math.max(getWidth(), 640), Math.max(getHeight(), 470)));
        setLocationRelativeTo(parent);

        // Keys
        getRootPane().registerKeyboardAction(e -> close.doClick(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        getRootPane().setDefaultButton(close);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                result = Result.CLOSED;
                dispose();
            }
        });
    }

    public static Result showDialog(Window parent, String appName, String description) {
        AboutUsDialog d = new AboutUsDialog(parent, appName, description);
        d.setVisible(true);
        return d.result;
    }
    public static Result showDialog(Window parent, String appName) {
        return showDialog(parent, appName,
                "Student Information System is a lightweight desktop tool for managing student records, subjects, and assessments. It streamlines routine tasks with a clean, modern interface and built‑in confirmations to prevent mistakes. View, add, update, and export data in just a few clicks.");
    }
    public static Result showDialog(Window parent) {
        return showDialog(parent, null);
    }

    // ---------- UI helpers ----------

    private static JLabel sectionHeader(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(TEXT);
        l.setFont(l.getFont().deriveFont(Font.BOLD, 14f));
        l.setHorizontalAlignment(SwingConstants.LEFT);
        return l;
    }

    // HTML JLabel with optional justification and nice line-height.
    private static JLabel wrapHtmlLabel(String text, int widthPx, boolean justify) {
        String style = String.format(
                "width:%dpx; color:#B8C3CE; line-height:1.5; font-size:12.8px; margin:0; %s",
                widthPx,
                justify ? "text-align:justify;" : "text-align:left;"
        );
        String html = String.format(
                "<html><div style='%s'>%s</div></html>",
                style,
                escapeHtml(text).replace("\n", "<br>")
        );
        JLabel l = new JLabel(html);
        l.setVerticalAlignment(SwingConstants.TOP);
        l.setHorizontalAlignment(SwingConstants.LEFT);
        return l;
    }

    private static String escapeHtml(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private static void forceFullWidth(JComponent c) {
        c.setAlignmentX(Component.LEFT_ALIGNMENT);
        c.setMaximumSize(new Dimension(Integer.MAX_VALUE, c.getPreferredSize().height));
    }

    private JPanel contactBlock(String name, String email, String phone) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(2, 0, 2, 0));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0, 0, 2, 0);

        JLabel nameL = new JLabel(name);
        nameL.setForeground(TEXT);
        nameL.setFont(nameL.getFont().deriveFont(Font.BOLD, 13.5f));
        nameL.setHorizontalAlignment(SwingConstants.LEFT);
        p.add(nameL, c);

        c.gridy++;
        JLabel emailL = linkLabel(email);
        emailL.setHorizontalAlignment(SwingConstants.LEFT);
        p.add(emailL, c);

        c.gridy++;
        JLabel phoneL = linkLabel(phone);
        phoneL.setHorizontalAlignment(SwingConstants.LEFT);
        p.add(phoneL, c);

        // Make the panel claim full width in its parent GridBag
        GridBagConstraints filler = new GridBagConstraints();
        filler.gridx = 1; filler.gridy = 0; filler.weightx = 1;
        filler.fill = GridBagConstraints.HORIZONTAL;
        p.add(Box.createHorizontalGlue(), filler);

        return p;
    }

    private JLabel linkLabel(String value) {
        JLabel l = new JLabel(value);
        l.setForeground(ACCENT_BLUE);
        l.setFont(l.getFont().deriveFont(13f));
        l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        l.setToolTipText("Click to copy");
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        l.addMouseListener(new MouseAdapter() {
            Color normal = ACCENT_BLUE;
            Color hover = ACCENT_BLUE.darker();
            @Override public void mouseEntered(MouseEvent e) { l.setForeground(hover); }
            @Override public void mouseExited(MouseEvent e) { l.setForeground(normal); }
            @Override public void mouseClicked(MouseEvent e) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(value), null);
                l.setText("Copied: " + value);
                Timer t = new Timer(1200, ev -> l.setText(value));
                t.setRepeats(false);
                t.start();
            }
        });
        return l;
    }

    private static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color fill;
        private final Color border;
        RoundedPanel(int radius, Color fill, Color border) {
            this.radius = radius; this.fill = fill; this.border = border;
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
            g2.setColor(TEXT);
            g2.drawString(getText(), tx, ty);
            g2.dispose();
        }
    }

    private static JButton primaryButton(String text, Color base) {
        return new FlatButton(text, base, base.darker(), base.darker().darker(), TEXT, null, true);
    }
}