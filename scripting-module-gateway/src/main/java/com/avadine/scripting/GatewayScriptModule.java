package com.avadine.scripting;

public class GatewayScriptModule extends AbstractScriptModule {

    @Override
    protected int getContextImpl() {
        return ((GatewayContext) Application.get());
    }

}
