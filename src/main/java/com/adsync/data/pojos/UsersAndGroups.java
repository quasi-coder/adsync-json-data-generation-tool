package com.adsync.data.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Arrays;

@JsonPropertyOrder({ "origin", "publicFolders", "searchBases", "users", "groups" })
public class UsersAndGroups {
    private String origin;
    private String[] publicFolders;
    private String[] searchBases;
    private User[] users;
    private Group[] groups;

    public UsersAndGroups(){
        this.origin = "Quasi-Coder";
        this.searchBases = new String[]{"DC=yellow,DC=quasi"};
        this.publicFolders = new String[0];
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

    @JsonProperty("users")
    public User[] getUsers() {
        return users;
    }

    @JsonProperty("groups")
    public Group[] getGroups() {
        return groups;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public void setGroups(Group[] groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "UsersAndGroups{" +
                "origin='" + origin + '\'' +
                ", publicFolders=" + Arrays.toString(publicFolders) +
                ", searchBases=" + Arrays.toString(searchBases) +
                ", users=" + Arrays.toString(users) +
                ", groups=" + Arrays.toString(groups) +
                '}';
    }
}




