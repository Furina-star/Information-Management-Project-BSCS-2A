package Custom_Components;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class RoundTextField extends JTextField {

    private int cornerRadius = 15;
    private Color borderColor = new Color(153, 153, 153);
    private Icon icon;
    // ### NEW fields for placeholder animation ###
private float placeholderAlpha = 1.0f; // fully visible
private javax.swing.Timer fadeTimer;
private boolean fadingOut = false;

    // ### NEW: Properties for the placeholder ###
    private String placeholderText = "";
    private Color placeholderColor = new Color(150, 150, 150); // A medium gray

    public RoundTextField() {
        super();
        setOpaque(false);
        setBackground(new Color(255, 255, 255));
        updateBorderAndPadding();

    setOpaque(false);
    setBackground(new Color(255, 255, 255));
    updateBorderAndPadding();

    // Listen to text changes
    getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            startFadeOut();
        }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            if (getText().isEmpty()) startFadeIn();
        }
        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {}
    });
}
// ### Fade-out animation when typing ###
private void startFadeOut() {
    if (fadeTimer != null && fadeTimer.isRunning()) fadeTimer.stop();
    fadingOut = true;
    fadeTimer = new javax.swing.Timer(30, e -> {
        placeholderAlpha -= 0.1f;
        if (placeholderAlpha <= 0f) {
            placeholderAlpha = 0f;
            fadeTimer.stop();
        }
        repaint();
    });
    fadeTimer.start();
}

// ### Fade-in animation when text is cleared ###
private void startFadeIn() {
    if (fadeTimer != null && fadeTimer.isRunning()) fadeTimer.stop();
    fadingOut = false;
    fadeTimer = new javax.swing.Timer(30, e -> {
        placeholderAlpha += 0.1f;
        if (placeholderAlpha >= 1f) {
            placeholderAlpha = 1f;
            fadeTimer.stop();
        }
        repaint();
    });
    fadeTimer.start();
}

    
    
    // --- Getters and setters for ALL properties ---
    public void setIcon(Icon icon) {
        this.icon = icon;
        updateBorderAndPadding();
    }

    public Icon getIcon() {
        return icon;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }
    
    // ### NEW: Getters/Setters for placeholder, visible in NetBeans ###
    public String getPlaceholderText() {
        return placeholderText;
    }

    public void setPlaceholderText(String placeholderText) {
        this.placeholderText = placeholderText;
        repaint();
    }

    public Color getPlaceholderColor() {
        return placeholderColor;
    }

    public void setPlaceholderColor(Color placeholderColor) {
        this.placeholderColor = placeholderColor;
        repaint();
    }
    
    private void updateBorderAndPadding() {
        int leftIconPadding = 10;
        int rightPadding = 5;
        int topBottomPadding = 5;

        if (icon == null) {
            setBorder(new EmptyBorder(topBottomPadding, leftIconPadding, topBottomPadding, rightPadding));
        } else {
            int iconTextGap = 5;
            int leftInset = leftIconPadding + icon.getIconWidth() + iconTextGap;
            setBorder(new EmptyBorder(topBottomPadding, leftInset, topBottomPadding, rightPadding));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. Paint the rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        
        // 2. Let the parent class paint the text, cursor, etc.
        super.paintComponent(g);

        // 3. Paint the icon ON TOP
        if (icon != null) {
            int iconY = (getHeight() - icon.getIconHeight()) / 2;
            int iconX = 10;
            icon.paintIcon(this, g2, iconX, iconY);
        }
        
        // ### NEW: Paint the placeholder text if the field is empty ###
        if (getText().isEmpty() && placeholderText != null && !placeholderText.isEmpty()) {
            // Set placeholder color and font
            g2.setColor(placeholderColor);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            
            // Calculate where to draw it. getInsets().left gives us the correct starting X.
            int x = getInsets().left;
            FontMetrics fm = g2.getFontMetrics();
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            
            g2.drawString(placeholderText, x, y);
        }
        
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        g2.dispose();
    }
}