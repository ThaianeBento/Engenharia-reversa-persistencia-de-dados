package model;
import java.sql.Connection;

public class DatabaseConnectionModel {
    private String sgbd;
    private String databaseName;
    private String username;
    private String password;
    private String host;
    private String port;

    public DatabaseConnectionModel(String sgbd, String databaseName, String username, String password, String host, String port) {
        this.sgbd = sgbd;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    public Connection connect() throws Exception {
        return DatabaseConfig.createConnection(sgbd, host, port, databaseName, username, password);
    }

    public String getSgbd() {
        return sgbd;
    }

    public void setSgbd(String sgbd) {
        this.sgbd = sgbd;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}