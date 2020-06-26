package com.adsync.data.utilities;

import com.adsync.data.generators.DataScale;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class PropertyHelper {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(new File("config.properties")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while  loading property file.", e);
        }
    }

    public static String getTrimmedProperties(String key){
        String value= properties.getProperty(key);
        return null == value ? null : value.trim();
    }

    public static DataScale getUserAndGroupEachSetProperties(String node) {
        DataScale ds = new DataScale();
        if(getTrimmedProperties("ds.max.user."+node) == null){
            throw new RuntimeException("I am finished");
        }
        ds.setMaxEntityA(Integer.parseInt(getTrimmedProperties("ds.max.user."+node)));
        ds.setMaxEntityB(Integer.parseInt(getTrimmedProperties("ds.max.group."+node)));
        Map<Integer,Integer> entityDistributionMap = new HashMap<Integer, Integer>();
        for(int i = 1 ; i<10; i++){
            try {
                String[] values = getTrimmedProperties("ds.distribution.u.g." + node + ".set." + i).split(",");
                entityDistributionMap.put(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
            }catch (Exception e){
                e.getMessage();
            }
        }
        ds.setEntityDistribution(entityDistributionMap);
        return ds;
    }

    public static DataScale getDeviceAndOrganizationEachSetProperties(String node) {
        DataScale ds = new DataScale();
        if(getTrimmedProperties("ds.max.devices."+node) == null){
            throw new RuntimeException("I am finished");
        }
        ds.setMaxEntityA(Integer.parseInt(getTrimmedProperties("ds.max.devices."+node)));
        ds.setMaxEntityB(Integer.parseInt(getTrimmedProperties("ds.max.orgs."+node)));
        Map<Integer,Integer> entityDistributionMap = new HashMap<Integer, Integer>();
        for(int i = 1 ; i<10; i++){
            try {
                String[] values = getTrimmedProperties("ds.distribution.d.o." + node + ".set." + i).split(",");
                entityDistributionMap.put(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
            }catch (Exception e){
                e.getMessage();
            }
        }
        ds.setEntityDistribution(entityDistributionMap);
        return ds;
    }


}
