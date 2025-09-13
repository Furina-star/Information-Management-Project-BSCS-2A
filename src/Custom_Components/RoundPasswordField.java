package Custom_Components;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.FontMetrics;

public class RoundPasswordField extends JPasswordField {

    private int cornerRadius = 15;
    private Color borderColor = new Color(153, 153, 153);
    private Icon icon;
    private String placeholderText = "";
    private Color placeholderColor = new Color(150, 150, 150);

    public RoundPasswordField() {
        super();
        setOpaque(false);
        setBackground(new Color(255, 255, 255));
        updateBorderAndPadding();
    }
    
    // --- Getters and setters for NetBeans properties ---
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

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
        
        super.paintComponent(g);

        if (icon != null) {
            int iconY = (getHeight() - icon.getIconHeight()) / 2;
            int iconX = 10;
            icon.paintIcon(this, g2, iconX, iconY);
        }
        
        // Paint the placeholder text
        if (getPassword().length == 0 && placeholderText != null && !placeholderText.isEmpty()) {
            g2.setColor(placeholderColor);
            g2.setFont(getFont().deriveFont(Font.ITALIC));
            
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