package com.avadine.scripting;

import java.util.List;

import com.inductiveautomation.ignition.common.BundleUtil;
import com.inductiveautomation.ignition.common.script.hints.ScriptArg;
import com.inductiveautomation.ignition.common.script.hints.ScriptFunction;

public abstract class AbstractScriptModule implements GatewayScripts, ClientScripts {

    static {
        BundleUtil.get().addBundle(
            AbstractScriptModule.class.getSimpleName(),
            AbstractScriptModule.class.getClassLoader(),
            AbstractScriptModule.class.getName().replace('.', '/')
        );
    }

    @Override
    @ScriptFunction(docBundlePrefix = "AbstractScriptModule")
    public void run(@ScriptArg("pythonScript") String pythonScript) {

        runImpl(pythonScript);
    }

    protected abstract void runImpl(String pythonScript);
    
    @Override
    @ScriptFunction(docBundlePrefix = "AbstractScriptModule")
    public void addBatch(@ScriptArg("dataSource") String dataSource, @ScriptArg("query") String query) {

        addBatchImpl(dataSource, query);
    }

    protected abstract void addBatchImpl(String dataSource, String query);
    
    @Override
    @ScriptFunction(docBundlePrefix = "AbstractScriptModule")
    public void addBatch(@ScriptArg("dataSource") String dataSource, @ScriptArg("prepQuery") String prepQuery, @ScriptArg("parameters") Object... parameters) {

        addBatchImpl(dataSource, prepQuery, parameters);
    }

    protected abstract void addBatchImpl(String dataSource, String query,  Object... parameters);
    
    @Override
    @ScriptFunction(docBundlePrefix = "AbstractScriptModule")
    public void executeBatch() {

        executeBatchImpl();
    }

    protected abstract void executeBatchImpl();
    
    @Override
    @ScriptFunction(docBundlePrefix = "AbstractScriptModule")
    public void extractTagInformation() {

        extractTagInformationImpl();
    }

    protected abstract void extractTagInformationImpl();
    
    @Override
    @ScriptFunction(docBundlePrefix = "AbstractScriptModule")
    public void extractTagInformation(@ScriptArg("tagProvider") String tagProvider) {

        extractTagInformationImpl(tagProvider);
    }

    protected abstract void extractTagInformationImpl(String tagProvider);
    
    @Override
    @ScriptFunction(docBundlePrefix = "AbstractScriptModule")
    public void extractTagInformation(@ScriptArg("tagProvider") String tagProvider, @ScriptArg("dataSource") String dataSource) {

        extractTagInformationImpl(tagProvider, dataSource);
    }

    protected abstract void extractTagInformationImpl(String tagProvider, String dataSource);

    @Override
    @ScriptFunction(docBundlePrefix = "AbstractScriptModule")
    public void extractTagInformation(@ScriptArg("tagProvider") String tagProvider, @ScriptArg("dataSource") String dataSource, @ScriptArg("parentPath") String parentPath) {

        extractTagInformationImpl(tagProvider, dataSource, parentPath);
    }

    protected abstract void extractTagInformationImpl(String tagProvider, String dataSource, String parentPath);

    @Override
    @ScriptFunction(docBundlePrefix = "AbstractScriptModule")
    public void extractTagInformation(@ScriptArg("tags") List<TagInformation> tags) {

        extractTagInformationImpl(tags);
    }

    protected abstract void extractTagInformationImpl(List<TagInformation> tags);

    @Override
    @ScriptFunction(docBundlePrefix = "AbstractScriptModule")
    public void extractTagInformation(@ScriptArg("tags") List<TagInformation> tags, @ScriptArg("dataSource") String dataSource) {

        extractTagInformationImpl(tags, dataSource);
    }

    protected abstract void extractTagInformationImpl(List<TagInformation> tags, String dataSource);

}
