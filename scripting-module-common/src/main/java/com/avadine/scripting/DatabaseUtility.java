package com.avadine.scripting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DatabaseUtility {
    private Logger logger = LoggerFactory.getLogger("Scripting");
    private Connection conn;
    private Statement statement;

    DatabaseUtility() {

    }

    public DatabaseUtility(Logger thisLogger, Connection thisConn) {
        logger = thisLogger;
        conn = thisConn;
        createStatement();
    }

    public DatabaseUtility(Logger thisLogger, String userName, String password, String serverName, String port, String databaseName) {
        logger = thisLogger;
        conn = createConnection(userName, password, serverName, port, databaseName);
        createStatement();
    }

    public DatabaseUtility(Logger thisLogger, String userName, String password, String connectionString) {
        logger = thisLogger;
        conn = createConnection(userName, password, connectionString);
        createStatement();
    }

    public DatabaseUtility(Connection thisConn) {
        conn = thisConn;
        createStatement();
    }

    public DatabaseUtility(String userName, String password, String serverName, String port, String databaseName) {
        conn = createConnection(userName, password, serverName, port, databaseName);
        createStatement();
    }

    public DatabaseUtility(String userName, String password, String connectionString) {
        conn = createConnection(userName, password, connectionString);
        createStatement();
    }

    public Connection getConnection() {
        return conn;
    }

    private void createStatement() {
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    public Connection createConnection(String userName, String password, String serverName, String port, String databaseName){
        if (conn == null) {
            String url = "jdbc:sqlserver://" +
                    serverName +
                    "\\MSSQLSERVER:" +
                    port +
                    ";databaseName=" +
                    databaseName;

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection(url, userName, password);

            } catch (ClassNotFoundException | SQLException ex){
                logger.error(ex.getMessage());
            }
        }
        return conn;
    }
    
    public Connection createConnection(String userName, String password, String connectionString){
        if (conn == null) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conn = DriverManager.getConnection(connectionString, userName, password);
            } catch (ClassNotFoundException ex){
                logger.error(ex.getMessage());
            } catch (SQLException ex){
                logger.error("SQL Exception:",ex);
            }
        }
        logger.debug(conn.toString());
        return conn;
    }

    public Connection createConnection(Connection newConn) {
        conn = newConn;
        return conn;
    }

    public void closeConnection(){
        if (conn != null) {
            try {
                clearStatement();
                conn.close();
                conn = null;
            } catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
    }

    public ResultSet executeQuery(String dataSource, String query) throws SQLException {
        if (conn != null && statement != null) {
            query = query.trim();
            if (query.startsWith("SELECT")) {
                statement.executeQuery(query);
            }
        }
        return statement.getResultSet();
    }

    public void executeUpdate(String dataSource, String query) throws SQLException {
        if (conn != null && statement != null) {
            query = query.trim();
            if (!query.startsWith("SELECT")) {
                statement.executeUpdate(query);
            }
        }
    }

    public void addBatch(String query) {
        if (conn != null && statement != null) {
            try {
                statement.addBatch(query);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public void executeBatch() {
        if (conn != null && statement != null) {
            try {
                statement.executeBatch();
            } catch (SQLException ex){
                logger.error(ex.getMessage());
            }
        }
    }

    public void clearStatement() {
        if (conn != null && statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Connection already closed.", e);
            }
            statement = null;
        }
    }

}