// File: ThinScrollDemo.java
package Custom_Components;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;

public class ThinScrollDemo extends JFrame {

    public ThinScrollDemo() {
        super("Custom ScrollPane Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Content panel with lots of items to force scrolling
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false); // show background if any
        for (int i = 1; i <= 40; i++) {
            JLabel lbl = new JLabel("Item " + i);
            lbl.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
            lbl.setOpaque(true);
            lbl.setBackground(new Color(255,255,255,20));
            content.add(lbl);
            content.add(Box.createRigidArea(new Dimension(0,6)));
        }

        // Scroll pane
        JScrollPane scroll = new JScrollPane(content,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16); // smoother scroll
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(12, Integer.MAX_VALUE));
        scroll.getVerticalScrollBar().setUI(new ThinScrollBarUI());

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scroll, BorderLayout.CENTER);

        setSize(640, 480);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Optional: set look and feel early
        SwingUtilities.invokeLater(() -> {
            new ThinScrollDemo().setVisible(true);
        });
    }
}

/**
 * ThinScrollBarUI - lightweight, rounded thumb + track, no arrow buttons.
 */

