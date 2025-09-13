package Custom_Components;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class HyperlinkLabel extends JLabel {

    private boolean isHovered = false;
    private Color originalColor;
    private Color underlineColor = Color.BLACK;

    public HyperlinkLabel() {
        super();
        
        // Add listeners for hover, click, and cursor changes
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                setCursor(Cursor.getDefaultCursor());
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Store the original color and change the text to blue
                originalColor = getForeground();
                setForeground(Color.BLUE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Change the text back to its original color
                if (originalColor != null) {
                    setForeground(originalColor);
                }
            }
        });
    }

    // Getter/Setter for the underline color property in NetBeans
    public Color getUnderlineColor() {
        return underlineColor;
    }

    public void setUnderlineColor(Color underlineColor) {
        this.underlineColor = underlineColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Let the parent JLabel class handle painting the text first.
        // This ensures it is not bold and respects alignment.
        super.paintComponent(g);

        // Draw underline only when the mouse is hovering over the label
        if (isHovered) {
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(getText());
            
            // Calculate starting x-position based on horizontal alignment
            int x;
            switch (getHorizontalAlignment()) {
                case SwingConstants.CENTER:
                    x = (getWidth() - textWidth) / 2;
                    break;
                case SwingConstants.RIGHT:
                    x = getWidth() - textWidth;
                    break;
                default: // LEFT
                    x = 0;
                    break;
            }

            // Calculate y-position for the underline
            int y = fm.getAscent() + 2;
            
            g.setColor(underlineColor);
            g.drawLine(x, y, x + textWidth, y);
        }
    }
}