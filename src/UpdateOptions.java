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
public class UpdateOptions extends javax.swing.JDialog {

    // Theme colors aligned with modern dialogs
    private static final Color BG = new Color(0x2C3E50);
    private static final Color BG2 = new Color(0x32475D);
    private static final Color BORDER = new Color(0x223040);
    private static final Color TEXT = new Color(0xE6EDF3);
    private static final Color SUBTEXT = new Color(0xB8C3CE);
    private static final Color ACCENT_BLUE = new Color(0x3B82F6);
    
    private Point dragOffset;

    /**
     * Creates new form UpdateOptions
     */
    public UpdateOptions(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    UpdateOptions() {
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
        
        JLabel title = new JLabel("Update Options");
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
        
        // Update Student button
        Custom_Components.RoundedButton updateStudentBtn = new Custom_Components.RoundedButton("UPDATE STUDENT");
        updateStudentBtn.setBackground(new Color(0, 153, 153));
        updateStudentBtn.setFont(new Font("Montserrat", Font.PLAIN, 16));
        updateStudentBtn.setPreferredSize(new Dimension(250, 40));
        updateStudentBtn.addActionListener(this::studActionPerformed);
        content.add(updateStudentBtn, gc);

        // Update Subject button
        gc.gridy++;
        Custom_Components.RoundedButton updateSubjectBtn = new Custom_Components.RoundedButton("UPDATE SUBJECT");
        updateSubjectBtn.setBackground(new Color(0, 153, 153));
        updateSubjectBtn.setFont(new Font("Montserrat", Font.PLAIN, 16));
        updateSubjectBtn.setPreferredSize(new Dimension(250, 40));
        updateSubjectBtn.addActionListener(this::subActionPerformed);
        content.add(updateSubjectBtn, gc);

        // Update Assessment button
        gc.gridy++;
        Custom_Components.RoundedButton updateAssessmentBtn = new Custom_Components.RoundedButton("UPDATE ASSESSMENT");
        updateAssessmentBtn.setBackground(new Color(0, 153, 153));
        updateAssessmentBtn.setFont(new Font("Montserrat", Font.PLAIN, 16));
        updateAssessmentBtn.setPreferredSize(new Dimension(250, 40));
        updateAssessmentBtn.addActionListener(this::assessActionPerformed);
        content.add(updateAssessmentBtn, gc);

        // Update Assessment Result button
        gc.gridy++;
        Custom_Components.RoundedButton updateResultBtn = new Custom_Components.RoundedButton("UPDATE ASSESSMENT RESULT");
        updateResultBtn.setBackground(new Color(0, 153, 153));
        updateResultBtn.setFont(new Font("Montserrat", Font.PLAIN, 16));
        updateResultBtn.setPreferredSize(new Dimension(250, 40));
        updateResultBtn.addActionListener(this::aserActionPerformed);
        content.add(updateResultBtn, gc);

        // Actions panel (Back and Close buttons)
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        actions.setOpaque(false);
        
        Custom_Components.RoundedButton backBtn = new Custom_Components.RoundedButton("Back");
        backBtn.setBackground(new Color(0x6C757D)); // Gray color for back button
        backBtn.setFont(new Font("Montserrat", Font.PLAIN, 14));
        backBtn.setPreferredSize(new Dimension(80, 35));
        backBtn.addActionListener(this::jButton2ActionPerformed);
        actions.add(backBtn);

        Custom_Components.RoundedButton closeBtn = new Custom_Components.RoundedButton("Close");
        closeBtn.setBackground(new Color(0xDC3545)); // Red color for close button
        closeBtn.setFont(new Font("Montserrat", Font.PLAIN, 14));
        closeBtn.setPreferredSize(new Dimension(80, 35));
        closeBtn.addActionListener(this::jButton1ActionPerformed);
        actions.add(closeBtn);

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
        setSize(new Dimension(Math.max(getWidth(), 400), Math.max(getHeight(), 470)));
        setLocationRelativeTo(getParent());

        // Keys
        getRootPane().registerKeyboardAction(e -> closeBtn.doClick(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void aserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aserActionPerformed
            UpdateAssessmentResult wih = new UpdateAssessmentResult();
        wih.setVisible(rootPaneCheckingEnabled);
           wih.setLocationRelativeTo(null);
            wih.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_aserActionPerformed

    private void assessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assessActionPerformed
             UpdateAssessment weh = new UpdateAssessment();
        weh.setVisible(rootPaneCheckingEnabled);
           weh.setLocationRelativeTo(null);
            weh.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_assessActionPerformed

    private void subActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subActionPerformed
      UpdateSubject omg = new UpdateSubject();
        omg.setVisible(rootPaneCheckingEnabled);
             omg.setLocationRelativeTo(null);
                omg.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_subActionPerformed

    private void studActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studActionPerformed
    UpdateStudMainDialog yes = new UpdateStudMainDialog();
      yes.setVisible(rootPaneCheckingEnabled);
             yes.setLocationRelativeTo(null);
                yes.setAlwaysOnTop(rootPaneCheckingEnabled);
    
    }//GEN-LAST:event_studActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
UpdateOptions wih = new UpdateOptions();
wih.setLocationRelativeTo(null); // center the dialog first
wih.setVisible(true);            // then show it
this.dispose();                  // close current window
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(UpdateOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateOptions dialog = new UpdateOptions(new javax.swing.JFrame(), true);
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
