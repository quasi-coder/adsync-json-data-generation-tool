package com.adsync.data.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Arrays;
import static com.adsync.data.utilities.RandomUtils.*;
import static java.lang.String.format;

@JsonPropertyOrder({"name","dn","external_id","domain","child_ous","child_devices"})
public class OrganizationalUnit {
    private String name;
    private String dn;
    private String external_id;
    private String domain;
    private String[] child_ous;
    private String[] child_devices;

    public OrganizationalUnit(){this(new String[0]);}

    public OrganizationalUnit(String[] child_devices){
        this.name=getRandomString("DevAndOrg",2);
        this.dn="DC="+getRandomString("ParentOrg",4)+"OU=Test";
        this.external_id= format("\\04\\%d\\%d\\%d\\84\\76\\89\\42\\9c\\65\\00\\b8\\57\\ad\\6a\\ed",getRandomNumber(10,99),getRandomNumber(10,99),getRandomNumber(10,99));
        this.domain = "yellow.quasi";
        this.child_ous=new String[0];
        this.child_devices=child_devices;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("dn")
    public String getDn() {
        return dn;
    }

    @JsonProperty("external_id")
    public String getExternal_id() {
        return external_id;
    }

    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("child_ous")
    public String[] getChild_ous() {
        return child_ous;
    }

    @JsonProperty("child_devices")
    public String[] getChild_devices() {
        return child_devices;
    }

    public void setChildDevices(String[] child_devices) {
        this.child_devices = child_devices;
    }

    @Override
    public String toString() {
        return "OrganizationalUnit{" +
                "name='" + name + '\'' +
                ", dn='" + dn + '\'' +
                ", external_id='" + external_id + '\'' +
                ", domain='" + domain + '\'' +
                ", child_ous=" + Arrays.toString(child_ous) +
                ", child_devices=" + Arrays.toString(child_devices) +
                '}';
    }
}

