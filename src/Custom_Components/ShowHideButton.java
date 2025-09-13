package Custom_Components;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShowHideButton extends JButton {

    private Icon showIcon;
    private Icon hideIcon;

    private boolean passwordVisible = false; // Tracks the current state
    private float scale = 1.0f;              // Controls the animation scale

    public ShowHideButton() {
        super();
        // ### Make the button completely transparent ###
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        // ### Add the "press down" animation ###
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Shrink the icon when pressed
                scale = 0.9f;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Return to original size when released
                scale = 1.0f;
                repaint();
            }
        });

        // ### Add the core show/hide logic ###
        addActionListener((ActionEvent e) -> {
            // Toggle the state
            passwordVisible = !passwordVisible;
            // Update the icon based on the new state
            if (passwordVisible) {
                setIcon(showIcon);
            } else {
                setIcon(hideIcon);
            }
        });
    }

    // These getters and setters create properties in the NetBeans designer
    public void setShowIcon(Icon icon) {
        this.showIcon = icon;
    }

    public Icon getShowIcon() {
        return showIcon;
    }

    public void setHideIcon(Icon icon) {
        this.hideIcon = icon;
        // Set the initial icon to the "hide" icon
        setIcon(this.hideIcon);
    }

    public Icon getHideIcon() {
        return hideIcon;
    }
    
    // This is a helper method so your main code can ask the button its state
    public boolean isPasswordVisible() {
        return passwordVisible;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getIcon() == null) {
            return; // Don't draw anything if there's no icon
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        ImageIcon currentIcon = (ImageIcon) getIcon();
        int iconWidth = currentIcon.getIconWidth();
        int iconHeight = currentIcon.getIconHeight();

        // Calculate the new size and position based on the animation scale
        int scaledWidth = (int) (iconWidth * scale);
        int scaledHeight = (int) (iconHeight * scale);
        int x = (getWidth() - scaledWidth) / 2;
        int y = (getHeight() - scaledHeight) / 2;

        // Draw the icon with the calculated scale
        g2.drawImage(currentIcon.getImage(), x, y, scaledWidth, scaledHeight, null);

        g2.dispose();
    }
}