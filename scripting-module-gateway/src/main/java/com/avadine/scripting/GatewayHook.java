package com.avadine.scripting;

import com.inductiveautomation.ignition.common.licensing.LicenseState;
import com.inductiveautomation.ignition.common.script.ScriptManager;
import com.inductiveautomation.ignition.common.script.hints.PropertiesFileDocProvider;
import com.inductiveautomation.ignition.gateway.clientcomm.ClientReqSession;
import com.inductiveautomation.ignition.gateway.model.AbstractGatewayModuleHook;
import com.inductiveautomation.ignition.gateway.model.GatewayContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GatewayHook extends AbstractGatewayModuleHook {

    private final Logger logger = LoggerFactory.getLogger("Scripting Module");

    private final GatewayScriptModule scriptModule = new GatewayScriptModule();

    @Override
    public void setup(GatewayContext gatewayContext) {
        logger.trace("setup()");
    }

    @Override
    public void startup(LicenseState licenseState) {
        logger.trace("startup()");
    }

    @Override
    public void shutdown() {
        logger.trace("shutdown()");
    }

    @Override
    public void initializeScriptManager(ScriptManager manager) {
        super.initializeScriptManager(manager);

        manager.addScriptModule(
                "system.gateway",
                scriptModule,
                new PropertiesFileDocProvider());
    }

    @Override
    public Object getRPCHandler(ClientReqSession session, Long projectId) {
        return scriptModule;
    }
}
