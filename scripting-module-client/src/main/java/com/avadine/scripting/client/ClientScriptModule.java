package com.avadine.scripting.client;

import com.avadine.scripting.AbstractScriptModule;
import com.avadine.scripting.GatewayScripts;
import com.inductiveautomation.ignition.client.gateway_interface.ModuleRPCFactory;
public class ClientScriptModule extends AbstractScriptModule {

    private final GatewayScripts rpc;

    public ClientScriptModule() {
        rpc = ModuleRPCFactory.create(
            "scripting-module.scripting-module",
            GatewayScripts.class
        );
    }

    @Override
    protected void runImpl(String pythonScript) {
        rpc.run(pythonScript);
    }

}
