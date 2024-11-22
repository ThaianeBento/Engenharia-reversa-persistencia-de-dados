package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConfig {
    private static final Map<String, String> URL_PATTERNS = new HashMap<>();
    private static final Map<String, String> DRIVERS = new HashMap<>();

    static {
        URL_PATTERNS.put("postgresql", "jdbc:postgresql://{host}:{port}/{database}");
        URL_PATTERNS.put("mysql", "jdbc:mysql://{host}:{port}/{database}");
        URL_PATTERNS.put("sqlserver", "jdbc:sqlserver://{host}:{port};databaseName={database}");
        URL_PATTERNS.put("oracle", "jdbc:oracle:thin:@{host}:{port}:{database}");

        DRIVERS.put("postgresql", "org.postgresql.Driver");
        DRIVERS.put("mysql", "com.mysql.cj.jdbc.Driver");
        DRIVERS.put("sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        DRIVERS.put("oracle", "oracle.jdbc.OracleDriver");
    }

    public static String getJdbcUrl(String sgbd, String host, String port, String databaseName) {
        String pattern = URL_PATTERNS.get(sgbd.toLowerCase());
        if (pattern != null) {
            return pattern.replace("{host}", host)
                    .replace("{port}", port)
                    .replace("{database}", databaseName);
        }
        throw new IllegalArgumentException("SGBD não suportado: " + sgbd);
    }

    public static String getDriver(String sgbd) {
        String driver = DRIVERS.get(sgbd.toLowerCase());
        if (driver != null) {
            return driver;
        }
        throw new IllegalArgumentException("Driver não encontrado para o SGBD: " + sgbd);
    }

    public static Connection createConnection(String sgbd, String host, String port, String databaseName, String username, String password)

        throws Exception {
        if (sgbd == null || host == null || port == null || databaseName == null || username == null || password == null) {
            throw new IllegalArgumentException("Todos os parâmetros de conexão devem ser fornecidos e não nulos.");
        }

        String jdbcUrl = getJdbcUrl(sgbd, host, port, databaseName);
        String driver = getDriver(sgbd);

        Class.forName(driver);
        return DriverManager.getConnection(jdbcUrl, username, password);
    }


}
