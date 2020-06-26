package com.adsync.data.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import static com.adsync.data.utilities.RandomUtils.*;
import java.util.Arrays;
import static java.lang.String.format;

@JsonPropertyOrder({"dn","email","external_id","members","name","proxy_addresses","sid"})
public class Group{
    private String dn;
    private String email;
    private String external_id;
    private String[] members;
    private String name;
    private String[] proxy_addresses;
    private String sid;
    private String prefix=getRandomString("GROUPOWNER",3).toUpperCase();

    public Group(){
        this(new String[0]);
    }

    public Group(String[] members){
        this.name = prefix+" (YELLOW)";
        this.dn ="CN="+prefix+",OU=Distribution Lists,OU=Exchange,OU=Groups,OU=quasi,DC=yellow,DC=quasi";
        this.email = prefix+"@quasi.com";
        this.external_id = format("\\33\\f5\\25\\72\\84\\76\\%d\\42\\9c\\%d\\%d\\b8\\57\\ad\\6a\\eb", getRandomNumber(10, 99), getRandomNumber(10, 99), getRandomNumber(10, 99));
        this.members=members;
        this.proxy_addresses = new String[]{prefix+"@astaro.quasi.com", prefix+"@exchange.local",prefix+"@emea.yellow.quasi"};
        this.sid= format("S-1-5-%d-1574557677-3491378025-%d-49107", getRandomNumber(10,99), getRandomNumber(100000000, 999999999));
    }

    @JsonProperty("dn")
    public String getDn() {
        return dn;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("external_id")
    public String getExternal_id() {
        return external_id;
    }

    @JsonProperty("members")
    public String[] getMembers() {
        return members;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("proxy_addresses")
    public String[] getProxy_addresses() {
        return proxy_addresses;
    }

    @JsonProperty("sid")
    public String getSid() {
        return sid;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Group{" +
                "dn='" + dn + '\'' +
                ", email='" + email + '\'' +
                ", external_id='" + external_id + '\'' +
                ", members=" + Arrays.toString(members) +
                ", name='" + name + '\'' +
                ", proxy_addresses=" + Arrays.toString(proxy_addresses) +
                ", sid='" + sid + '\'' +
                '}';
    }

}

