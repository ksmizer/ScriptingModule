package com.avadine.scripting;

public interface GatewayScripts {

    public void run(String pythonScript);

    public void addBatch(String dataSource, String query);
    public void addBatch(String dataSource, String prepQuery, Object... parameters);
    public void executeBatch();
}
