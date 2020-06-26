package com.adsync.data.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import static java.lang.String.format;
import static com.adsync.data.utilities.RandomUtils.*;

@JsonPropertyOrder({"name","dn","external_id","domain","dns_host_name","operating_system","operating_system_sp","operating_system_version","sid"})
public class DeviceUnit {
    private String name;
    private String dn;
    private String external_id;
    private String domain;
    private String dns_host_name;
    private String operating_system;
    private String operating_system_sp;
    private String operating_system_version;
    private String sid;

    public DeviceUnit(){
        this.name = "UK-"+getRandomString("",4).toUpperCase()+"-"+getRandomNumber(10000,99999);
        this.dn = "CN="+name+",OU=Dev,OU=UK,OU=Computers,OU=quasi,DC=yellow,DC=quasi";
        this.external_id= format("\\01\\02\\%d\\%d\\%d\\84\\76\\89\\42\\9c\\65\\00\\b8\\57\\ad\\6a\\eb", getRandomNumber(10, 99), getRandomNumber(10, 99), getRandomNumber(10, 99));
        this.domain = "yellow.quasi";
        this.dns_host_name = name+"."+domain;
        this.operating_system = "Windows 10 Enterprise";
        this. operating_system_sp= "Service Pack 3";
        this.operating_system_version = "10.0 (10586)";
        this.sid = format("S-1-5-21-1574557677-3491378025-%d-%d", getRandomNumber(100000000, 999999999), getRandomNumber(10000, 99999));
    };

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

    @JsonProperty("dns_host_name")
    public String getDns_host_name() {
        return dns_host_name;
    }

    @JsonProperty("operating_system")
    public String getOperating_system() {
        return operating_system;
    }

    @JsonProperty("operating_system_sp")
    public String getOperating_system_sp() {
        return operating_system_sp;
    }

    @JsonProperty("operating_system_version")
    public String getOperating_system_version() {
        return operating_system_version;
    }

    @JsonProperty("sid")
    public String getSid() {
        return sid;
    }

    @Override
    public String toString() {
        return "DeviceUnit{" +
                "name='" + name + '\'' +
                ", dn='" + dn + '\'' +
                ", external_id='" + external_id + '\'' +
                ", domain='" + domain + '\'' +
                ", dns_host_name='" + dns_host_name + '\'' +
                ", operating_system='" + operating_system + '\'' +
                ", operating_system_sp='" + operating_system_sp + '\'' +
                ", operating_system_version='" + operating_system_version + '\'' +
                ", sid='" + sid + '\'' +
                '}';
    }
}


