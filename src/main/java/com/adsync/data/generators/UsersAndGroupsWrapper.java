package com.adsync.data.generators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.adsync.data.pojos.Group;
import com.adsync.data.pojos.User;
import com.adsync.data.pojos.UsersAndGroups;
import com.adsync.data.utilities.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.lang.String.format;


public class UsersAndGroupsWrapper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static void usersAndGroupsJavaObjToJson(int maxUserCount, int maxGroupCount, String csvFile) throws IOException {

        long startTime = System.currentTimeMillis();

        DataScale[] ds = populateDataScale();

        List<String[]> tenantIds = CsvParser.csvReader(csvFile);

        try {
            for (String[] s : tenantIds) {
                if (s != null && s.length > 0 && !s[0].trim().isEmpty()) {
                    List<UsersAndGroups> usersAndGroups = getUsersAndGroupsEntities(maxUserCount, maxGroupCount, ds);
                    for (UsersAndGroups u : usersAndGroups) {
                        String fName = format(System.getProperty("user.dir") + "/data/json/" + "/" + s[0] + "/" +
                                "usersAndGroups%sx%s.json", u.getUsers().length, u.getGroups().length);
                        File f = new File(fName);
                        if (!f.getParentFile().exists()) {
                            f.getParentFile().mkdirs();
                        }
                        File dir = new File(f.getParentFile(), f.getName());
                        MAPPER.writeValue(dir, u);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long diff = (endTime-startTime)/1000;

        System.out.printf("Time Taken in seconds to create usersAndGroups json files :: %d%n", diff);
        System.out.println("UsersAndGroups json files are created under following directory: " +System.getProperty("user.dir") + "/data/json");

    }

    private static DataScale[] populateDataScale() {

        List<DataScale> dataScales = new ArrayList<>();
        String[] nodes = {"a","b","c","d","e","f","g","h","i","j"};
        for(String node : nodes){
            try{
                DataScale d = PropertyHelper.getUserAndGroupEachSetProperties(node);
                dataScales.add(d);
            }catch (Exception e){
                break;
            }
        }
        return dataScales.toArray(new DataScale[dataScales.size()]);
    }

    private static List<UsersAndGroups> getUsersAndGroupsEntities(int maxUserCount, int maxGroupCount, DataScale[] ds) {
        
        List<UsersAndGroups> usersAndGroups = new ArrayList<>();

        //Create max users
        List<User> users = new ArrayList<User>() {{
            for (int i = 0; i < maxUserCount; i++) {
                User user = new User();
                add((user));
            }
        }};

       //Create max groups
        List<Group> groups = new ArrayList<Group>() {{
            for (int i = 0; i < maxGroupCount; i++) {
                add(new Group()); 
            }
        }};
        
        for(DataScale d : ds){
            usersAndGroups.add(createUsersAndGroupsEntity(users.subList(0,d.getMaxEntityA()),groups.subList(0, d.getMaxEntityB()),d));
        }
        
        return usersAndGroups;
        
    }

    private static UsersAndGroups createUsersAndGroupsEntity(List<User> users, List<Group> groups, DataScale ds) {
        
        UsersAndGroups usersAndGroups = new UsersAndGroups();
        
        Map<Integer,Integer> usersAndGroupDistribution = ds.getEntityDistribution();
        
        List<String> userDns = new ArrayList<>();
        for(User user:users){
            userDns.add(user.getDn());
        }
        
        List<List<String>> userDnsDistributionSubList = new ArrayList<>();
        int counter = 0;
        for(Map.Entry<Integer,Integer> entry: usersAndGroupDistribution.entrySet()){
            for (int j = 0; j< entry.getValue(); j++){
                userDnsDistributionSubList.add(userDns.subList(counter, counter+ entry.getKey()));
                counter=counter+entry.getKey();
            }
        }
        usersAndGroups.setUsers(users.toArray(new User[users.size()]));
        if(groups.size()!=userDnsDistributionSubList.size()){
            throw new RuntimeException("Your given group distribution is not matching with total number of groups for an entity.");
        }

        for(int i = 0;i<groups.size();i++){
            groups.get(i).setMembers(userDnsDistributionSubList.get(i).toArray(new String[userDnsDistributionSubList.get(i).size()]));
        }

        usersAndGroups.setGroups(groups.toArray(new Group[groups.size()]));

        return usersAndGroups;
    }

    public static void main(String[] args) throws IOException {
        usersAndGroupsJavaObjToJson(Integer.parseInt(PropertyHelper.getTrimmedProperties("max.user.onerun")),
                Integer.parseInt(PropertyHelper.getTrimmedProperties("max.group.onerun")),PropertyHelper.getTrimmedProperties("csv.file.path"));
    }
}
