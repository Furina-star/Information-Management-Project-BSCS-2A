/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bello
 */

import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * CSV export utilities for JTable.
 * - Single table -> single CSV file.
 * - Multiple tables -> one CSV file with sections (CSV is single-sheet by design).
 * No external libraries required.
 */
public final class ExportUtils {
    private ExportUtils() {}

    /**
     * Export a single JTable to CSV (UTF-8 with BOM for best Excel compatibility).
     */
    public static void exportTableToCsv(JTable table, File file) throws IOException {
        if (table == null) throw new IllegalArgumentException("table is null");
        String path = file.getAbsolutePath();
        if (!path.toLowerCase().endsWith(".csv")) {
            file = new File(path + ".csv");
        }

        TableModel m = table.getModel();
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {

            // Write BOM so Excel detects UTF-8 properly
            pw.write('\uFEFF');

            // Header
            for (int col = 0; col < m.getColumnCount(); col++) {
                if (col > 0) pw.print(',');
                pw.print(escapeCsv(m.getColumnName(col)));
            }
            pw.println();

            // Rows
            for (int row = 0; row < m.getRowCount(); row++) {
                for (int col = 0; col < m.getColumnCount(); col++) {
                    if (col > 0) pw.print(',');
                    Object v = m.getValueAt(row, col);
                    pw.print(escapeCsv(v == null ? "" : v.toString()));
                }
                pw.println();
            }
        }
    }

    /**
     * Export multiple tables into a single CSV file, one after another, separated by a blank line
     * and a section header line starting with "# ".
     * Note: CSV cannot have multiple sheets; this produces one sheet with sections.
     */
    public static void exportTablesToSingleCsv(Map<String, JTable> tables, File file) throws IOException {
        if (tables == null || tables.isEmpty()) {
            throw new IllegalArgumentException("tables is null or empty");
        }
        String path = file.getAbsolutePath();
        if (!path.toLowerCase().endsWith(".csv")) {
            file = new File(path + ".csv");
        }

        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {

            // Write BOM for Excel
            pw.write('\uFEFF');

            boolean firstSection = true;
            for (Map.Entry<String, JTable> entry : tables.entrySet()) {
                String sectionName = entry.getKey() == null ? "Section" : entry.getKey();
                JTable table = entry.getValue();
                if (table == null) continue;

                TableModel m = table.getModel();

                if (!firstSection) {
                    pw.println(); // blank line between sections
                } else {
                    firstSection = false;
                }

                // Section header
                pw.println("# " + sectionName);

                // Header row
                for (int col = 0; col < m.getColumnCount(); col++) {
                    if (col > 0) pw.print(',');
                    pw.print(escapeCsv(m.getColumnName(col)));
                }
                pw.println();

                // Data rows
                for (int row = 0; row < m.getRowCount(); row++) {
                    for (int col = 0; col < m.getColumnCount(); col++) {
                        if (col > 0) pw.print(',');
                        Object v = m.getValueAt(row, col);
                        pw.print(escapeCsv(v == null ? "" : v.toString()));
                    }
                    pw.println();
                }
            }
        }
    }

    /**
     * Convenience overload: three tables -> one CSV with "Subjects", "Assessments", "Results" sections.
     */
    public static void exportTablesToSingleCsv(JTable subjects, JTable assessments, JTable results, File file) throws IOException {
        Map<String, JTable> map = new LinkedHashMap<>();
        map.put("Subjects", subjects);
        map.put("Assessments", assessments);
        map.put("Results", results);
        exportTablesToSingleCsv(map, file);
    }

    /**
     * Convenience overload: four tables -> one CSV with "Students", "Subjects", "Assessments", "Results" sections.
     */
    public static void exportTablesToSingleCsv(JTable students, JTable subjects, JTable assessments, JTable results, File file) throws IOException {
        Map<String, JTable> map = new LinkedHashMap<>();
        map.put("Students", students);
        map.put("Subjects", subjects);
        map.put("Assessments", assessments);
        map.put("Results", results);
        exportTablesToSingleCsv(map, file);
    }

    // ----- internal helpers -----

    private static String escapeCsv(String s) {
        if (s == null) return "";
        boolean needsQuotes = s.contains(",") || s.contains("\"") || s.contains("\n") || s.contains("\r");
        if (needsQuotes) {
            s = s.replace("\"", "\"\"");
            return "\"" + s + "\"";
        }
        return s;
    }
}