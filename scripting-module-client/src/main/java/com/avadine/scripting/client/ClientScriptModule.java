package com.avadine.scripting.client;

import com.avadine.scripting.AbstractScriptModule;
import com.avadine.scripting.GatewayScripts;
import com.inductiveautomation.ignition.client.gateway_interface.ModuleRPCFactory;
public class ClientScriptModule extends AbstractScriptModule {

    private final GatewayScripts rpc;

    public ClientScriptModule() {
        rpc = ModuleRPCFactory.create(
            "com.avadine.scripting",
            GatewayScripts.class
        );
    }

    @Override
    protected void runImpl(String pythonScript) {
        rpc.run(pythonScript);
    }

    @Override
    protected void addBatchImpl(String dataSource, String query) {
        rpc.addBatch(dataSource, query);
    }

    @Override
    protected void addBatchImpl(String dataSource, String prepQuery, Object... parameters) {
        rpc.addBatch(dataSource, prepQuery, parameters);
    }

    @Override
    protected void executeBatchImpl() {
        rpc.executeBatch();
    }
}
