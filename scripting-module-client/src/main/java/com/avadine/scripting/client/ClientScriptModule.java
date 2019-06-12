package com.avadine.scripting.client;

import com.avadine.scripting.AbstractScriptModule;
import com.avadine.scripting.Gateway;
import com.inductiveautomation.ignition.client.gateway_interface.ModuleRPCFactory;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;

public class ClientScriptModule extends AbstractScriptModule {

    private final Gateway rpc;

    public ClientScriptModule() {
        rpc = ModuleRPCFactory.create(
            "com.avadine.scripting.scripting-module",
            Gateway.class
        );
    }

    @Override
    protected GatewayContext getContextImpl() {
        return rpc.getContext();
    }

}
