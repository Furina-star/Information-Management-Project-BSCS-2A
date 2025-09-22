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
public class DeleteOptions extends javax.swing.JDialog {

    // Theme colors aligned with modern dialogs
    private static final Color BG = new Color(0x2C3E50);
    private static final Color BG2 = new Color(0x32475D);
    private static final Color BORDER = new Color(0x223040);
    private static final Color TEXT = new Color(0xE6EDF3);
    private static final Color SUBTEXT = new Color(0xB8C3CE);
    private static final Color ACCENT_BLUE = new Color(0x3B82F6);
    
    private Point dragOffset;

    /**
     * Creates new form DeleteOptions
     */
    public DeleteOptions(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    DeleteOptions() {
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
        
        JLabel title = new JLabel("Delete Options");
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
        JLabel descHeader = new JLabel("PLEASE CHOOSE THE FOLLOWING");
        descHeader.setForeground(TEXT);
        descHeader.setFont(descHeader.getFont().deriveFont(Font.BOLD, 18f));
        descHeader.setHorizontalAlignment(SwingConstants.CENTER);
        descHeader.setBorder(new EmptyBorder(0, 0, 16, 0));
        content.add(descHeader, gc);

        // Button setup with consistent spacing
        gc.gridy++;
        gc.insets = new Insets(8, 0, 8, 0);
        
        // Delete Student button
        Custom_Components.RoundedButton deleteStudentBtn = new Custom_Components.RoundedButton("Delete Student");
        deleteStudentBtn.setBackground(new Color(0xDC3545)); // Red color for delete buttons
        deleteStudentBtn.setFont(new Font("Montserrat", Font.PLAIN, 18));
        deleteStudentBtn.setPreferredSize(new Dimension(275, 40));
        deleteStudentBtn.addActionListener(this::DeleteStudActionPerformed);
        content.add(deleteStudentBtn, gc);

        // Delete Subject button
        gc.gridy++;
        Custom_Components.RoundedButton deleteSubjectBtn = new Custom_Components.RoundedButton("Delete Subject");
        deleteSubjectBtn.setBackground(new Color(0xDC3545));
        deleteSubjectBtn.setFont(new Font("Montserrat", Font.PLAIN, 18));
        deleteSubjectBtn.setPreferredSize(new Dimension(275, 40));
        deleteSubjectBtn.addActionListener(this::DeleteSubActionPerformed);
        content.add(deleteSubjectBtn, gc);

        // Delete Assessment button
        gc.gridy++;
        Custom_Components.RoundedButton deleteAssessmentBtn = new Custom_Components.RoundedButton("Delete Assessment");
        deleteAssessmentBtn.setBackground(new Color(0xDC3545));
        deleteAssessmentBtn.setFont(new Font("Montserrat", Font.PLAIN, 18));
        deleteAssessmentBtn.setPreferredSize(new Dimension(275, 40));
        deleteAssessmentBtn.addActionListener(this::DeleteAssActionPerformed);
        content.add(deleteAssessmentBtn, gc);

        // Delete Assessment Results button
        gc.gridy++;
        Custom_Components.RoundedButton deleteResultsBtn = new Custom_Components.RoundedButton("Delete Assessment Results");
        deleteResultsBtn.setBackground(new Color(0xDC3545));
        deleteResultsBtn.setFont(new Font("Montserrat", Font.PLAIN, 18));
        deleteResultsBtn.setPreferredSize(new Dimension(275, 40));
        deleteResultsBtn.addActionListener(this::DeleteAssRedActionPerformed);
        content.add(deleteResultsBtn, gc);

        // Actions panel (Back button)
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        actions.setOpaque(false);
        
        Custom_Components.RoundedButton backBtn = new Custom_Components.RoundedButton("Back");
        backBtn.setBackground(new Color(0x6C757D)); // Gray color for back button
        backBtn.setFont(new Font("Montserrat", Font.PLAIN, 14));
        backBtn.setPreferredSize(new Dimension(100, 35));
        backBtn.addActionListener(this::jButton2ActionPerformed);
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
        setSize(new Dimension(Math.max(getWidth(), 420), Math.max(getHeight(), 480)));
        setLocationRelativeTo(getParent());

        // Keys
        getRootPane().registerKeyboardAction(e -> backBtn.doClick(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void DeleteStudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteStudActionPerformed
        DeleteStudeMainDialog del = new DeleteStudeMainDialog();
        del.setVisible(rootPaneCheckingEnabled);
        del.setLocationRelativeTo(null);
        del.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_DeleteStudActionPerformed

    private void DeleteSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteSubActionPerformed
     DeleteSubject dele = new DeleteSubject();
     dele.setVisible(rootPaneCheckingEnabled);
     dele.setLocationRelativeTo(null);
     dele.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_DeleteSubActionPerformed

    private void DeleteAssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAssActionPerformed
     DeleteAssessment yes = new DeleteAssessment();
     yes.setVisible(rootPaneCheckingEnabled);
     yes.setLocationRelativeTo(null);
     yes.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_DeleteAssActionPerformed

    private void DeleteAssRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAssRedActionPerformed
        DeleteAssessmentResults passobemsolto = new DeleteAssessmentResults();
        passobemsolto.setVisible(rootPaneCheckingEnabled);
        passobemsolto.setLocationRelativeTo(null);
        passobemsolto.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_DeleteAssRedActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(DeleteOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DeleteOptions dialog = new DeleteOptions(new javax.swing.JFrame(), true);
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
