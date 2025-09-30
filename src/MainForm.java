import Export_Components.ExportUtils;
import Export_Components.XlsxExportUtils;
import java.sql.Types;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.awt.Dimension;
import java.awt.Toolkit;
import Custom_Components.ThinScrollDemo;
import Custom_Components.ThinScrollBarUI;
import Custom_Components.RoundedPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import Custom_Components.TextPrompt;
import DataBaseConnection.Connector;
import com.mysql.cj.callback.UsernameCallback;
//import com.mysql.cj.xdevapi.Statement;
//import com.sun.jdi.connect.spi.Connection;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
//import javax.lang.model.util.Types;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import Custom_Components.TableStyler;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import DataBaseConnection.Connector;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import Custom_Components.RoundedPanel;
import com.mysql.cj.protocol.Resultset;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author bello and laurence yeaaaaaaaaaaaaaa
 */
public class MainForm extends javax.swing.JFrame {

    // Main constructor for default (no username)
    public MainForm() {
        this(null); // call the other constructor with null username
    }

    // Constructor with username
    public MainForm(String username) {
        initComponents(); // generated UI components
        
        //Table Design
        TableStyler.apply(subjectTable);
        TableStyler.apply(AssessmentTable);
        TableStyler.apply(Result);
        TableStyler.apply(StudentTable);
        
        
            BodyPanel.setVisible(false);
            String[] columnss = {"Result ID", "Student", "Assessment", "Score", "Date Taken", "Rating"};
                resultModel = new DefaultTableModel(columnss, 0);
                Result.setModel(resultModel);

            String[] columns = {"Assessment ID", "Subject", "Title", "Type", "Max Score", "Date Given"};
                assessmentModel = new DefaultTableModel(columns, 0);
                AssessmentTable.setModel(assessmentModel);
                        //Set greeting if username is provided
                        //note saka lang to gagana if kaka login ni user
                        if (username != null && !username.isEmpty()) {
                            GreetLabel.setText("Hello " + username + "!");
                        } else {
                            GreetLabel.setText("Hello!");
                        }

                        //Optional styling for greeting
                        GreetLabel.setFont(new Font("Segoe UI", Font.BOLD, 19));
                            GreetLabel.setForeground(Color.WHITE);
                                GreetLabel.setPreferredSize(new Dimension(250, 30));

                        // Layout fixes
                        if (GreetLabel.getParent() != null) {
                                GreetLabel.getParent().revalidate();
                                    GreetLabel.getParent().repaint();
                        }

                        // Scroll panel tweaks
                        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
                            SidePanelHolder.setBorder(BorderFactory.createEmptyBorder());
                                jScrollPane1.getVerticalScrollBar().setUI(new ThinScrollBarUI());
                                    jScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));

                        TableScrollPane1.setBorder(BorderFactory.createEmptyBorder());
                            TableScrollPane1.setBorder(BorderFactory.createEmptyBorder());
                                TableScrollPane1.getVerticalScrollBar().setUI(new ThinScrollBarUI());
                                    TableScrollPane1.getHorizontalScrollBar().setUI(new ThinScrollBarUI());
                                        TableScrollPane1.getVerticalScrollBar().setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
                        //load stats
                        dashboardStats();


                        //Search bar placeholder
                        new TextPrompt("Search here..", Searchbar);
                            loadStudentsFromDB();
                                loadSubjectsFromDB();
                                    loadAssessments();
                                        loadAssessmentResults();
                        //Final frame settings
                        pack(); // fit all components
                            setLocationRelativeTo(null); // center on screen
                                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Optional helper if you want a brief success toast
    private void showAutoClosingInfo(String message, int millis) {
        javax.swing.JOptionPane pane = new javax.swing.JOptionPane(
                message, javax.swing.JOptionPane.INFORMATION_MESSAGE);
        javax.swing.JDialog dialog = pane.createDialog(this, "Export");
        dialog.setModal(false);
        dialog.setDefaultCloseOperation(javax.swing.JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        new javax.swing.Timer(millis, e -> dialog.dispose()) {{ setRepeats(false); }}.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BgPanel = new javax.swing.JPanel();
        SidePanelHolder = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SidebarPanel = new Custom_Components.BetterRoundPanel();
        ViewLabel = new javax.swing.JLabel();
        VStudentButton = new Custom_Components.PressedDownAnimButton();
        VSubjectButton = new Custom_Components.PressedDownAnimButton();
        VAssessmentButton = new Custom_Components.PressedDownAnimButton();
        CustomizeLabel = new javax.swing.JLabel();
        CDeleteButton = new Custom_Components.PressedDownAnimButton();
        CUpdateButton = new Custom_Components.PressedDownAnimButton();
        CAddButton = new Custom_Components.PressedDownAnimButton();
        UtilitiesLabel = new javax.swing.JLabel();
        UAboutButton = new Custom_Components.PressedDownAnimButton();
        UCloseButton = new Custom_Components.PressedDownAnimButton();
        UBackButton = new Custom_Components.PressedDownAnimButton();
        UExportButton = new Custom_Components.PressedDownAnimButton();
        VHomeButton = new Custom_Components.PressedDownAnimButton();
        VAssessmentButton1 = new Custom_Components.PressedDownAnimButton();
        jLabel1 = new javax.swing.JLabel();
        UpperNavigationPanel = new javax.swing.JPanel();
        GreetLabel = new javax.swing.JLabel();
        Searchbar = new Custom_Components.SearchBar();
        GreetLabel1 = new javax.swing.JLabel();
        Refresher = new Custom_Components.RoundedButton();
        ExportAllButton = new Custom_Components.RoundedButton();
        BodyPanel = new javax.swing.JPanel();
        Home = new javax.swing.JPanel();
        jPanel1 = new Custom_Components.Wrapper(
            new Color(27,39,50),
            new Color(30,45,50),
            20
        )
        ;
        jLabel2 = new javax.swing.JLabel();
        ValueDisplayStud = new javax.swing.JLabel();
        TotalAssessment = new Custom_Components.Wrapper(
            new Color(27,39,50),
            new Color(30,45,50),
            20
        )
        ;
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ValueDisplaySUB = new javax.swing.JLabel();
        jPanel3 = new Custom_Components.Wrapper(
            new Color(27,39,50),
            new Color(30, 45, 50),
            20
        )
        ;
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        GradeDisplay = new javax.swing.JLabel();
        jPanel5 = new Custom_Components.Wrapper(
            new Color(27,39,50),
            new Color(30, 45, 50),
            20
        )
        ;
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ValueDisplayASS = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        StudentsPanel = new javax.swing.JPanel();
        TableScrollPane1 = new javax.swing.JScrollPane();
        StudentTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        profilePicLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblContact = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblYear = new javax.swing.JLabel();
        lblSection = new javax.swing.JLabel();
        lblProgram = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        Subjects = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        subjectTable = new javax.swing.JTable();
        AssignSub = new Custom_Components.RoundedButton();
        UnAssign = new Custom_Components.RoundedButton();
        DeleteSub = new Custom_Components.RoundedButton();
        jLabel19 = new javax.swing.JLabel();
        ExportButton = new Custom_Components.RoundedButton();
        AssignSub1 = new Custom_Components.RoundedButton();
        AssignSub2 = new Custom_Components.RoundedButton();
        Assessment = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        AssessmentTable = new javax.swing.JTable();
        creation = new Custom_Components.RoundedButton();
        deleteass = new Custom_Components.RoundedButton();
        ExportAssessmentButton = new Custom_Components.RoundedButton();
        jLabel20 = new javax.swing.JLabel();
        UpdateAssessmentButton = new Custom_Components.RoundedButton();
        AssessmentResult = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Result = new javax.swing.JTable();
        addorcreatetable = new Custom_Components.RoundedButton();
        UpdateResultsButton = new Custom_Components.RoundedButton();
        dEL = new Custom_Components.RoundedButton();
        ExportResultButton = new Custom_Components.RoundedButton();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BgPanel.setBackground(new java.awt.Color(48, 62, 78));
        BgPanel.setForeground(new java.awt.Color(255, 204, 204));
        BgPanel.setMaximumSize(new java.awt.Dimension(2560, 1440));
        BgPanel.setMinimumSize(new java.awt.Dimension(1280, 720));
        BgPanel.setPreferredSize(new java.awt.Dimension(1280, 720));

        SidePanelHolder.setBackground(new java.awt.Color(27, 39, 50));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(225, 720));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(225, 720));

        SidebarPanel.setBackground(new java.awt.Color(27, 39, 50));

        ViewLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ViewLabel.setForeground(new java.awt.Color(255, 255, 255));
        ViewLabel.setText("View");

        VStudentButton.setBackground(new java.awt.Color(44, 62, 80));
        VStudentButton.setForeground(new java.awt.Color(255, 255, 255));
        VStudentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image Folder/girl.png"))); // NOI18N
        VStudentButton.setText("Students\n");
        VStudentButton.setCornerRadius(15);
        VStudentButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        VStudentButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        VStudentButton.setIconTextGap(30);
        VStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VStudentButtonActionPerformed(evt);
            }
        });

        VSubjectButton.setBackground(new java.awt.Color(44, 62, 80));
        VSubjectButton.setForeground(new java.awt.Color(255, 255, 255));
        VSubjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image Folder/bookmark.png"))); // NOI18N
        VSubjectButton.setText("Subjects");
        VSubjectButton.setCornerRadius(15);
        VSubjectButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        VSubjectButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        VSubjectButton.setIconTextGap(30);
        VSubjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VSubjectButtonActionPerformed(evt);
            }
        });

        VAssessmentButton.setBackground(new java.awt.Color(44, 62, 80));
        VAssessmentButton.setForeground(new java.awt.Color(255, 255, 255));
        VAssessmentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image Folder/test.png"))); // NOI18N
        VAssessmentButton.setText("Assessment");
        VAssessmentButton.setCornerRadius(15);
        VAssessmentButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        VAssessmentButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        VAssessmentButton.setIconTextGap(30);
        VAssessmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VAssessmentButtonActionPerformed(evt);
            }
        });

        CustomizeLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        CustomizeLabel.setForeground(new java.awt.Color(255, 255, 255));
        CustomizeLabel.setText("Customize");

        CDeleteButton.setBackground(new java.awt.Color(44, 62, 80));
        CDeleteButton.setForeground(new java.awt.Color(255, 255, 255));
        CDeleteButton.setText("Delete");
        CDeleteButton.setCornerRadius(15);
        CDeleteButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CDeleteButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CDeleteButton.setIconTextGap(30);
        CDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CDeleteButtonActionPerformed(evt);
            }
        });

        CUpdateButton.setBackground(new java.awt.Color(44, 62, 80));
        CUpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        CUpdateButton.setText("Update");
        CUpdateButton.setCornerRadius(15);
        CUpdateButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CUpdateButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CUpdateButton.setIconTextGap(30);
        CUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CUpdateButtonActionPerformed(evt);
            }
        });

        CAddButton.setBackground(new java.awt.Color(44, 62, 80));
        CAddButton.setForeground(new java.awt.Color(255, 255, 255));
        CAddButton.setText("Add");
        CAddButton.setCornerRadius(15);
        CAddButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        CAddButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CAddButton.setIconTextGap(30);
        CAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CAddButtonActionPerformed(evt);
            }
        });

        UtilitiesLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        UtilitiesLabel.setForeground(new java.awt.Color(255, 255, 255));
        UtilitiesLabel.setText("Utilities");

        UAboutButton.setBackground(new java.awt.Color(44, 62, 80));
        UAboutButton.setForeground(new java.awt.Color(255, 255, 255));
        UAboutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image Folder/info.png"))); // NOI18N
        UAboutButton.setText("About Us");
        UAboutButton.setCornerRadius(15);
        UAboutButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        UAboutButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        UAboutButton.setIconTextGap(30);
        UAboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UAboutButtonActionPerformed(evt);
            }
        });

        UCloseButton.setBackground(new java.awt.Color(44, 62, 80));
        UCloseButton.setForeground(new java.awt.Color(255, 255, 255));
        UCloseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image Folder/cross.png"))); // NOI18N
        UCloseButton.setText("Close");
        UCloseButton.setCornerRadius(15);
        UCloseButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        UCloseButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        UCloseButton.setIconTextGap(30);
        UCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UCloseButtonActionPerformed(evt);
            }
        });

        UBackButton.setBackground(new java.awt.Color(44, 62, 80));
        UBackButton.setForeground(new java.awt.Color(255, 255, 255));
        UBackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image Folder/previous.png"))); // NOI18N
        UBackButton.setText("Back to Login");
        UBackButton.setCornerRadius(15);
        UBackButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        UBackButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        UBackButton.setIconTextGap(30);
        UBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UBackButtonActionPerformed(evt);
            }
        });

        UExportButton.setBackground(new java.awt.Color(44, 62, 80));
        UExportButton.setForeground(new java.awt.Color(255, 255, 255));
        UExportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image Folder/upload.png"))); // NOI18N
        UExportButton.setText("Export");
        UExportButton.setCornerRadius(15);
        UExportButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        UExportButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        UExportButton.setIconTextGap(30);
        UExportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UExportButtonActionPerformed(evt);
            }
        });

        VHomeButton.setBackground(new java.awt.Color(44, 62, 80));
        VHomeButton.setForeground(new java.awt.Color(255, 255, 255));
        VHomeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image Folder/home-icon-silhouette (1).png"))); // NOI18N
        VHomeButton.setText("Home");
        VHomeButton.setCornerRadius(15);
        VHomeButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        VHomeButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        VHomeButton.setIconTextGap(30);
        VHomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VHomeButtonActionPerformed(evt);
            }
        });

        VAssessmentButton1.setBackground(new java.awt.Color(44, 62, 80));
        VAssessmentButton1.setForeground(new java.awt.Color(255, 255, 255));
        VAssessmentButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image Folder/test.png"))); // NOI18N
        VAssessmentButton1.setText("Assessment Results");
        VAssessmentButton1.setCornerRadius(15);
        VAssessmentButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        VAssessmentButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        VAssessmentButton1.setIconTextGap(30);
        VAssessmentButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VAssessmentButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SidebarPanelLayout = new javax.swing.GroupLayout(SidebarPanel);
        SidebarPanel.setLayout(SidebarPanelLayout);
        SidebarPanelLayout.setHorizontalGroup(
            SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidebarPanelLayout.createSequentialGroup()
                .addGroup(SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SidebarPanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ViewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(SidebarPanelLayout.createSequentialGroup()
                                .addGroup(SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CAddButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CUpdateButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CDeleteButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UExportButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UAboutButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UCloseButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UBackButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UtilitiesLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CustomizeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(19, 19, 19))))
                    .addGroup(SidebarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(VSubjectButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(VAssessmentButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(VStudentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(SidebarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(VHomeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(SidebarPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(VAssessmentButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        SidebarPanelLayout.setVerticalGroup(
            SidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidebarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ViewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(VHomeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(VStudentButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(VSubjectButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(VAssessmentButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(VAssessmentButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(50, 50, 50)
                .addComponent(CustomizeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CAddButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CUpdateButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addGap(44, 44, 44)
                .addComponent(UtilitiesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UBackButton, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UCloseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UAboutButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(UExportButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jScrollPane1.setViewportView(SidebarPanel);

        jLabel1.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SCHOOL DASHBOARD");

        javax.swing.GroupLayout SidePanelHolderLayout = new javax.swing.GroupLayout(SidePanelHolder);
        SidePanelHolder.setLayout(SidePanelHolderLayout);
        SidePanelHolderLayout.setHorizontalGroup(
            SidePanelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
            .addGroup(SidePanelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SidePanelHolderLayout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        SidePanelHolderLayout.setVerticalGroup(
            SidePanelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelHolderLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 712, Short.MAX_VALUE))
            .addGroup(SidePanelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SidePanelHolderLayout.createSequentialGroup()
                    .addGap(89, 89, 89)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(111, Short.MAX_VALUE)))
        );

        UpperNavigationPanel.setOpaque(false);

        GreetLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        GreetLabel.setForeground(new java.awt.Color(255, 255, 255));

        Searchbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image Folder/search (2).png"))); // NOI18N
        Searchbar.setInheritsPopupMenu(true);

        GreetLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        GreetLabel1.setForeground(new java.awt.Color(255, 255, 255));
        GreetLabel1.setText("What's the agenda today?");

        Refresher.setBackground(new java.awt.Color(0, 153, 153));
        Refresher.setForeground(new java.awt.Color(255, 255, 255));
        Refresher.setText("REFRESH");
        Refresher.setActionCommand("Refresh");
        Refresher.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Refresher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefresherActionPerformed(evt);
            }
        });

        ExportAllButton.setBackground(new java.awt.Color(0, 153, 153));
        ExportAllButton.setForeground(new java.awt.Color(255, 255, 255));
        ExportAllButton.setText("EXPORT ALL");
        ExportAllButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ExportAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportAllButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UpperNavigationPanelLayout = new javax.swing.GroupLayout(UpperNavigationPanel);
        UpperNavigationPanel.setLayout(UpperNavigationPanelLayout);
        UpperNavigationPanelLayout.setHorizontalGroup(
            UpperNavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpperNavigationPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(UpperNavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UpperNavigationPanelLayout.createSequentialGroup()
                        .addComponent(GreetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Searchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(UpperNavigationPanelLayout.createSequentialGroup()
                        .addGroup(UpperNavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(GreetLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(UpperNavigationPanelLayout.createSequentialGroup()
                                .addComponent(Refresher, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ExportAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        UpperNavigationPanelLayout.setVerticalGroup(
            UpperNavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpperNavigationPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(UpperNavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Searchbar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GreetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(GreetLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UpperNavigationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Refresher, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(ExportAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        BodyPanel.setOpaque(false);
        BodyPanel.setLayout(new java.awt.CardLayout());

        Home.setOpaque(false);

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total Students");

        ValueDisplayStud.setFont(new java.awt.Font("Franklin Gothic Book", 0, 48)); // NOI18N
        ValueDisplayStud.setForeground(new java.awt.Color(255, 255, 255));
        ValueDisplayStud.setText("100%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ValueDisplayStud, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(ValueDisplayStud)
                .addGap(80, 80, 80)
                .addComponent(jLabel2)
                .addGap(36, 36, 36))
        );

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Subjects");

        jLabel6.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total ");

        ValueDisplaySUB.setFont(new java.awt.Font("Franklin Gothic Book", 0, 48)); // NOI18N
        ValueDisplaySUB.setForeground(new java.awt.Color(255, 255, 255));
        ValueDisplaySUB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ValueDisplaySUB.setText("100%");

        javax.swing.GroupLayout TotalAssessmentLayout = new javax.swing.GroupLayout(TotalAssessment);
        TotalAssessment.setLayout(TotalAssessmentLayout);
        TotalAssessmentLayout.setHorizontalGroup(
            TotalAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TotalAssessmentLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(TotalAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ValueDisplaySUB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        TotalAssessmentLayout.setVerticalGroup(
            TotalAssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TotalAssessmentLayout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(ValueDisplaySUB)
                .addGap(53, 53, 53)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(94, 94, 94))
        );

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Average");

        jLabel9.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Grades");

        GradeDisplay.setFont(new java.awt.Font("Franklin Gothic Book", 0, 48)); // NOI18N
        GradeDisplay.setForeground(new java.awt.Color(255, 255, 255));
        GradeDisplay.setText("100%");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GradeDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(GradeDisplay)
                .addGap(58, 58, 58)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(21, 21, 21))
        );

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Assessments");

        jLabel7.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total");

        ValueDisplayASS.setFont(new java.awt.Font("Franklin Gothic Book", 0, 48)); // NOI18N
        ValueDisplayASS.setForeground(new java.awt.Color(255, 255, 255));
        ValueDisplayASS.setText("100%");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(ValueDisplayASS, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addComponent(ValueDisplayASS)
                .addGap(49, 49, 49)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(22, 22, 22))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("QUICK STATS");

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TotalAssessment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(35, 35, 35)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalAssessment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(189, 189, 189))
        );

        BodyPanel.add(Home, "HomePanel");

        StudentsPanel.setBackground(new java.awt.Color(51, 255, 51));
        StudentsPanel.setForeground(new java.awt.Color(255, 153, 153));
        StudentsPanel.setOpaque(false);

        StudentTable.setAutoCreateRowSorter(true);
        StudentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "StudentID", "Name", "Pogram", "Year Level", "Section", "Address", "Contact Number", "Username", "Password"
            }
        ));
        StudentTable.setAlignmentY(2.0F);
        StudentTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        StudentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StudentTableMouseClicked(evt);
            }
        });
        TableScrollPane1.setViewportView(StudentTable);
        if (StudentTable.getColumnModel().getColumnCount() > 0) {
            StudentTable.getColumnModel().getColumn(0).setResizable(false);
            StudentTable.getColumnModel().getColumn(1).setResizable(false);
            StudentTable.getColumnModel().getColumn(2).setResizable(false);
            StudentTable.getColumnModel().getColumn(3).setResizable(false);
            StudentTable.getColumnModel().getColumn(4).setResizable(false);
            StudentTable.getColumnModel().getColumn(5).setResizable(false);
            StudentTable.getColumnModel().getColumn(6).setResizable(false);
            StudentTable.getColumnModel().getColumn(7).setResizable(false);
            StudentTable.getColumnModel().getColumn(8).setResizable(false);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        profilePicLabel.setText("Profile Picture");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("ID:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("NAME: ");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("PROGRAM:");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("YEAR: ");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("SECTION: ");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("ADDRESS: ");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("CONTACT:");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText("USERNAME: ");

        lblUsername.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblUsername.setText("*");

        lblContact.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblContact.setText("*");

        lblAddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblAddress.setText("*");

        lblYear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblYear.setText("*:");

        lblSection.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSection.setText("*");

        lblProgram.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblProgram.setText("*");

        lblName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblName.setText("*");

        lblID.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblID.setText("*");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(profilePicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(67, 67, 67)
                                .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(38, 38, 38)
                                .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblProgram, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblContact, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(lblSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(42, 42, 42)
                                .addComponent(lblYear, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(profilePicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblID))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblName))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblProgram))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblYear))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblSection))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lblAddress))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblContact))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblUsername))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout StudentsPanelLayout = new javax.swing.GroupLayout(StudentsPanel);
        StudentsPanel.setLayout(StudentsPanelLayout);
        StudentsPanelLayout.setHorizontalGroup(
            StudentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentsPanelLayout.createSequentialGroup()
                .addComponent(TableScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        StudentsPanelLayout.setVerticalGroup(
            StudentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StudentsPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(StudentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TableScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(222, Short.MAX_VALUE))
        );

        BodyPanel.add(StudentsPanel, "StudentsPanel");

        Subjects.setOpaque(false);

        subjectTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Taker", "SubjectID", "Name", "Code", "Units", "Credits"
            }
        ));
        jScrollPane2.setViewportView(subjectTable);

        AssignSub.setText("Assign Student");
        AssignSub.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AssignSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssignSubActionPerformed(evt);
            }
        });

        UnAssign.setText("Unassign Student");
        UnAssign.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UnAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UnAssignActionPerformed(evt);
            }
        });

        DeleteSub.setBackground(new java.awt.Color(220, 53, 70));
        DeleteSub.setForeground(new java.awt.Color(255, 255, 255));
        DeleteSub.setText("Delete Subject");
        DeleteSub.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DeleteSub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteSubActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("QUICK ACCESS:");

        ExportButton.setBackground(new java.awt.Color(0, 153, 153));
        ExportButton.setForeground(new java.awt.Color(255, 255, 255));
        ExportButton.setText("Export");
        ExportButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ExportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportButtonActionPerformed(evt);
            }
        });

        AssignSub1.setText("Add Subject");
        AssignSub1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AssignSub1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssignSub1ActionPerformed(evt);
            }
        });

        AssignSub2.setText("Update Subject");
        AssignSub2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        AssignSub2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AssignSub2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SubjectsLayout = new javax.swing.GroupLayout(Subjects);
        Subjects.setLayout(SubjectsLayout);
        SubjectsLayout.setHorizontalGroup(
            SubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SubjectsLayout.createSequentialGroup()
                .addGroup(SubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SubjectsLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(ExportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteSub, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(UnAssign, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AssignSub, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AssignSub2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AssignSub1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        SubjectsLayout.setVerticalGroup(
            SubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SubjectsLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(SubjectsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AssignSub, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UnAssign, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteSub, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AssignSub1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AssignSub2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 211, Short.MAX_VALUE))
        );

        BodyPanel.add(Subjects, "Subjects");

        Assessment.setOpaque(false);

        AssessmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "AssessmentID", "Subject", "Title ", "Type", "Max Score", "Date Given"
            }
        ));
        jScrollPane3.setViewportView(AssessmentTable);

        creation.setText("Add Assessment");
        creation.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        creation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creationActionPerformed(evt);
            }
        });

        deleteass.setBackground(new java.awt.Color(220, 53, 70));
        deleteass.setForeground(new java.awt.Color(255, 255, 255));
        deleteass.setText("Delete Assessment");
        deleteass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteassActionPerformed(evt);
            }
        });

        ExportAssessmentButton.setBackground(new java.awt.Color(0, 153, 153));
        ExportAssessmentButton.setForeground(new java.awt.Color(255, 255, 255));
        ExportAssessmentButton.setText("Export");
        ExportAssessmentButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ExportAssessmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportAssessmentButtonActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("QUICK ACCESS");

        UpdateAssessmentButton.setText("Update Assessment");
        UpdateAssessmentButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UpdateAssessmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateAssessmentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AssessmentLayout = new javax.swing.GroupLayout(Assessment);
        Assessment.setLayout(AssessmentLayout);
        AssessmentLayout.setHorizontalGroup(
            AssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssessmentLayout.createSequentialGroup()
                .addContainerGap(243, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(ExportAssessmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UpdateAssessmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(creation, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(AssessmentLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        AssessmentLayout.setVerticalGroup(
            AssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssessmentLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(AssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(creation, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteass, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExportAssessmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateAssessmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(211, Short.MAX_VALUE))
        );

        BodyPanel.add(Assessment, "Assessment");

        AssessmentResult.setOpaque(false);

        Result.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ResultID", "StudentID", "AssessmentID", "Score", "DateTaken", "Ratings"
            }
        ));
        jScrollPane4.setViewportView(Result);

        addorcreatetable.setText("Add Results");
        addorcreatetable.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addorcreatetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addorcreatetableActionPerformed(evt);
            }
        });

        UpdateResultsButton.setText("Update Results");
        UpdateResultsButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UpdateResultsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateResultsButtonActionPerformed(evt);
            }
        });

        dEL.setBackground(new java.awt.Color(220, 53, 70));
        dEL.setForeground(new java.awt.Color(255, 255, 255));
        dEL.setText("Delete Results");
        dEL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dELActionPerformed(evt);
            }
        });

        ExportResultButton.setBackground(new java.awt.Color(0, 153, 153));
        ExportResultButton.setForeground(new java.awt.Color(255, 255, 255));
        ExportResultButton.setText("Export");
        ExportResultButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ExportResultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportResultButtonActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("QUICK ACCESS:");

        javax.swing.GroupLayout AssessmentResultLayout = new javax.swing.GroupLayout(AssessmentResult);
        AssessmentResult.setLayout(AssessmentResultLayout);
        AssessmentResultLayout.setHorizontalGroup(
            AssessmentResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AssessmentResultLayout.createSequentialGroup()
                .addContainerGap(319, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addComponent(ExportResultButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UpdateResultsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addorcreatetable, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(AssessmentResultLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        AssessmentResultLayout.setVerticalGroup(
            AssessmentResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssessmentResultLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(AssessmentResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addorcreatetable, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateResultsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dEL, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExportResultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(212, Short.MAX_VALUE))
        );

        BodyPanel.add(AssessmentResult, "AssessmentResult");

        javax.swing.GroupLayout BgPanelLayout = new javax.swing.GroupLayout(BgPanel);
        BgPanel.setLayout(BgPanelLayout);
        BgPanelLayout.setHorizontalGroup(
            BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BgPanelLayout.createSequentialGroup()
                .addComponent(SidePanelHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UpperNavigationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BgPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(BodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        BgPanelLayout.setVerticalGroup(
            BgPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SidePanelHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(BgPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UpperNavigationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BodyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(BgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(BgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1316, 728));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void UCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UCloseButtonActionPerformed
        // TODO add your handling code here:
        java.awt.Window owner = javax.swing.SwingUtilities.getWindowAncestor(UCloseButton);
            CloseConfirmDialog.Result res = CloseConfirmDialog.showDialog(owner, "Your System");
        if (res == CloseConfirmDialog.Result.CLOSE) {
            if (owner != null) {
                owner.dispatchEvent(new java.awt.event.WindowEvent(owner, java.awt.event.WindowEvent.WINDOW_CLOSING));
            } else {
                System.exit(0);
            }
        }
    }//GEN-LAST:event_UCloseButtonActionPerformed

    private void UBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UBackButtonActionPerformed
        // TODO add your handling code here:
        java.awt.Window owner = javax.swing.SwingUtilities.getWindowAncestor(UBackButton);
            BackToLoginConfirmDialog.Result res = BackToLoginConfirmDialog.showDialog(owner, "Your System");
                
            if (res == BackToLoginConfirmDialog.Result.BACK) {
                if (owner != null) {
                    owner.setVisible(false); // or owner.dispose();
            }
                        LoginDialog Back = new LoginDialog();
                            this.dispose();
                                Back.setVisible(true);
        }
    }//GEN-LAST:event_UBackButtonActionPerformed

    private void UAboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UAboutButtonActionPerformed
        // TODO add your handling code here:
        java.awt.Window owner = javax.swing.SwingUtilities.getWindowAncestor(UAboutButton);
            String description = "Student Information System is a lightweight desktop tool for managing student records, subjects, and assessments. It streamlines routine tasks with a clean, "
                + "modern interface and built‑in confirmations to prevent mistakes. View, add, update, and export data in just a few clicks.";
                    AboutUsDialog.showDialog(owner, "Student Information System", description);
    }//GEN-LAST:event_UAboutButtonActionPerformed

    private void VStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VStudentButtonActionPerformed
        BodyPanel.setVisible(true);
            CardLayout cl = (CardLayout)(BodyPanel.getLayout());
                cl.show(BodyPanel, "StudentsPanel" );
    }//GEN-LAST:event_VStudentButtonActionPerformed

    private void VSubjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VSubjectButtonActionPerformed
        BodyPanel.setVisible(true);
            CardLayout cl = (CardLayout)(BodyPanel.getLayout());
                cl.show(BodyPanel, "Subjects" );
    }//GEN-LAST:event_VSubjectButtonActionPerformed

    private void CAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CAddButtonActionPerformed
        AddOptions add = new AddOptions(this, true);
        add.setVisible(rootPaneCheckingEnabled);
        add.setLocationRelativeTo(null);
    }//GEN-LAST:event_CAddButtonActionPerformed

    private void VHomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VHomeButtonActionPerformed
        BodyPanel.setVisible(true);
            CardLayout cl = (CardLayout)(BodyPanel.getLayout());
                cl.show(BodyPanel, "HomePanel");
    }//GEN-LAST:event_VHomeButtonActionPerformed

    private void VAssessmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VAssessmentButtonActionPerformed
        BodyPanel.setVisible(true);
            CardLayout cl = (CardLayout)(BodyPanel.getLayout());
                cl.show(BodyPanel, "Assessment");
    }//GEN-LAST:event_VAssessmentButtonActionPerformed

    private void StudentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StudentTableMouseClicked
    int row = StudentTable.getSelectedRow();
    if (row != -1) {
        // Get data from selected row
        String ID = (String) StudentTable.getValueAt(row, 0);
            String name = (String) StudentTable.getValueAt(row, 1);
                String program = (String) StudentTable.getValueAt(row, 2);
                    String year = (String) StudentTable.getValueAt(row, 3);
                        String section = (String) StudentTable.getValueAt(row, 4);
                            String address = (String) StudentTable.getValueAt(row, 5);
                                String contact = (String) StudentTable.getValueAt(row, 6);
                                    String username = (String) StudentTable.getValueAt(row,7);

        // Show text in labels
                                    lblID.setText(ID);
                                lblName.setText(name);
                            lblProgram.setText(program);
                        lblYear.setText(year);
                    lblSection.setText(section);
                lblAddress.setText(address);
            lblContact.setText(contact);
        lblUsername.setText(username);
        
try (Connection con = Connector.getConnection()) {
    String sql = "SELECT PhotoPath FROM student_photos WHERE StudentID = ?";
    PreparedStatement pst = con.prepareStatement(sql);
    pst.setInt(1, Integer.parseInt(ID));
    ResultSet rs = pst.executeQuery();

    if (rs.next()) {
        String path = rs.getString("PhotoPath");
        if (path != null && !path.isEmpty()) {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(
                profilePicLabel.getWidth(),
                profilePicLabel.getHeight(),
                Image.SCALE_SMOOTH
            );
            profilePicLabel.setIcon(new ImageIcon(img));
        } else {
            profilePicLabel.setIcon(null); // or set a default image
        }
    }
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, "Error loading profile photo: " + ex.getMessage());
        ex.printStackTrace();
}

    }
    }//GEN-LAST:event_StudentTableMouseClicked

    private void VAssessmentButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VAssessmentButton1ActionPerformed
        BodyPanel.setVisible(true);
            CardLayout cl = (CardLayout)(BodyPanel.getLayout());
                cl.show(BodyPanel, "AssessmentResult");
    }//GEN-LAST:event_VAssessmentButton1ActionPerformed

    private void CUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CUpdateButtonActionPerformed
  UpdateOptions wih = new UpdateOptions(this, true);
        wih.setVisible(rootPaneCheckingEnabled);
           wih.setLocationRelativeTo(null);

    }//GEN-LAST:event_CUpdateButtonActionPerformed

    private void CDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CDeleteButtonActionPerformed
DeleteOptions wih = new DeleteOptions(this, true);
        wih.setVisible(rootPaneCheckingEnabled);
           wih.setLocationRelativeTo(null);
     
    }//GEN-LAST:event_CDeleteButtonActionPerformed

    private void RefresherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefresherActionPerformed
        loadAssessments();
            loadStudentsFromDB();
                loadSubjectsFromDB();
                    loadAssessmentResults();
                        dashboardStats();
    }//GEN-LAST:event_RefresherActionPerformed

    private void AssignSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssignSubActionPerformed
     AssignStudents assign = new AssignStudents(this,rootPaneCheckingEnabled);
              assign.setVisible(rootPaneCheckingEnabled);
                assign.setLocationRelativeTo(null);
                    assign.setAlwaysOnTop(rootPaneCheckingEnabled); 
    }//GEN-LAST:event_AssignSubActionPerformed

    private void UnAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UnAssignActionPerformed
        UnAssignStud blah = new UnAssignStud(this, rootPaneCheckingEnabled);
            blah.setVisible(rootPaneCheckingEnabled);
                blah.setLocationRelativeTo(null);
                    blah.setAlwaysOnTop(rootPaneCheckingEnabled);   
    }//GEN-LAST:event_UnAssignActionPerformed

    private void DeleteSubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteSubActionPerformed
            DeleteSubject heeya = new DeleteSubject();
                heeya.setVisible(rootPaneCheckingEnabled);
                    heeya.setLocationRelativeTo(null);
                        heeya.setAlwaysOnTop(rootPaneCheckingEnabled);

 
  // TODO add your handling code here:
    }//GEN-LAST:event_DeleteSubActionPerformed

    private void creationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creationActionPerformed
      AddAssessment los = new AddAssessment();
        los.setVisible(rootPaneCheckingEnabled);
            los.setLocationRelativeTo(null);
                los.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_creationActionPerformed

    private void deleteassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteassActionPerformed
       DeleteAssessment amor = new DeleteAssessment();
        amor.setVisible(rootPaneCheckingEnabled);
            amor.setLocationRelativeTo(null);
                amor.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_deleteassActionPerformed

    private void ExportAssessmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportAssessmentButtonActionPerformed
        // TODO add your handling code here:
        if (AssessmentTable.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "There is no data to export.");
                return;
        }

        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
            chooser.setDialogTitle("Save assessment export");
                chooser.setSelectedFile(new java.io.File("assessments.xlsx"));

        javax.swing.filechooser.FileNameExtensionFilter xlsx =
                new javax.swing.filechooser.FileNameExtensionFilter("Excel Workbook (*.xlsx)", "xlsx");
        javax.swing.filechooser.FileNameExtensionFilter csv =
                new javax.swing.filechooser.FileNameExtensionFilter("CSV (Comma delimited) (*.csv)", "csv");
                    chooser.addChoosableFileFilter(xlsx);
                        chooser.addChoosableFileFilter(csv);
                            chooser.setFileFilter(xlsx); // default to .xlsx

        int result = chooser.showSaveDialog(this);
        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = chooser.getSelectedFile();
                javax.swing.filechooser.FileNameExtensionFilter sel =
                    (javax.swing.filechooser.FileNameExtensionFilter) chooser.getFileFilter();
                        boolean toXlsx = sel == xlsx || file.getName().toLowerCase().endsWith(".xlsx");

            try {
                if (toXlsx) {
                    XlsxExportUtils.exportTableToXlsx(AssessmentTable, file);
                } else {
                    ExportUtils.exportTableToCsv(AssessmentTable, file);
                }
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Exported to:\n" + file.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Export failed:\n" + ex.getMessage(),
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_ExportAssessmentButtonActionPerformed

    private void dELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dELActionPerformed
        DeleteAssessmentResults diewithasmile = new DeleteAssessmentResults();
            diewithasmile.setVisible(rootPaneCheckingEnabled);
                diewithasmile.setLocationRelativeTo(null);
                    diewithasmile.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_dELActionPerformed

    private void addorcreatetableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addorcreatetableActionPerformed
        AddResults ladygaga = new AddResults();
            ladygaga.setVisible(rootPaneCheckingEnabled);
                ladygaga.setLocationRelativeTo(null);
                    ladygaga.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_addorcreatetableActionPerformed

    private void AssignSub1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssignSub1ActionPerformed
              AddSubject ladygaga = new AddSubject();
            ladygaga.setVisible(rootPaneCheckingEnabled);
                ladygaga.setLocationRelativeTo(null);
                    ladygaga.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_AssignSub1ActionPerformed

    private void ExportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportButtonActionPerformed
        // TODO add your handling code here:
        if (subjectTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "There is no data to export.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Save Subject export");
                chooser.setSelectedFile(new File("subjects.xlsx"));

        FileNameExtensionFilter xlsx =
                new FileNameExtensionFilter("Excel Workbook (*.xlsx)", "xlsx");
        FileNameExtensionFilter csv =
                new FileNameExtensionFilter("CSV (Comma delimited) (*.csv)", "csv");
                    chooser.addChoosableFileFilter(xlsx);
                        chooser.addChoosableFileFilter(csv);
                            chooser.setFileFilter(xlsx);

        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
                FileNameExtensionFilter sel =
                    (FileNameExtensionFilter) chooser.getFileFilter();
                        boolean toXlsx = sel == xlsx || file.getName().toLowerCase().endsWith(".xlsx");

            try {
                if (toXlsx) {
                    XlsxExportUtils.exportTableToXlsx(subjectTable, file);
                } else {
                    ExportUtils.exportTableToCsv(subjectTable, file);
                }
                JOptionPane.showMessageDialog(this, "Exported to:\n" + file.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Export failed:\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_ExportButtonActionPerformed

    private void ExportResultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportResultButtonActionPerformed
        // TODO add your handling code here:
        if (Result.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "There is no data to export.");
                return;
        }

        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
            chooser.setDialogTitle("Save results export");
                chooser.setSelectedFile(new java.io.File("results.xlsx"));

        javax.swing.filechooser.FileNameExtensionFilter xlsx =
                new javax.swing.filechooser.FileNameExtensionFilter("Excel Workbook (*.xlsx)", "xlsx");
        javax.swing.filechooser.FileNameExtensionFilter csv =
                new javax.swing.filechooser.FileNameExtensionFilter("CSV (Comma delimited) (*.csv)", "csv");
                    chooser.addChoosableFileFilter(xlsx);
                        chooser.addChoosableFileFilter(csv);
                            chooser.setFileFilter(xlsx); // default to .xlsx

        int result = chooser.showSaveDialog(this);
        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = chooser.getSelectedFile();
                javax.swing.filechooser.FileNameExtensionFilter sel =
                    (javax.swing.filechooser.FileNameExtensionFilter) chooser.getFileFilter();
                        boolean toXlsx = sel == xlsx || file.getName().toLowerCase().endsWith(".xlsx");

            try {
                if (toXlsx) {
                    XlsxExportUtils.exportTableToXlsx(Result, file);
                } else {
                    ExportUtils.exportTableToCsv(Result, file);
                }
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Exported to:\n" + file.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Export failed:\n" + ex.getMessage(),
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_ExportResultButtonActionPerformed

    private void ExportAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportAllButtonActionPerformed
        // TODO add your handling code here:
        boolean allEmpty =
            StudentTable.getRowCount() == 0 &&
                subjectTable.getRowCount() == 0 &&
                    AssessmentTable.getRowCount() == 0 &&
                        Result.getRowCount() == 0;

        if (allEmpty) {
            javax.swing.JOptionPane.showMessageDialog(this, "There is no data to export.");
            return;
        }

        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
            chooser.setDialogTitle("Save all data");
                chooser.setSelectedFile(new java.io.File("all-data.xlsx"));

        javax.swing.filechooser.FileNameExtensionFilter xlsx =
                new javax.swing.filechooser.FileNameExtensionFilter("Excel Workbook (*.xlsx)", "xlsx");
        javax.swing.filechooser.FileNameExtensionFilter csv =
                new javax.swing.filechooser.FileNameExtensionFilter("CSV (Comma delimited) (*.csv)", "csv");
                    chooser.addChoosableFileFilter(xlsx);
                        chooser.addChoosableFileFilter(csv);
                            chooser.setFileFilter(xlsx);

        int result = chooser.showSaveDialog(this);
        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = chooser.getSelectedFile();
            boolean toXlsx = chooser.getFileFilter() == xlsx
                    || file.getName().toLowerCase().endsWith(".xlsx");

            try {
                if (toXlsx) {
                    XlsxExportUtils.exportTablesToXlsx(StudentTable, subjectTable, AssessmentTable, Result, file);
                } else {
                    ExportUtils.exportTablesToSingleCsv(StudentTable, subjectTable, AssessmentTable, Result, file);
                }
                javax.swing.JOptionPane.showMessageDialog(this, "Exported to:\n" + file.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Export failed:\n" + ex.getMessage(),
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_ExportAllButtonActionPerformed

    private void UExportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UExportButtonActionPerformed
        // TODO add your handling code here:
        boolean allEmpty =
            StudentTable.getRowCount() == 0 &&
            subjectTable.getRowCount() == 0 &&
            AssessmentTable.getRowCount() == 0 &&
            Result.getRowCount() == 0;

        if (allEmpty) {
            javax.swing.JOptionPane.showMessageDialog(this, "There is no data to export.");
            return;
        }

        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setDialogTitle("Save all data");
        chooser.setSelectedFile(new java.io.File("all-data.xlsx"));

        javax.swing.filechooser.FileNameExtensionFilter xlsx =
                new javax.swing.filechooser.FileNameExtensionFilter("Excel Workbook (*.xlsx)", "xlsx");
        javax.swing.filechooser.FileNameExtensionFilter csv =
                new javax.swing.filechooser.FileNameExtensionFilter("CSV (Comma delimited) (*.csv)", "csv");
        chooser.addChoosableFileFilter(xlsx);
        chooser.addChoosableFileFilter(csv);
        chooser.setFileFilter(xlsx);

        int result = chooser.showSaveDialog(this);
        if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
            java.io.File file = chooser.getSelectedFile();
            boolean toXlsx = chooser.getFileFilter() == xlsx
                    || file.getName().toLowerCase().endsWith(".xlsx");

            try {
                if (toXlsx) {
                    XlsxExportUtils.exportTablesToXlsx(StudentTable, subjectTable, AssessmentTable, Result, file);
                } else {
                    ExportUtils.exportTablesToSingleCsv(StudentTable, subjectTable, AssessmentTable, Result, file);
                }
                javax.swing.JOptionPane.showMessageDialog(this, "Exported to:\n" + file.getAbsolutePath());
            } catch (Exception ex) {
                ex.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Export failed:\n" + ex.getMessage(),
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_UExportButtonActionPerformed

    private void UpdateAssessmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateAssessmentButtonActionPerformed
        // TODO add your handling code here:
        UpdateAssessment ua = new UpdateAssessment();
            ua.setVisible(rootPaneCheckingEnabled);
                ua.setLocationRelativeTo(null);
                    ua.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_UpdateAssessmentButtonActionPerformed

    private void UpdateResultsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateResultsButtonActionPerformed
        // TODO add your handling code here:
        UpdateAssessmentResult nig = new UpdateAssessmentResult();
            nig.setVisible(rootPaneCheckingEnabled);
                nig.setLocationRelativeTo(null);
                    nig.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_UpdateResultsButtonActionPerformed

    private void AssignSub2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AssignSub2ActionPerformed
        // TODO add your handling code here:
        UpdateSubject usub = new UpdateSubject();
            usub.setVisible(rootPaneCheckingEnabled);
                usub.setLocationRelativeTo(null);
                    usub.setAlwaysOnTop(rootPaneCheckingEnabled);
    }//GEN-LAST:event_AssignSub2ActionPerformed
private DefaultTableModel assessmentModel;

private void loadAssessments() {
    assessmentModel.setRowCount(0);
    try (Connection con = Connector.getConnection()) {
        String sql = "SELECT a.AssessmentID, s.SubjectName, a.Title, a.Type, a.MaxScore, a.DateGiven " +
                     "FROM assessment a JOIN subject s ON a.SubjectID = s.SubjectID";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String raw = rs.getString("DateGiven");
            String display = normalizeDate(raw);
            assessmentModel.addRow(new Object[]{
                rs.getInt("AssessmentID"),
                rs.getString("SubjectName"),
                rs.getString("Title"),
                rs.getString("Type"),
                rs.getInt("MaxScore"),
                display
            });
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading assessments: " + ex.getMessage());
    }
}


private DefaultTableModel resultModel;

private void loadAssessmentResults() {
    resultModel.setRowCount(0);

    try (Connection con = Connector.getConnection()) {
        String sql = "SELECT r.ResultID, s.FirstName, s.LastName, a.Title, r.Score, r.DateTaken " +
                     "FROM assessmentresult r " +
                     "JOIN student s ON r.StudentID = s.StudentID " +
                     "JOIN assessment a ON r.AssessmentID = a.AssessmentID";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            int score = rs.getInt("Score");
            String rating = getRating(score);

            String raw = rs.getString("DateTaken");
            String display = normalizeDate(raw);

            resultModel.addRow(new Object[]{
                rs.getInt("ResultID"),
                rs.getString("FirstName") + " " + rs.getString("LastName"),
                rs.getString("Title"),
                score,
                display,
                rating
            });
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading results: " + ex.getMessage());
    }
}

// helper: turn "1758556800" or "2025-09-27 00:00:00" into "2025-09-27"
private static String normalizeDate(String raw) {
    if (raw == null || raw.isBlank()) return "";
    raw = raw.trim();

    // numeric? treat as unix epoch seconds
    if (raw.matches("^\\d{10}$")) {
        long epochSec = Long.parseLong(raw);
        return java.time.Instant.ofEpochSecond(epochSec)
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate()
                .toString();
    }

    // if it looks like "YYYY-MM-DD ..." keep first 10 chars
    if (raw.length() >= 10 && raw.charAt(4) == '-' && raw.charAt(7) == '-') {
        String d10 = raw.substring(0, 10);
        try { LocalDate.parse(d10); return d10; } catch (DateTimeParseException ignored) {}
    }

    // last resort: give raw back
    return raw;
}
    
private void loadSubjectsFromDB() {
    try (Connection con = Connector.getConnection();
     PreparedStatement pst = con.prepareStatement(
    "SELECT s.SubjectID, s.SubjectName, s.SubjectCode, s.Units, s.Credits, " +
    "CONCAT(st.LastName, ', ', st.FirstName) AS FullName " +
    "FROM subject s " +
    "LEFT JOIN student_subject ss ON s.SubjectID = ss.SubjectID " +
    "LEFT JOIN student st ON ss.StudentID = st.StudentID");
     ResultSet rs = pst.executeQuery()) {

    DefaultTableModel model = (DefaultTableModel) subjectTable.getModel();
    model.setRowCount(0); // clear existing rows

    while (rs.next()) {
     String fullName = rs.getString("FullName");
        if (fullName == null) fullName = "Epmty"; // or "No takers"

        Object[] row = {
            fullName,
                rs.getInt("SubjectID"),
                    rs.getString("SubjectName"),
                        rs.getString("SubjectCode"),
                            rs.getString("Units"),
                                rs.getInt("Credits")
};
        model.addRow(row);
    }

} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(),
        "Error", JOptionPane.ERROR_MESSAGE);
    ex.printStackTrace();
}
}
            
private void loadStudentsFromDB() {

    try (java.sql.Connection con = Connector.getConnection()) {

       String sql = "SELECT StudentID, LastName, FirstName, MiddleName, Program, YearLevel, Section, "
                    + "Barangay, City, Province, Country, Contact_number, Username, Password FROM student";
            PreparedStatement pst = con.prepareStatement(sql); 
                ResultSet rs = pst.executeQuery();
                    DefaultTableModel model = (DefaultTableModel) StudentTable.getModel();
                        model.setRowCount(0); // clear table first

        while (rs.next()) {
            // Combine full name
            String fullName = rs.getString("LastName") + ", " +
                                rs.getString("FirstName") + " " +
                                    rs.getString("MiddleName");

            // Combine address
            String fullAddress = rs.getString("Barangay") + ", " +
                                    rs.getString("City") + ", " +
                                        rs.getString("Province") + ", " +
                                            rs.getString("Country");
            Object[] row = {
                rs.getString("StudentID"),
                    fullName,
                        rs.getString("Program"),
                            rs.getString("YearLevel"),
                                rs.getString("Section"),
                                    fullAddress,
                                        rs.getString("Contact_number"),
                                            rs.getString("Username"),
                                                rs.getString("Password")
            };
            model.addRow(row);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
            "Database error: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);  
    }
}
private String getRating(int score) {
    if (score == 100) return "God Mode";
    else if (score >= 85) return "Outstanding";
    else if (score >= 70) return "Good";
    else if (score >= 50) return "Fair";
    else return "Needs Improvement";
}

private void dashboardStats(){
    try (Connection con = Connector.getConnection()) {
    // for total number of students
    String sqlStud = "SELECT COUNT(*) FROM student";
        PreparedStatement pstStud = con.prepareStatement(sqlStud);
            ResultSet rstStud = pstStud.executeQuery();
    if (rstStud.next()){
        ValueDisplayStud.setText(String.valueOf(rstStud.getInt(1)));
    }
    // for subjects
    String sqlSub = "SELECT COUNT(*) FROM subject";
        PreparedStatement pstSub = con.prepareStatement(sqlSub);
            ResultSet rstSub = pstSub.executeQuery();
    if(rstSub.next()){
        ValueDisplaySUB.setText(String.valueOf(rstSub.getInt(1)));
    }
    // for Assessments
    String sqlASS = "SELECT COUNT(*) FROM assessment";
        PreparedStatement pstAss = con.prepareStatement(sqlASS);
            ResultSet rstASS = pstAss.executeQuery();
    if (rstASS.next()){
        ValueDisplayASS.setText(String.valueOf(rstASS.getInt(1)));
    }
    //for the total Average of all students
    String sqlAve = "SELECT COUNT(*) FROM assessmentresult";
        PreparedStatement pstAve = con.prepareStatement(sqlAve);
            ResultSet rstAve = pstAve.executeQuery();
            if (rstAve.next()){
                double average = rstAve.getDouble(1);
                        GradeDisplay.setText(String.format("%.2f", average));
            }
    
}
    catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(),
                                  "SQL Error", JOptionPane.ERROR_MESSAGE);
    
}
}
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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Assessment;
    private javax.swing.JPanel AssessmentResult;
    private javax.swing.JTable AssessmentTable;
    private Custom_Components.RoundedButton AssignSub;
    private Custom_Components.RoundedButton AssignSub1;
    private Custom_Components.RoundedButton AssignSub2;
    private javax.swing.JPanel BgPanel;
    private javax.swing.JPanel BodyPanel;
    private Custom_Components.PressedDownAnimButton CAddButton;
    private Custom_Components.PressedDownAnimButton CDeleteButton;
    private Custom_Components.PressedDownAnimButton CUpdateButton;
    private javax.swing.JLabel CustomizeLabel;
    private Custom_Components.RoundedButton DeleteSub;
    private Custom_Components.RoundedButton ExportAllButton;
    private Custom_Components.RoundedButton ExportAssessmentButton;
    private Custom_Components.RoundedButton ExportButton;
    private Custom_Components.RoundedButton ExportResultButton;
    private javax.swing.JLabel GradeDisplay;
    private javax.swing.JLabel GreetLabel;
    private javax.swing.JLabel GreetLabel1;
    private javax.swing.JPanel Home;
    private Custom_Components.RoundedButton Refresher;
    private javax.swing.JTable Result;
    private Custom_Components.SearchBar Searchbar;
    private javax.swing.JPanel SidePanelHolder;
    private Custom_Components.BetterRoundPanel SidebarPanel;
    private javax.swing.JTable StudentTable;
    private javax.swing.JPanel StudentsPanel;
    private javax.swing.JPanel Subjects;
    private javax.swing.JScrollPane TableScrollPane1;
    private javax.swing.JPanel TotalAssessment;
    private Custom_Components.PressedDownAnimButton UAboutButton;
    private Custom_Components.PressedDownAnimButton UBackButton;
    private Custom_Components.PressedDownAnimButton UCloseButton;
    private Custom_Components.PressedDownAnimButton UExportButton;
    private Custom_Components.RoundedButton UnAssign;
    private Custom_Components.RoundedButton UpdateAssessmentButton;
    private Custom_Components.RoundedButton UpdateResultsButton;
    private javax.swing.JPanel UpperNavigationPanel;
    private javax.swing.JLabel UtilitiesLabel;
    private Custom_Components.PressedDownAnimButton VAssessmentButton;
    private Custom_Components.PressedDownAnimButton VAssessmentButton1;
    private Custom_Components.PressedDownAnimButton VHomeButton;
    private Custom_Components.PressedDownAnimButton VStudentButton;
    private Custom_Components.PressedDownAnimButton VSubjectButton;
    private javax.swing.JLabel ValueDisplayASS;
    private javax.swing.JLabel ValueDisplaySUB;
    private javax.swing.JLabel ValueDisplayStud;
    private javax.swing.JLabel ViewLabel;
    private Custom_Components.RoundedButton addorcreatetable;
    private Custom_Components.RoundedButton creation;
    private Custom_Components.RoundedButton dEL;
    private Custom_Components.RoundedButton deleteass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblProgram;
    private javax.swing.JLabel lblSection;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblYear;
    private javax.swing.JLabel profilePicLabel;
    private javax.swing.JTable subjectTable;
    // End of variables declaration//GEN-END:variables
}
