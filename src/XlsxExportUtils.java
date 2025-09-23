/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel (.xlsx) export utilities for JTable.
 * - Single table -> single sheet.
 * - Multiple tables -> one workbook with multiple sheets.
 * Requires Apache POI on the classpath (poi, poi-ooxml, poi-ooxml-lite or poi-ooxml-full)
 * plus its runtime dependencies (xmlbeans, commons-io, commons-compress, commons-collections4, curvesapi, commons-logging).
 */
public final class XlsxExportUtils {
    private XlsxExportUtils() {}

    /**
     * Export a single JTable to a .xlsx file (sheet name = "Export").
     */
    public static void exportTableToXlsx(JTable table, File file) throws IOException {
        if (table == null) throw new IllegalArgumentException("table is null");
        String path = file.getAbsolutePath();
        if (!path.toLowerCase().endsWith(".xlsx")) {
            file = new File(path + ".xlsx");
        }

        TableModel model = table.getModel();

        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Export");

            // Styles
            CellStyle headerStyle = wb.createCellStyle();
            Font bold = wb.createFont();
            bold.setBold(true);
            headerStyle.setFont(bold);

            CellStyle dateStyle = wb.createCellStyle();
            short df = wb.getCreationHelper().createDataFormat().getFormat("yyyy-mm-dd");
            dateStyle.setDataFormat(df);

            writeModelToSheet(sheet, model, headerStyle, dateStyle);
            autosizeColumns(sheet, model.getColumnCount());

            try (OutputStream out = new FileOutputStream(file)) {
                wb.write(out);
            }
        }
    }

    /**
     * Export multiple tables into a single .xlsx workbook.
     * Keys in the map are sheet names; values are the corresponding tables.
     */
    public static void exportTablesToXlsx(Map<String, JTable> sheets, File file) throws IOException {
        if (sheets == null || sheets.isEmpty()) {
            throw new IllegalArgumentException("sheets is null or empty");
        }
        String path = file.getAbsolutePath();
        if (!path.toLowerCase().endsWith(".xlsx")) {
            file = new File(path + ".xlsx");
        }

        try (Workbook wb = new XSSFWorkbook()) {
            // Shared styles
            CellStyle headerStyle = wb.createCellStyle();
            Font bold = wb.createFont();
            bold.setBold(true);
            headerStyle.setFont(bold);

            CellStyle dateStyle = wb.createCellStyle();
            short df = wb.getCreationHelper().createDataFormat().getFormat("yyyy-mm-dd");
            dateStyle.setDataFormat(df);

            Set<String> usedNames = new HashSet<>();

            for (Map.Entry<String, JTable> e : sheets.entrySet()) {
                JTable table = e.getValue();
                if (table == null) continue;

                String rawName = e.getKey() == null ? "Sheet" : e.getKey().trim();
                String sheetName = uniqueExcelSheetName(sanitizeSheetName(rawName), usedNames);
                usedNames.add(sheetName);

                Sheet sheet = wb.createSheet(sheetName);
                TableModel model = table.getModel();

                writeModelToSheet(sheet, model, headerStyle, dateStyle);
                autosizeColumns(sheet, model.getColumnCount());
            }

            try (OutputStream out = new FileOutputStream(file)) {
                wb.write(out);
            }
        }
    }

    /**
     * Convenience overload: export three tables as sheets "Subjects", "Assessments", "Results".
     */
    public static void exportTablesToXlsx(JTable subjects, JTable assessments, JTable results, File file) throws IOException {
        Map<String, JTable> map = new LinkedHashMap<>();
        map.put("Subjects", subjects);
        map.put("Assessments", assessments);
        map.put("Results", results);
        exportTablesToXlsx(map, file);
    }

    /**
     * Convenience overload: export four tables as sheets "Students", "Subjects", "Assessments", "Results".
     */
    public static void exportTablesToXlsx(JTable students, JTable subjects, JTable assessments, JTable results, File file) throws IOException {
        Map<String, JTable> map = new LinkedHashMap<>();
        map.put("Students", students);
        map.put("Subjects", subjects);
        map.put("Assessments", assessments);
        map.put("Results", results);
        exportTablesToXlsx(map, file);
    }

    // ----- internal helpers -----

    private static void writeModelToSheet(Sheet sheet, TableModel model, CellStyle headerStyle, CellStyle dateStyle) {
        if (model == null) return;

        // Header
        Row header = sheet.createRow(0);
        for (int c = 0; c < model.getColumnCount(); c++) {
            Cell cell = header.createCell(c, CellType.STRING);
            cell.setCellValue(model.getColumnName(c));
            cell.setCellStyle(headerStyle);
        }

        // Data
        for (int r = 0; r < model.getRowCount(); r++) {
            Row row = sheet.createRow(r + 1);
            for (int c = 0; c < model.getColumnCount(); c++) {
                Object v = model.getValueAt(r, c);
                Cell cell = row.createCell(c);

                if (v == null) {
                    cell.setBlank();
                } else if (v instanceof Number) {
                    cell.setCellValue(((Number) v).doubleValue());
                } else if (v instanceof java.util.Date) {
                    cell.setCellValue((java.util.Date) v);
                    cell.setCellStyle(dateStyle);
                } else if (v instanceof Boolean) {
                    cell.setCellValue((Boolean) v);
                } else {
                    cell.setCellValue(v.toString());
                }
            }
        }
    }

    private static void autosizeColumns(Sheet sheet, int columnCount) {
        for (int c = 0; c < columnCount; c++) {
            try {
                sheet.autoSizeColumn(c);
            } catch (Exception ignore) {
                // Safe-guard for extremely wide/complex cells
            }
        }
    }

    // Excel sheet name rules: max 31 chars, no : \ / ? * [ ]
    private static String sanitizeSheetName(String name) {
        String cleaned = name.replace(':',' ')
                             .replace('\\',' ')
                             .replace('/',' ')
                             .replace('?',' ')
                             .replace('*',' ')
                             .replace('[',' ')
                             .replace(']',' ')
                             .trim();
        if (cleaned.isEmpty()) cleaned = "Sheet";
        if (cleaned.length() > 31) cleaned = cleaned.substring(0, 31);
        return cleaned;
    }

    private static String uniqueExcelSheetName(String base, Set<String> used) {
        if (!used.contains(base)) return base;
        int i = 2;
        while (true) {
            String candidate = sanitizeSheetName(base + " (" + i + ")");
            if (!used.contains(candidate)) return candidate;
            i++;
        }
    }
}