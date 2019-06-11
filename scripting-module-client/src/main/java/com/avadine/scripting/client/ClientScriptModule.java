package com.avadine.scripting.client;

import com.avadine.scripting.AbstractScriptModule;
import com.avadine.scripting.Gateway;
import com.inductiveautomation.ignition.client.gateway_interface.ModuleRPCFactory;

public class ClientScriptModule extends AbstractScriptModule {

    private final Gateway rpc;

    public ClientScriptModule() {
        rpc = ModuleRPCFactory.create(
            "com.avadine.scripting-module",
            Gateway.class
        );
    }

    @Override
    protected int getContextImpl() {
        return rpc.getContext(arg0, arg1);
    }

}
