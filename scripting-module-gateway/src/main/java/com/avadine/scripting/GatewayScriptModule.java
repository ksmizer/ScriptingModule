package com.avadine.scripting;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.inductiveautomation.ignition.gateway.model.GatewayContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.python.util.PythonInterpreter;

public class GatewayScriptModule extends AbstractScriptModule {
    
    private Logger logger = LoggerFactory.getLogger("Scripting");
    private DatabaseUtility database;
    private GatewayContext context;

    public GatewayScriptModule(GatewayContext thisContext) {
        context = thisContext;
    }

    @Override
    protected void runImpl(String pythonScript) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec(pythonScript);
    }

    @Override
    protected void addBatchImpl(String dataSource, String query) {
        try {
            createConnectionIfNotExists(dataSource);
            database.addBatch(query);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }
    
    @Override
    protected void addBatchImpl(String dataSource, String prepQuery, Object... parameters) {
        try {
            createConnectionIfNotExists(dataSource);
            if (prepQuery.contains("?") && parameters.length == countCharacters(prepQuery, '?')) {
                for (Object parameter : parameters) {
                    String param;
                    if (isInteger(parameter)) {
                        param = (String) parameter;
                    } else if (isFloat(parameter)) {
                        param = (String) parameter;
                    } else if (isDouble(parameter)) {
                        param = (String) parameter;
                    } else if (isString(parameter)) {
                        param = "'" + (String) parameter + "'";
                    } else if (isDate(parameter)) {
                        param = parameter.toString();
                    } else {
                        param = "";
                    }
                    prepQuery = prepQuery.replaceFirst("?", param);
                }
                database.addBatch(prepQuery);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    protected void executeBatchImpl() {
        database.executeBatch();
        database.closeConnection();
    }

    
    
    private void createConnectionIfNotExists(String dataSource) throws SQLException {
        if (database.getConnection() == null) {
            Connection connection;
            connection = context.getDatasourceManager().getConnection(dataSource);
            database.createConnection(connection);
        }
    }
    
    private boolean isInteger(Object obj) {
        return obj instanceof Integer;
    }
    
    private boolean isFloat(Object obj) {
        return obj instanceof Float;
    }
    
    private boolean isString(Object obj) {
        return obj instanceof String;
    }
    
    private boolean isDouble(Object obj) {
        return obj instanceof Double;
    }
    
    private boolean isDate(Object obj) {
        return obj instanceof Date;
    }
    
    public int countCharacters(String string, char character) {
        int count = 0;
        for (char iCharacter : string.toCharArray()) {
            if (iCharacter == character) {
                count ++;
            }
        }
        return count;
    }
    
    @Override
    protected void extractTagInformationImpl() {
        logger.info("Unused method");
    }

    @Override
    protected void extractTagInformationImpl(String tagProvider) {
        logger.info("Unused method");
    }

    @Override
    protected void extractTagInformationImpl(String tagProvider, String dataSource) {
        logger.info("Unused method");
    }
    
    @Override
    protected void extractTagInformationImpl(String tagProvider, String dataSource, String parentPath) {
        logger.info("Unused method");
    }
    
    @Override
    protected void extractTagInformationImpl(List<TagInformation> tags) {
        logger.info("Unused method");
    }
    
    @Override
    protected void extractTagInformationImpl(List<TagInformation> tags, String dataSource) {
        try {
            createConnectionIfNotExists(dataSource);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        for (TagInformation tag : tags) {
            logger.info(tag.toString());
        }
    }
}
