package com.adsync.data.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Arrays;
import static com.adsync.data.utilities.RandomUtils.*;
import static java.lang.String.format;

@JsonPropertyOrder({"dn","email","external_id","name","delegate_dns","proxy_addresses","exchange_login","given_name","login","primary_group_sid","principal_name","sid","surname"})
public class User {
    private String dn;
    private String email;
    private String external_id;
    private String name;
    private String[] delegate_dns;
    private String[] proxy_addresses;
    private String exchange_login;
    private String given_name;
    private String login;
    private String primary_group_sid;
    private String principal_name;
    private String sid;
    private String surname;

    public User(){
        this.given_name = getRandomString("ADUSER",3).toUpperCase();
        this.surname = getRandomString("TEST",3).toUpperCase();
        this.dn = "CN="+given_name+" "+surname+",OU=Marketing,OU=JP,OU=Users,OU=quasi,DC=yellow,DC=quasi";
        this.email = given_name.toLowerCase()+"."+surname.toLowerCase()+"@quasi.co.jp";
        this.external_id = format("\\42\\c9\\bc\\%d\\%d\\%d\\e7\\4e\\93\\25\\42\\e7\\68\\39\\59\\25", getRandomNumber(10, 99), getRandomNumber(10, 99), getRandomNumber(10, 99));
        this.name = given_name+" "+surname;
        this.delegate_dns = new String[0];
        this.proxy_addresses = new String[]{given_name+"."+surname+"@astaro.quasi.com",given_name+"."+surname+"@quasi.com",
                                            given_name+"."+surname+"@emea.yellow.quasi",given_name+"."+surname+"@exchange.local"};
        this.exchange_login = given_name+surname;
        this.login = "YELLOW\\"+exchange_login;
        this.primary_group_sid = format("S-1-5-%d-1574557677-3491378025-%d-513", getRandomNumber(10,99), getRandomNumber(100000, 999999));
        this.principal_name = exchange_login+"@yellow.quasi";
        this.sid = format("S-1-5-%d-1574557677-3491378025-%d-3776", getRandomNumber(10,99), getRandomNumber(100000000, 999999999));
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("delegate_dns")
    public String[] getDelegate_dns() {
        return delegate_dns;
    }

    @JsonProperty("proxy_addresses")
    public String[] getProxy_addresses() {
        return proxy_addresses;
    }

    @JsonProperty("exchange_login")
    public String getExchange_login() {
        return exchange_login;
    }

    @JsonProperty("given_name")
    public String getGiven_name() {
        return given_name;
    }

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("primary_group_sid")
    public String getPrimary_group_sid() {
        return primary_group_sid;
    }

    @JsonProperty("principal_name")
    public String getPrincipal_name() {
        return principal_name;
    }

    @JsonProperty("sid")
    public String getSid() {
        return sid;
    }

    @JsonProperty("surname")
    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "User{" +
                "dn='" + dn + '\'' +
                ", email='" + email + '\'' +
                ", external_id='" + external_id + '\'' +
                ", name='" + name + '\'' +
                ", delegate_dns=" + Arrays.toString(delegate_dns) +
                ", proxy_addresses=" + Arrays.toString(proxy_addresses) +
                ", exchange_login='" + exchange_login + '\'' +
                ", given_name='" + given_name + '\'' +
                ", login='" + login + '\'' +
                ", primary_group_sid='" + primary_group_sid + '\'' +
                ", principal_name='" + principal_name + '\'' +
                ", sid='" + sid + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
