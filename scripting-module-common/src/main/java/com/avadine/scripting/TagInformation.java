package com.avadine.scripting;

import java.util.Objects;

/**
 * TagInformation
 */
public class TagInformation {
    private String tagPath;
    private String opcItemPath;
    private String hostname;
    private String engUnits;
    private String accessRights;
    private String scaleMode;
    private float rawMin;
    private float rawMax;
    private float scaledMin;
    private float scaledMax;
    private float engLow;
    private float engHigh;
    private String engLimitMode;

    public TagInformation() {
    }

    public TagInformation(String tagPath, String opcItemPath, String hostname, String engUnits, String accessRights, String scaleMode, float rawMin, float rawMax, float scaledMin, float scaledMax, float engLow, float engHigh, String engLimitMode) {
        this.tagPath = tagPath;
        this.opcItemPath = opcItemPath;
        this.hostname = hostname;
        this.engUnits = engUnits;
        this.accessRights = accessRights;
        this.scaleMode = scaleMode;
        this.rawMin = rawMin;
        this.rawMax = rawMax;
        this.scaledMin = scaledMin;
        this.scaledMax = scaledMax;
        this.engLow = engLow;
        this.engHigh = engHigh;
        this.engLimitMode = engLimitMode;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public TagInformation hostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public String getTagPath() {
        return this.tagPath;
    }

    public void setTagPath(String tagPath) {
        this.tagPath = tagPath;
    }

    public String getOpcItemPath() {
        return this.opcItemPath;
    }

    public void setOpcItemPath(String opcItemPath) {
        this.opcItemPath = opcItemPath;
    }

    public String getEngUnits() {
        return this.engUnits;
    }

    public void setEngUnits(String engUnits) {
        this.engUnits = engUnits;
    }

    public String getAccessRights() {
        return this.accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public String getScaleMode() {
        return this.scaleMode;
    }

    public void setScaleMode(String scaleMode) {
        this.scaleMode = scaleMode;
    }

    public float getRawMin() {
        return this.rawMin;
    }

    public void setRawMin(float rawMin) {
        this.rawMin = rawMin;
    }

    public float getRawMax() {
        return this.rawMax;
    }

    public void setRawMax(float rawMax) {
        this.rawMax = rawMax;
    }

    public float getScaledMin() {
        return this.scaledMin;
    }

    public void setScaledMin(float scaledMin) {
        this.scaledMin = scaledMin;
    }

    public float getScaledMax() {
        return this.scaledMax;
    }

    public void setScaledMax(float scaledMax) {
        this.scaledMax = scaledMax;
    }

    public float getEngLow() {
        return this.engLow;
    }

    public void setEngLow(float engLow) {
        this.engLow = engLow;
    }

    public float getEngHigh() {
        return this.engHigh;
    }

    public void setEngHigh(float engHigh) {
        this.engHigh = engHigh;
    }

    public String getEngLimitMode() {
        return this.engLimitMode;
    }

    public void setEngLimitMode(String engLimitMode) {
        this.engLimitMode = engLimitMode;
    }

    public TagInformation tagPath(String tagPath) {
        this.tagPath = tagPath;
        return this;
    }

    public TagInformation opcItemPath(String opcItemPath) {
        this.opcItemPath = opcItemPath;
        return this;
    }

    public TagInformation engUnits(String engUnits) {
        this.engUnits = engUnits;
        return this;
    }

    public TagInformation accessRights(String accessRights) {
        this.accessRights = accessRights;
        return this;
    }

    public TagInformation scaleMode(String scaleMode) {
        this.scaleMode = scaleMode;
        return this;
    }

    public TagInformation rawMin(float rawMin) {
        this.rawMin = rawMin;
        return this;
    }

    public TagInformation rawMax(float rawMax) {
        this.rawMax = rawMax;
        return this;
    }

    public TagInformation scaledMin(float scaledMin) {
        this.scaledMin = scaledMin;
        return this;
    }

    public TagInformation scaledMax(float scaledMax) {
        this.scaledMax = scaledMax;
        return this;
    }

    public TagInformation engLow(float engLow) {
        this.engLow = engLow;
        return this;
    }

    public TagInformation engHigh(float engHigh) {
        this.engHigh = engHigh;
        return this;
    }

    public TagInformation engLimitMode(String engLimitMode) {
        this.engLimitMode = engLimitMode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TagInformation)) {
            return false;
        }
        TagInformation tagInformation = (TagInformation) o;
        return Objects.equals(tagPath, tagInformation.tagPath) && Objects.equals(opcItemPath, tagInformation.opcItemPath) && Objects.equals(engUnits, tagInformation.engUnits) && Objects.equals(accessRights, tagInformation.accessRights) && Objects.equals(scaleMode, tagInformation.scaleMode) && rawMin == tagInformation.rawMin && rawMax == tagInformation.rawMax && scaledMin == tagInformation.scaledMin && scaledMax == tagInformation.scaledMax && engLow == tagInformation.engLow && engHigh == tagInformation.engHigh && Objects.equals(engLimitMode, tagInformation.engLimitMode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagPath, opcItemPath, engUnits, accessRights, scaleMode, rawMin, rawMax, scaledMin, scaledMax, engLow, engHigh, engLimitMode);
    }

    @Override
    public String toString() {
        return "{" +
            " tagPath='" + getTagPath() + "'" +
            ", opcItemPath='" + getOpcItemPath() + "'" +
            ", engUnits='" + getEngUnits() + "'" +
            ", accessRights='" + getAccessRights() + "'" +
            ", scaleMode='" + getScaleMode() + "'" +
            ", rawMin='" + getRawMin() + "'" +
            ", rawMax='" + getRawMax() + "'" +
            ", scaledMin='" + getScaledMin() + "'" +
            ", scaledMax='" + getScaledMax() + "'" +
            ", engLow='" + getEngLow() + "'" +
            ", engHigh='" + getEngHigh() + "'" +
            ", engLimitMode='" + getEngLimitMode() + "'" +
            "}";
    }
    
}