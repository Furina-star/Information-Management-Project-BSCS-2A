package DataBaseConnection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.stream.Collectors;

public class Connector {
    private static Connection SINGLETON;

    public static synchronized Connection getConnection() throws SQLException {
        if (SINGLETON != null && !SINGLETON.isClosed()) return SINGLETON;

        try {
            // Create app data folder and DB file
            Path dir = Path.of(System.getProperty("user.home"), ".learningtracker");
            Files.createDirectories(dir);
            String url = "jdbc:sqlite:" + dir.resolve("learningtracker.db").toAbsolutePath();

            SINGLETON = DriverManager.getConnection(url);

            // Important for ON DELETE CASCADE
            try (Statement st = SINGLETON.createStatement()) {
                st.execute("PRAGMA foreign_keys = ON");
                st.execute("PRAGMA journal_mode = WAL");
                st.execute("PRAGMA synchronous = NORMAL");
            }

            // Create schema on first run, then optionally seed
            ensureSchemaAndSeed();

            return SINGLETON;
        } catch (Exception e) {
            throw new SQLException("Failed to open SQLite database", e);
        }
    }

    private static void ensureSchemaAndSeed() throws Exception {
        if (!tableExists("student")) {
            // exact resource paths reflect your package and filenames
            runSqlFromResource("/DataBaseConnection/schema-sqlite.sql");

            // Optional: load sample data if present (supports both naming styles)
            runSqlIfExists("/DataBaseConnection/seed-sqlite.sql");
            runSqlIfExists("/DataBaseConnection/seed.sqlite.sql");
        }
    }

    private static boolean tableExists(String name) throws SQLException {
        try (PreparedStatement ps = SINGLETON.prepareStatement(
                "SELECT 1 FROM sqlite_master WHERE type='table' AND name=?")) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private static void runSqlIfExists(String resourcePath) throws Exception {
        String sql = readResource(resourcePath);
        if (sql == null) return; // silently skip if not found
        executeSqlScript(sql);
    }

    private static void runSqlFromResource(String resourcePath) throws Exception {
        String sql = readResource(resourcePath);
        if (sql == null) {
            throw new IllegalStateException("Resource not found: " + resourcePath);
        }
        executeSqlScript(sql);
    }

    private static void executeSqlScript(String sql) throws SQLException {
        // Split on semicolons that end a statement (ignore comments/blank lines)
        for (String stmt : sql.split(";(\\s*\\r?\\n|\\s*$)")) {
            String s = stmt.trim();
            if (s.isEmpty() || s.startsWith("--")) continue;
            try (Statement st = SINGLETON.createStatement()) {
                st.execute(s);
            }
        }
    }

    private static String readResource(String path) {
        try (InputStream in = Connector.class.getResourceAsStream(path)) {
            if (in == null) return null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                return br.lines().collect(Collectors.joining("\n"));
            }
        } catch (Exception e) {
            return null;
        }
    }
}
