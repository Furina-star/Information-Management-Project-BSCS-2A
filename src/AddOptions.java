/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author domin
 */
public class AddOptions extends javax.swing.JDialog {

    // Theme colors aligned with modern dialogs
    private static final Color BG = new Color(0x2C3E50);
    private static final Color BG2 = new Color(0x32475D);
    private static final Color BORDER = new Color(0x223040);
    private static final Color TEXT = new Color(0xE6EDF3);
    private static final Color SUBTEXT = new Color(0xB8C3CE);
    private static final Color ACCENT_BLUE = new Color(0x3B82F6);
    
    private Point dragOffset;

    /**
     * Creates new form AddOptions
     */
    public AddOptions(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    AddOptions() {
       initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * Modern dialog with undecorated, rounded design.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        // Modern undecorated dialog setup
        setModal(true);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0)); // rounded corners support

        JPanel shell = new RoundedPanel(16, BG, BORDER);
        shell.setLayout(new BorderLayout());
        shell.setBorder(new EmptyBorder(16, 16, 16, 16));

        // Header (draggable)
        JPanel header = new RoundedPanel(12, BG2, null);
        header.setLayout(new BorderLayout());
        header.setBorder(new EmptyBorder(12, 12, 12, 12));
        
        JLabel title = new JLabel("Add Options");
        title.setForeground(TEXT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        title.setHorizontalAlignment(SwingConstants.LEFT);
        header.add(title, BorderLayout.WEST);

        MouseAdapter dragger = new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                dragOffset = e.getPoint();
                SwingUtilities.convertPointToScreen(dragOffset, header);
                Point wLoc = getLocationOnScreen();
                dragOffset.translate(-wLoc.x, -wLoc.y);
            }
            @Override public void mouseDragged(MouseEvent e) {
                Point p = e.getPoint();
                SwingUtilities.convertPointToScreen(p, header);
                setLocation(p.x - dragOffset.x, p.y - dragOffset.y);
            }
        };
        header.addMouseListener(dragger);
        header.addMouseMotionListener(dragger);

        // Content
        JPanel content = new JPanel(new GridBagLayout());
        content.setOpaque(false);
        content.setBorder(new EmptyBorder(16, 16, 0, 16));
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0; gc.gridy = 0;
        gc.weightx = 1;
        gc.insets = new Insets(0, 0, 10, 0);
        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.HORIZONTAL;

        // Description header
        JLabel descHeader = new JLabel("Please choose the following:");
        descHeader.setForeground(TEXT);
        descHeader.setFont(descHeader.getFont().deriveFont(Font.BOLD, 18f));
        descHeader.setHorizontalAlignment(SwingConstants.CENTER);
        descHeader.setBorder(new EmptyBorder(0, 0, 16, 0));
        content.add(descHeader, gc);

        // Button setup with consistent spacing
        gc.gridy++;
        gc.insets = new Insets(8, 0, 8, 0);
        
        // Add Student button
        Custom_Components.RoundedButton addStudentBtn = new Custom_Components.RoundedButton("Add Student");
        addStudentBtn.setBackground(new Color(0, 153, 153));
        addStudentBtn.setFont(new Font("Montserrat", Font.PLAIN, 18));
        addStudentBtn.setPreferredSize(new Dimension(250, 40));
        addStudentBtn.addActionListener(this::roundedButton1ActionPerformed);
        content.add(addStudentBtn, gc);

        // Add Subject button
        gc.gridy++;
        Custom_Components.RoundedButton addSubjectBtn = new Custom_Components.RoundedButton("Add Subject");
        addSubjectBtn.setBackground(new Color(0, 153, 153));
        addSubjectBtn.setFont(new Font("Montserrat", Font.PLAIN, 18));
        addSubjectBtn.setPreferredSize(new Dimension(250, 40));
        addSubjectBtn.addActionListener(this::AddSubActionPerformed);
        content.add(addSubjectBtn, gc);

        // Add Assessment button
        gc.gridy++;
        Custom_Components.RoundedButton addAssessmentBtn = new Custom_Components.RoundedButton("Add Assessment");
        addAssessmentBtn.setBackground(new Color(0, 153, 153));
        addAssessmentBtn.setFont(new Font("Montserrat", Font.PLAIN, 18));
        addAssessmentBtn.setPreferredSize(new Dimension(250, 40));
        addAssessmentBtn.addActionListener(this::AddAssActionPerformed);
        content.add(addAssessmentBtn, gc);

        // Add Assessment Results button
        gc.gridy++;
        Custom_Components.RoundedButton addResultsBtn = new Custom_Components.RoundedButton("Add Assessment Results");
        addResultsBtn.setBackground(new Color(0, 153, 153));
        addResultsBtn.setFont(new Font("Montserrat", Font.PLAIN, 18));
        addResultsBtn.setPreferredSize(new Dimension(250, 40));
        addResultsBtn.addActionListener(this::roundedButton4ActionPerformed);
        content.add(addResultsBtn, gc);

        // Actions panel (Back button)
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        actions.setOpaque(false);
        
        Custom_Components.RoundedButton backBtn = new Custom_Components.RoundedButton("Back");
        backBtn.setBackground(new Color(0x6C757D)); // Gray color for back button
        backBtn.setFont(new Font("Montserrat", Font.PLAIN, 14));
        backBtn.setPreferredSize(new Dimension(100, 35));
        backBtn.addActionListener(this::BackActionPerformed);
        actions.add(backBtn);

        // Layout assembly
        shell.add(header, BorderLayout.NORTH);
        shell.add(content, BorderLayout.CENTER);
        
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        bottom.setBorder(new EmptyBorder(18, 0, 0, 0));
        bottom.add(actions, BorderLayout.CENTER);
        shell.add(bottom, BorderLayout.SOUTH);

        // Window setup
        setContentPane(shell);
        pack();
        setSize(new Dimension(Math.max(getWidth(), 400), Math.max(getHeight(), 450)));
        setLocationRelativeTo(getParent());

        // Keys
        getRootPane().registerKeyboardAction(e -> backBtn.doClick(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void roundedButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton1ActionPerformed
     AddStudMainDialog add = new AddStudMainDialog();
     add.setVisible(rootPaneCheckingEnabled);
            add.setLocationRelativeTo(null);
                add.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_roundedButton1ActionPerformed

    private void AddSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSubActionPerformed
     AddSubject adde = new AddSubject();
     adde.setVisible(rootPaneCheckingEnabled);
     adde.setLocationRelativeTo(null);
                adde.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_AddSubActionPerformed

    private void AddAssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAssActionPerformed
        AddAssessment yes = new AddAssessment();
        yes.setVisible(rootPaneCheckingEnabled);
             yes.setLocationRelativeTo(null);
                yes.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_AddAssActionPerformed

    private void roundedButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton4ActionPerformed
AddResults wow = new AddResults();
wow.setVisible(rootPaneCheckingEnabled);
     wow.setLocationRelativeTo(null);
                wow.setAlwaysOnTop(rootPaneCheckingEnabled);
                    this.dispose();
    }//GEN-LAST:event_roundedButton4ActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
      dispose();
    }//GEN-LAST:event_BackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddOptions dialog = new AddOptions(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // RoundedPanel helper class for modern design
    private static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color fill;
        private final Color border;

        RoundedPanel(int radius, Color fill, Color border) {
            this.radius = radius;
            this.fill = fill;
            this.border = border;
            setOpaque(false);
        }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, w - 1, h - 1, radius, radius);
            if (border != null) {
                g2.setColor(border);
                g2.drawRoundRect(0, 0, w - 1, h - 1, radius, radius);
            }
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
