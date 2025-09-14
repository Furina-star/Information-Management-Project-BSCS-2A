/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom_Components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.Timer;

public class PressedDownAnimButton extends JButton {

    private int cornerRadius = 20;

    // "Pressed down" animation fields
    private final int maxDepth = 6;          // Maximum vertical press offset (px)
    private int currentOffset = 0;           // Current vertical offset (px)
    private Timer animTimer;
    private boolean animatingDown = false;   // Direction of the animation

    // Visual settings
    private final Color hoverOverlay = new Color(30, 144, 255, 90); // Blue highlight with alpha
    private final int animStep = 1;          // Pixels per frame
    private final int animDelayMs = 10;      // ~100 FPS for smoothness

    public PressedDownAnimButton(String text) {
        super(text);
        commonSetup();
    }

    public PressedDownAnimButton() {
        super();
        commonSetup();
    }

    private void commonSetup() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setRolloverEnabled(true);

        animTimer = new Timer(animDelayMs, e -> {
            if (animatingDown) {
                if (currentOffset < maxDepth) {
                    currentOffset += animStep;
                    if (currentOffset > maxDepth) currentOffset = maxDepth;
                } else {
                    animTimer.stop();
                }
            } else {
                if (currentOffset > 0) {
                    currentOffset -= animStep;
                    if (currentOffset < 0) currentOffset = 0;
                } else {
                    animTimer.stop();
                }
            }
            repaint();
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startAnimDown();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                startAnimUp();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // If cursor leaves while pressed, still animate up
                if (!getModel().isArmed()) {
                    startAnimUp();
                }
            }
        });
    }

    private void startAnimDown() {
        animatingDown = true;
        if (!animTimer.isRunning()) animTimer.start();
    }

    private void startAnimUp() {
        animatingDown = false;
        if (!animTimer.isRunning()) animTimer.start();
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
        int w = getWidth();
        int h = getHeight();

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Colors
        Color base = getBackground() != null ? getBackground() : new Color(0x2C3E50);

        // CLEAN LOOK: no external shadow or outline below the button.
        // Draw the face translated by currentOffset (pressed-down effect)
        g2.translate(0, currentOffset);

        // Base face
        g2.setColor(base);
        g2.fillRoundRect(0, 0, w, h, cornerRadius, cornerRadius);

        // Hover blue highlight (no darkening/shadow below)
        if (getModel().isRollover() && !getModel().isPressed()) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, hoverOverlay.getAlpha() / 255f));
            g2.setColor(new Color(hoverOverlay.getRed(), hoverOverlay.getGreen(), hoverOverlay.getBlue()));
            g2.fillRoundRect(0, 0, w, h, cornerRadius, cornerRadius);
            g2.setComposite(AlphaComposite.SrcOver);
        }

        // Draw text/icon with same translation so it moves down too
        Graphics2D gForText = (Graphics2D) g.create();
        gForText.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gForText.translate(0, currentOffset);
        super.paintComponent(gForText);
        gForText.dispose();

        g2.dispose();
    }
}