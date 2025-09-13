package Custom_Components;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.Timer;

public class RoundedButton extends JButton {

    private int cornerRadius = 20;

    // Animation fields
    private Timer animationTimer;
    private float rippleAlpha = 0.5f;   // Transparency of the ripple
    private int rippleRadius = 0;     // Current radius of the ripple
    private Point clickPoint;         // The x, y location of the mouse click

    public RoundedButton(String text) {
        super(text);
        commonSetup();
    }

    public RoundedButton() {
        super();
        commonSetup();
    }
    
    private void commonSetup() {
        // These settings create the flat, rounded look
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        // ### ANIMATION SETUP ###
        
        // 1. Create the Timer
        // It will fire every 15ms to create a smooth animation (~66 FPS)
        animationTimer = new Timer(15, e -> {
            // Increase the ripple size
            rippleRadius += 5;
            // Decrease the ripple's visibility (fade out)
            rippleAlpha -= 0.03f;
            
            // If the ripple has faded out completely, stop the animation
            if (rippleAlpha <= 0) {
                animationTimer.stop();
                rippleAlpha = 0; // Ensure it's clamped to 0
            }
            repaint();
        });

        // 2. Add a MouseListener to trigger the animation
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Stop any previous animation
                if (animationTimer.isRunning()) {
                    animationTimer.stop();
                }
                // Store the click location
                clickPoint = e.getPoint();
                // Reset animation state
                rippleRadius = 0;
                rippleAlpha = 0.5f;
                // Start the animation timer
                animationTimer.start();
            }
        });
    }

    //region Getter and Setter for cornerRadius
    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }
    //endregion
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // --- 1. Paint the button background ---
        Color color = getBackground();
        if (getModel().isPressed()) {
            color = color.darker().darker();
        } else if (getModel().isRollover()) {
            color = color.darker();
        }
        g2.setColor(color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // --- 2. Paint the ripple animation if it's running ---
        if (animationTimer.isRunning() && clickPoint != null) {
            // Use a transparent white for the ripple
            g2.setColor(new Color(255, 255, 255));
            // Set the transparency (alpha)
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, rippleAlpha));
            
            // Draw a circle centered at the click point
            g2.fillOval(
                clickPoint.x - rippleRadius, 
                clickPoint.y - rippleRadius, 
                rippleRadius * 2, 
                rippleRadius * 2
            );
        }
        
        // Reset composite to default
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // --- 3. Let the parent class paint the text ---
        super.paintComponent(g);
        
        g2.dispose();
    }
}