/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom_Components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

/**
 *
 * @author bello
 */
public class BetterRoundPanel extends JPanel {

    // Properties to control the radius of each corner
    private int topLeftRadius = 0;
    private int topRightRadius = 0;
    private int bottomLeftRadius = 0;
    private int bottomRightRadius = 0;

    public BetterRoundPanel() {
        setOpaque(false); // We will paint our own background
    }

    // --- Getters and Setters for the corner properties ---
    // These will appear in the NetBeans designer
    public int getTopLeftRadius() {
        return topLeftRadius;
    }
    public void setTopLeftRadius(int topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
        repaint();
    }
    public int getTopRightRadius() {
        return topRightRadius;
    }
    public void setTopRightRadius(int topRightRadius) {
        this.topRightRadius = topRightRadius;
        repaint();
    }
    public int getBottomLeftRadius() {
        return bottomLeftRadius;
    }
    public void setBottomLeftRadius(int bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
        repaint();
    }
    public int getBottomRightRadius() {
        return bottomRightRadius;
    }
    public void setBottomRightRadius(int bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        
        // Create a path with rounded corners
        Path2D.Float path = new Path2D.Float();
        path.moveTo(topLeftRadius, 0);
        path.lineTo(width - topRightRadius, 0);
        path.quadTo(width, 0, width, topRightRadius);
        path.lineTo(width, height - bottomRightRadius);
        path.quadTo(width, height, width - bottomRightRadius, height);
        path.lineTo(bottomLeftRadius, height);
        path.quadTo(0, height, 0, height - bottomLeftRadius);
        path.lineTo(0, topLeftRadius);
        path.quadTo(0, 0, topLeftRadius, 0);
        path.closePath();

        // Fill the background
        g2.setColor(getBackground());
        g2.fill(path);
        
        // This is important! It clips the child components to the rounded shape.
        g2.setClip(path);
        
        // Let the superclass paint the child components.
        super.paintComponent(g2);
        
        g2.dispose();
    }
}

