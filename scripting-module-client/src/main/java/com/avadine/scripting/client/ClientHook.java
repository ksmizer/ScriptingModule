package com.avadine.scripting.client;

import com.inductiveautomation.ignition.client.model.ClientContext;
import com.inductiveautomation.ignition.common.licensing.LicenseState;
import com.inductiveautomation.ignition.common.script.ScriptManager;
import com.inductiveautomation.ignition.common.script.hints.PropertiesFileDocProvider;
import com.inductiveautomation.vision.api.client.AbstractClientModuleHook;

public class ClientHook extends AbstractClientModuleHook {

    private ClientContext context;

    @Override
    public void startup(ClientContext thisContext, LicenseState licenseState){
        context = thisContext;
    }

    @Override
    public void initializeScriptManager(ScriptManager manager) {
        super.initializeScriptManager(manager);

        manager.addScriptModule(
            "aera.db",
            new ClientScriptModule(context),
            new PropertiesFileDocProvider()
        );
    }

}
