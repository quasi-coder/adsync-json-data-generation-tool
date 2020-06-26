package com.adsync.data.generators;

import com.adsync.data.pojos.DeviceUnit;
import com.adsync.data.pojos.DevicesAndOrganizations;
import com.adsync.data.pojos.OrganizationalUnit;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.lang.String.format;
import com.adsync.data.utilities.*;

public class DevicesAndOrganizationsWrapper {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static void devicesAndOrganizationsObjToJson(int maxDeviceCount, int maxOrganizationCount, String csvFile) throws IOException {

        long startTime = System.currentTimeMillis();

        DataScale[] ds = populateDataScale();

        List<String[]> tenantIds = CsvParser.csvReader(csvFile);

        try {
            for (String[] s : tenantIds) {
                if (s != null && s.length > 0 && !s[0].trim().isEmpty()) {
                    List<DevicesAndOrganizations> devicesAndOrganizations = getDeviceAndOrganizationEntities(maxDeviceCount, maxOrganizationCount, ds);
                    for (DevicesAndOrganizations d : devicesAndOrganizations) {
                        String fName = format(System.getProperty("user.dir") + "/data/json/" + "/" + s[0] + "/" +
                                "devicesAndOrganization%sx%s.json", d.getDevices().length, d.getOrganizationalUnits().length);
                        File f = new File(fName);
                        if (!f.getParentFile().exists()) {
                            f.getParentFile().mkdirs();
                        }
                        File dir = new File(f.getParentFile(), f.getName());
                        MAPPER.writeValue(dir, d);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long diff = (endTime-startTime)/1000;

        System.out.printf("Time Taken in seconds to create devicesAndOrgs json files :: %d%n", diff);
        System.out.println("DevicesAndOrganization json files are created under following directory: " +System.getProperty("user.dir") + "/data/json");
    }

    private static DataScale[] populateDataScale() {

        List<DataScale> dataScales = new ArrayList<>();
        String[] nodes = {"a","b","c","d","e","f","g","h","i","j"};
        for(String node : nodes){
            try{
                DataScale d = PropertyHelper.getDeviceAndOrganizationEachSetProperties(node);
                dataScales.add(d);
            }catch (Exception e){
                break;
            }
        }
        return dataScales.toArray(new DataScale[dataScales.size()]);
    }

    private static List<DevicesAndOrganizations> getDeviceAndOrganizationEntities(int maxDeviceCount, int maxOrganizationCount, DataScale[] ds) {

        List<DevicesAndOrganizations> devicesAndOrganizations = new ArrayList<>();

        //Create max devices
        List<DeviceUnit> devices = new ArrayList<DeviceUnit>() {{
            for (int i = 0; i < maxDeviceCount; i++) {
                DeviceUnit deviceUnit = new DeviceUnit();
                add((deviceUnit));
            }
        }};

        //Create max organizations
        List<OrganizationalUnit> organizations = new ArrayList<OrganizationalUnit>() {{
            for (int i = 0; i < maxOrganizationCount; i++) {
                add(new OrganizationalUnit());
            }
        }};

        for(DataScale d : ds){
            devicesAndOrganizations.add(createDeviceAndOrganizationEntity(devices.subList(0,d.getMaxEntityA()),organizations.subList(0, d.getMaxEntityB()),d));
        }

        return devicesAndOrganizations;

    }

    private static DevicesAndOrganizations createDeviceAndOrganizationEntity(List<DeviceUnit> devices, List<OrganizationalUnit> organizations, DataScale ds) {

        DevicesAndOrganizations devicesAndOrganizations = new DevicesAndOrganizations();

        Map<Integer,Integer> devicesAndOrganizationDistribution = ds.getEntityDistribution();

        List<String> deviceDns = new ArrayList<>();
        for(DeviceUnit deviceUnit:devices){
            deviceDns.add(deviceUnit.getDn());
        }

        List<List<String>> deviceDnsDistributionSubList = new ArrayList<>();
        int counter = 0;
        for(Map.Entry<Integer,Integer> entry: devicesAndOrganizationDistribution.entrySet()){
            for (int j = 0; j< entry.getValue(); j++){
                deviceDnsDistributionSubList.add(deviceDns.subList(counter, counter+ entry.getKey()));
                counter=counter+entry.getKey();
            }
        }
        devicesAndOrganizations.setDevices(devices.toArray(new DeviceUnit[devices.size()]));
        if(organizations.size()!=deviceDnsDistributionSubList.size()){
            throw new RuntimeException("Your given org distribution is not matching with total number of orgs for an entity.");
        }

        for(int i = 0;i<organizations.size();i++){
            organizations.get(i).setChildDevices(deviceDnsDistributionSubList.get(i).toArray(new String[deviceDnsDistributionSubList.get(i).size()]));
        }

        devicesAndOrganizations.setOrganizationalUnits(organizations.toArray(new OrganizationalUnit[organizations.size()]));

        return devicesAndOrganizations;
    }

    public static void main(String[] args) throws IOException {
        devicesAndOrganizationsObjToJson(Integer.parseInt(PropertyHelper.getTrimmedProperties("max.devices.onerun")),
                Integer.parseInt(PropertyHelper.getTrimmedProperties("max.orgs.onerun")),PropertyHelper.getTrimmedProperties("csv.file.path"));
    }
}
