/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Custom_Components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Styles an existing JTable to fit a modern dashboard aesthetic:
 * - Dark header with bold white text
 * - Teal selection color (matches "Export" button accent)
 * - Subtle zebra striping
 * - Hover highlight
 * - Slim rounded scrollbars
 * Apply with: TableStyler.apply(myTable);
 */
public final class TableStyler {

    // Palette tuned to your screenshot
    private static final Color ACCENT          = new Color(0x12BFA5); // teal from your buttons
    private static final Color HEADER_BG       = new Color(0x22313F); // dark slate
    private static final Color HEADER_DIVIDER  = new Color(0x1A2631);
    private static final Color TEXT_PRIMARY    = new Color(0x24313A);
    private static final Color ROW_BG          = new Color(0xF7F9FC);
    private static final Color ROW_ALT_BG      = new Color(0xEEF2F7);
    private static final Color GRID_COLOR      = new Color(0xE6EAF0);
    private static final Color HOVER_BG        = new Color(0xDDE9F0);
    private static final Color SCROLL_TRACK    = new Color(0xE6EAF0);
    private static final Color SCROLL_THUMB    = new Color(0xB9C4CF);
    private static final Color SCROLL_THUMB_RO = new Color(0xA9B4BF);

    private static final String HOVER_ROW_KEY = "TableStyler.hoverRow";

    private TableStyler() {}

    /**
     * Style the given JTable in place. Call this once after initComponents().
     */
    public static void apply(JTable table) {
        if (table == null) return;

        // Base table appearance
        table.setRowHeight(32);
        table.setFont(safeFont(table.getFont(), 13f, Font.PLAIN));
        table.setForeground(TEXT_PRIMARY);
        table.setBackground(ROW_BG);
        table.setSelectionBackground(ACCENT);
        table.setSelectionForeground(Color.WHITE);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setGridColor(GRID_COLOR);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setFillsViewportHeight(true);

        // Header styling
        JTableHeader header = table.getTableHeader();
        if (header != null) {
            header.setReorderingAllowed(false);
            header.setResizingAllowed(true);
            header.setPreferredSize(new Dimension(header.getPreferredSize().width, 36));
            header.setDefaultRenderer(new HeaderRenderer(table));
            header.putClientProperty("TableHeader.rightAlignSortArrow", Boolean.TRUE);
        }

        // Zebra striping + hover highlight for body
        AlternatingRowRenderer zebra = new AlternatingRowRenderer(SwingConstants.LEFT);
        table.setDefaultRenderer(Object.class, zebra);
        table.setDefaultRenderer(Number.class, new AlternatingRowRenderer(SwingConstants.RIGHT));
        table.setDefaultRenderer(Boolean.class, new AlternatingRowRenderer(SwingConstants.CENTER));

        // Hover support
        attachHoverBehavior(table);

        // Scroll pane tweaks (if the table is inside one)
        JScrollPane sp = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, table);
        if (sp != null) {
            sp.setBorder(new EmptyBorder(8, 8, 8, 8));
            sp.getViewport().setBackground(ROW_BG);
            sp.setBackground(ROW_BG);
            // Slim rounded scrollbars
            sp.getVerticalScrollBar().setUI(new SlimScrollBarUI());
            sp.getHorizontalScrollBar().setUI(new SlimScrollBarUI());
            sp.getVerticalScrollBar().setUnitIncrement(16);
            sp.getHorizontalScrollBar().setUnitIncrement(16);

            // Nice corner to match header bg at top-right
            JComponent corner = new JComponent(){};
            corner.setBackground(HEADER_BG);
            corner.setOpaque(true);
            sp.setCorner(JScrollPane.UPPER_RIGHT_CORNER, corner);
        }
    }

    /**
     * Right-align a specific column (e.g., numeric).
     */
    public static void alignRight(JTable table, int columnIndex) {
        TableColumnModel cm = table.getColumnModel();
        if (columnIndex < 0 || columnIndex >= cm.getColumnCount()) return;
        TableColumn col = cm.getColumn(columnIndex);
        col.setCellRenderer(new AlternatingRowRenderer(SwingConstants.RIGHT));
    }

    /**
     * Center-align a specific column.
     */
    public static void alignCenter(JTable table, int columnIndex) {
        TableColumnModel cm = table.getColumnModel();
        if (columnIndex < 0 || columnIndex >= cm.getColumnCount()) return;
        TableColumn col = cm.getColumn(columnIndex);
        col.setCellRenderer(new AlternatingRowRenderer(SwingConstants.CENTER));
    }

    // ---------- Renderers and UI ----------

    private static class HeaderRenderer extends DefaultTableCellRenderer {
        HeaderRenderer(JTable table) {
            setOpaque(true);
            setBackground(HEADER_BG);
            setForeground(Color.WHITE);
            setFont(safeFont(table.getTableHeader().getFont(), 13f, Font.BOLD));
            // Bottom divider + horizontal padding
            setBorder(BorderFactory.createCompoundBorder(
                    new MatteBorder(0, 0, 1, 0, HEADER_DIVIDER),
                    new EmptyBorder(0, 10, 0, 10)
            ));
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            super.getTableCellRendererComponent(table, value, false, false, row, column);
            setHorizontalAlignment(SwingConstants.LEFT);
            return this;
        }
    }

    private static class AlternatingRowRenderer extends DefaultTableCellRenderer {
        private final int align;

        AlternatingRowRenderer(int align) {
            this.align = align;
            setBorder(new EmptyBorder(0, 10, 0, 10));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            setHorizontalAlignment(align);

            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                // Hover row (stored in clientProperty)
                Integer hoverRow = (Integer) table.getClientProperty(HOVER_ROW_KEY);
                boolean isHover = (hoverRow != null && hoverRow == row);

                Color bg = (row % 2 == 0) ? ROW_BG : ROW_ALT_BG;
                if (isHover) bg = HOVER_BG;

                setBackground(bg);
                setForeground(TEXT_PRIMARY);
            }
            return this;
        }
    }

    private static void attachHoverBehavior(JTable table) {
        table.addMouseMotionListener(new MouseAdapter() {
            @Override public void mouseMoved(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                Object prev = table.getClientProperty(HOVER_ROW_KEY);
                if (!(prev instanceof Integer) || ((Integer) prev) != row) {
                    table.putClientProperty(HOVER_ROW_KEY, row);
                    table.repaint();
                }
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override public void mouseExited(MouseEvent e) {
                table.putClientProperty(HOVER_ROW_KEY, -1);
                table.repaint();
            }
        });
    }

    private static class SlimScrollBarUI extends BasicScrollBarUI {
        private static final int THICKNESS = 10;
        private static final int ARC = 10;

        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = SCROLL_THUMB;
            this.trackColor = SCROLL_TRACK;
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return zeroButton();
        }
        @Override
        protected JButton createIncreaseButton(int orientation) {
            return zeroButton();
        }
        private JButton zeroButton() {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(0, 0));
            b.setMinimumSize(new Dimension(0, 0));
            b.setMaximumSize(new Dimension(0, 0));
            b.setBorder(BorderFactory.createEmptyBorder());
            b.setFocusable(false);
            b.setOpaque(false);
            b.setContentAreaFilled(false);
            return b;
        }

        @Override
        protected Dimension getMinimumThumbSize() {
            return new Dimension(THICKNESS, THICKNESS);
        }
        @Override
        protected Dimension getMaximumThumbSize() {
            return new Dimension(THICKNESS, THICKNESS);
        }
        @Override
        public Dimension getPreferredSize(JComponent c) {
            if (scrollbar.getOrientation() == Adjustable.VERTICAL) {
                return new Dimension(THICKNESS, 50);
            } else {
                return new Dimension(50, THICKNESS);
            }
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
            if (!c.isEnabled() || r.width <= 0 || r.height <= 0) return;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(isThumbRollover() ? SCROLL_THUMB_RO : SCROLL_THUMB);
            g2.fillRoundRect(r.x + 2, r.y + 2, r.width - 4, r.height - 4, ARC, ARC);
            g2.dispose();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(SCROLL_TRACK);
            g2.fillRect(r.x, r.y, r.width, r.height);
            g2.dispose();
        }
    }

    // ---------- small helpers ----------

    private static Font safeFont(Font base, float size, int style) {
        if (base == null) base = UIManager.getFont("Table.font");
        if (base == null) base = new JLabel().getFont();
        return base.deriveFont(style, size);
    }
}
