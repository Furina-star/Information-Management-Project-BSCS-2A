package Custom_Components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ProfilePanel extends JLabel {
    private Image image;

    public ProfilePanel() {
        setPreferredSize(new Dimension(120, 120)); // adjust size
    }

    public void setImage(Image img) {
        this.image = img;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            int diameter = Math.min(getWidth(), getHeight());
            BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = masked.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw circular clip
            g2.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, diameter, diameter));
            g2.drawImage(image, 0, 0, diameter, diameter, null);
            g2.dispose();

            g.drawImage(masked, 0, 0, null);
        } else {
            super.paintComponent(g);
        }
    }
}
