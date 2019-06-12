package com.avadine.scripting;

import com.inductiveautomation.ignition.gateway.model.GatewayContext;

import org.apache.wicket.Application;

public class GatewayScriptModule extends AbstractScriptModule {

    @Override
    protected GatewayContext getContextImpl() {
        return ((GatewayContext) Application.get());
    }

}
