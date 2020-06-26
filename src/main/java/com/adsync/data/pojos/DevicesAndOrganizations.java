package com.adsync.data.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Arrays;

@JsonPropertyOrder({"origin","publicFolders","searchBases","devices","organizationalUnits"})
public class DevicesAndOrganizations {
    private String origin;
    private String[] publicFolders;
    private String[] searchBases;
    private OrganizationalUnit[] organizationalUnits;
    private DeviceUnit[] devices;

    public DevicesAndOrganizations(){
        this.origin = "Quasi-Coder";
        this.publicFolders = new String[0];
        this.searchBases = new String[]{"DC=yellow,DC=quasi-domain"};
    }

    @JsonProperty("origin")
    public String getOrigin() {
        return origin;
    }

    @JsonProperty("publicFolders")
    public String[] getPublicFolders() {
        return publicFolders;
    }

    @JsonProperty("searchBases")
    public String[] getSearchBases() {
        return searchBases;
    }

    @JsonProperty("organizationalUnits")
    public OrganizationalUnit[] getOrganizationalUnits() {
        return organizationalUnits;
    }

    @JsonProperty("devices")
    public DeviceUnit[] getDevices() {
        return devices;
    }

    public void setOrganizationalUnits(OrganizationalUnit[] organizationalUnits) {
        this.organizationalUnits = organizationalUnits;
    }

    public void setDevices(DeviceUnit[] devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "DevicesAndOrganizations{" +
                "origin='" + origin + '\'' +
                ", publicFolders=" + Arrays.toString(publicFolders) +
                ", searchBases=" + Arrays.toString(searchBases) +
                ", organizationalUnits=" + Arrays.toString(organizationalUnits) +
                ", devices=" + Arrays.toString(devices) +
                '}';
    }
}
