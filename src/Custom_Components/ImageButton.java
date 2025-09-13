package Custom_Components;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {

    // Animation variable: controls the size of the image
    private float scale = 1.0f;

    public ImageButton() {
        super();
        commonSetup();
    }

    public ImageButton(Icon icon) {
        super(icon);
        commonSetup();
    }

    private void commonSetup() {
        // ### Make the button completely transparent ###
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        // ### Add the press/release animation ###
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // When pressed, shrink the image to 90% of its size
                scale = 0.9f;
                repaint(); // Redraw the button
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // When released, return the image to its original size
                scale = 1.0f;
                repaint(); // Redraw the button
            }
        });
    }
    
    // The setIcon method is already part of JButton, so we don't need a new one.
    // NetBeans will use it automatically for the "icon" property.

    @Override
    protected void paintComponent(Graphics g) {
        // We don't call super.paintComponent(g) because we don't want
        // the default button painting behavior. We want full control.

        if (getIcon() == null) {
            return; // Don't do anything if there's no icon
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        ImageIcon icon = (ImageIcon) getIcon();
        int iconWidth = icon.getIconWidth();
        int iconHeight = icon.getIconHeight();

        // Calculate the new size and position based on the scale
        int scaledWidth = (int) (iconWidth * scale);
        int scaledHeight = (int) (iconHeight * scale);
        int x = (getWidth() - scaledWidth) / 2;
        int y = (getHeight() - scaledHeight) / 2;

        // Draw the scaled image
        g2.drawImage(icon.getImage(), x, y, scaledWidth, scaledHeight, null);

        g2.dispose();
    }
}