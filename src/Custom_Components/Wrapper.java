package Custom_Components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class Wrapper extends JPanel {

    private Color bgColor;
    private Color hoverColor;
    private int radius;
    private boolean hovering = false;

    // Constructor
    public Wrapper(Color bgColor, Color hoverColor, int radius) {
        this.bgColor = bgColor;
        this.hoverColor = hoverColor;
        this.radius = radius;

        setOpaque(false); // So we can custom paint

        // Hover detection
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovering = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovering = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(hovering ? hoverColor : bgColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        g2.dispose();
        super.paintComponent(g);
    }
}
