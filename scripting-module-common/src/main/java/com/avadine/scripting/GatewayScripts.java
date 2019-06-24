package com.avadine.scripting;

import java.util.List;

public interface GatewayScripts {

    public void run(String pythonScript);

    public void addBatch(String dataSource, String query);
    public void addBatch(String dataSource, String prepQuery, Object... parameters);
    public void executeBatch();

    public void extractTagInformation();
    public void extractTagInformation(String tagProvider);
    public void extractTagInformation(String tagProvider, String dataSource);
    public void extractTagInformation(String tagProvider, String dataSource, String parentPath);
    public void extractTagInformation(List<TagInformation> tags);
    public void extractTagInformation(List<TagInformation> tags, String dataSource);

}
