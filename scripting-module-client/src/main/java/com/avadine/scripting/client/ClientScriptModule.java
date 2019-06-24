package com.avadine.scripting.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.avadine.scripting.AbstractScriptModule;
import com.avadine.scripting.GatewayScripts;
import com.avadine.scripting.TagInformation;
import com.inductiveautomation.ignition.client.gateway_interface.ModuleRPCFactory;
import com.inductiveautomation.ignition.client.model.ClientContext;
import com.inductiveautomation.ignition.client.script.ClientOpcUtilities;
import com.inductiveautomation.ignition.client.script.ClientTagUtilities;
import com.inductiveautomation.ignition.common.config.Property;
import com.inductiveautomation.ignition.common.tags.config.TagConfigSet;
import com.inductiveautomation.ignition.common.tags.config.TagConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClientScriptModule extends AbstractScriptModule {
    private final Logger logger = LoggerFactory.getLogger("Scripting");
    private final GatewayScripts rpc;
    private ClientContext context;

    public ClientScriptModule(ClientContext thisContext) {
        rpc = ModuleRPCFactory.create("com.avadine.scripting", GatewayScripts.class);
        context = thisContext;
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

    @Override
    protected void extractTagInformationImpl() {
        String tagProvider = context.getDefaultSQLTagsProviderName();
        String dataSource = context.getDefaultDatasourceName();
        String parentPath = "";
        // ClientTagUtilities tagUtils = new ClientTagUtilities(context);
        // TagConfigSet tagConfig = tagUtils.browseConfiguration("", true);
        List<TagInformation> tags = browseConfiguration(tagProvider, parentPath);
        rpc.extractTagInformation(tags, dataSource);
    }

    @Override
    protected void extractTagInformationImpl(String tagProvider) {
        String dataSource = context.getDefaultDatasourceName();
        String parentPath = "";
        // ClientTagUtilities tagUtils = new ClientTagUtilities(context);
        // TagConfigSet tagConfig = tagUtils.browseConfiguration("", true);
        List<TagInformation> tags = browseConfiguration(tagProvider, parentPath);
        rpc.extractTagInformation(tags, dataSource);
    }

    @Override
    protected void extractTagInformationImpl(String tagProvider, String dataSource) {
        String parentPath = "";
        List<TagInformation> tags = browseConfiguration(tagProvider, parentPath);
        rpc.extractTagInformation(tags, dataSource);
    }

    @Override
    protected void extractTagInformationImpl(String tagProvider, String dataSource, String parentPath) {
        List<TagInformation> tags = browseConfiguration(tagProvider, parentPath);
        rpc.extractTagInformation(tags, dataSource);
    }

    @Override
    protected void extractTagInformationImpl(List<TagInformation> tags) {
        String dataSource = context.getDefaultDatasourceName();
        rpc.extractTagInformation(tags, dataSource);
    }

    @Override
    protected void extractTagInformationImpl(List<TagInformation> tags, String dataSource) {
        rpc.extractTagInformation(tags, dataSource);
    }

    private List<TagInformation> browseConfiguration(String tagProvider, String parentPath) {
        ClientTagUtilities tagUtils = new ClientTagUtilities(context);
        TagConfigSet tagConfig = tagUtils.browseConfiguration(String.format("[%s]%s", tagProvider, parentPath), true);
        return loopTags(tagProvider, tagConfig);
    }

    private List<TagInformation> loopTags(String tagProvider, TagConfigSet tagConfig) {
        List<TagInformation> tags = new ArrayList<TagInformation>();
        tagConfig.forEach(tag -> {
            if (tag.getTagType().toString() != "UDT_INST" && tag.getTagType().toString() != "Folder") {
                TagInformation tagInfo = parseTag(tag);
                if (tagInfo != null) {
                    tags.add(tagInfo);
                }
            } else if (tag.getTagType().toString() == "UDT_INST") {
                // Run on udt
                tags.addAll(browseConfiguration(tagProvider, tag.getFullPath()));
            } else {
                // Run
                tags.addAll(loopTags(tagProvider, tag.getSubTags()));
            }
        });
        return tags;
    }

    private TagInformation parseTag(TagConfiguration tag) {
        TagInformation tagInfo = new TagInformation();
        String tagPath = tag.getFullPath();
        String deviceName = tagPath.substring(tagPath.indexOf("[") + 1, tagPath.lastIndexOf("]"));
        String hostname = getHostname(deviceName);
        tagInfo.setTagPath(tagPath);
        tagInfo.setHostname(hostname);
        String opcItemPath;
        String engUnits;
        String accessRights;
        String scaleMode;
        float rawMin;
        float rawMax;
        float scaledMin;
        float scaledMax;
        float engLow;
        float engHigh;
        String engLimitMode;
        Iterator<Property<?>> prop = tag.getProperties().iterator();
        while (prop.hasNext()) {
            switch (prop.next().toString()) {
            case "opcItemPath":
                opcItemPath = (String) tag.get((Property<?>) prop.next());
                tagInfo.setOpcItemPath(opcItemPath);
                break;
            case "engUnit":
                engUnits = (String) tag.get((Property<?>) prop.next());
                tagInfo.setEngUnits(engUnits);
                break;
            case "accessRights":
                accessRights = (String) tag.get((Property<?>) prop.next());
                tagInfo.setAccessRights(accessRights);
                break;
            case "scaleMode":
                scaleMode = (String) tag.get((Property<?>) prop.next());
                tagInfo.setScaleMode(scaleMode);
                break;
            case "rawMin":
                rawMin = (float) tag.get((Property<?>) prop.next());
                tagInfo.setRawMin(rawMin);
                break;
            case "rawMax":
                rawMax = (float) tag.get((Property<?>) prop.next());
                tagInfo.setRawMax(rawMax);
                break;
            case "scaledMin":
                scaledMin = (float) tag.get((Property<?>) prop.next());
                tagInfo.setScaledMin(scaledMin);
                break;
            case "scaledMax":
                scaledMax = (float) tag.get((Property<?>) prop.next());
                tagInfo.setScaledMax(scaledMax);
                break;
            case "engLow":
                engLow = (float) tag.get((Property<?>) prop.next());
                tagInfo.setEngLow(engLow);
                break;
            case "engHigh":
                engHigh = (float) tag.get((Property<?>) prop.next());
                tagInfo.setEngHigh(engHigh);
                break;
            case "engLimitMode":
                engLimitMode = (String) tag.get((Property<?>) prop.next());
                tagInfo.setEngLimitMode(engLimitMode);
                break;
            default:
                break;
            }
        }
        if (!tagPath.contains("zzBulk")) {
            return tagInfo;
        } else {
            return null;
        }
    }

    private String getHostname(String deviceName) {
        String opcItemPath = String.format("[%s][Diagnostics]/Hostname", deviceName);
        ClientOpcUtilities opc = new ClientOpcUtilities();
        String hostname = null;
        try {
            hostname = (String) opc.readValue("Ignition OPC-UA Server", opcItemPath).getValue();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return hostname;
    }
}
