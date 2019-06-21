package com.avadine.scripting;

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

}
