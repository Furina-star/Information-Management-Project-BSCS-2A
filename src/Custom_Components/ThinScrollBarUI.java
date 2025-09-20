package Custom_Components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ThinScrollBarUI extends BasicScrollBarUI {
    private final Color thumb = new Color(100, 115, 140, 200);
    private final Color track = new Color(200, 200, 200, 50);
    private final Color thumbHover = new Color(70, 90, 115, 230);
    private boolean hovering = false;
    private final int THUMB_ARC = 10;
    private final int TRACK_WIDTH = 8;

    @Override
    protected void installListeners() {
        super.installListeners();
        // listen for hover
        scrollbar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { hovering = true; scrollbar.repaint(); }
            @Override
            public void mouseExited(MouseEvent e)  { hovering = false; scrollbar.repaint(); }
        });
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (!c.isEnabled() || thumbBounds.width <= 0 || thumbBounds.height <= 0) return;
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color use = hovering ? thumbHover : thumb;
        g2.setPaint(use);

        int x = thumbBounds.x + Math.max(0,(thumbBounds.width - TRACK_WIDTH) / 2);
        int y = thumbBounds.y + 2;
        int w = Math.max(6, TRACK_WIDTH);
        int h = Math.max(6, thumbBounds.height - 4);

        g2.fillRoundRect(x, y, w, h, THUMB_ARC, THUMB_ARC);
        g2.dispose();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
       Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = trackBounds.x + Math.max(0,(trackBounds.width - TRACK_WIDTH) / 2);
        int y = trackBounds.y + 2;
        int w = TRACK_WIDTH;
        int h = trackBounds.height - 4;

        g2.setPaint(track);
        g2.fillRoundRect(x, y, w, h, THUMB_ARC, THUMB_ARC);
        g2.dispose();
   
}

    // hide the buttons
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return makeZeroButton();
    }
    @Override
    protected JButton createIncreaseButton(int orientation) {
        return makeZeroButton();
    }
    private JButton makeZeroButton() {
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(0,0));
        btn.setMinimumSize(new Dimension(0,0));
        btn.setMaximumSize(new Dimension(0,0));
        btn.setFocusable(false);
        return btn;
    }

    // make the scrollbar transparent background
    @Override
    protected void installComponents() {
        super.installComponents();
        scrollbar.setOpaque(false);
    }
}