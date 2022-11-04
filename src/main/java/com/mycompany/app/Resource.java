package com.mycompany.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class Resource {

    private static final Logger LOGGER = LoggerFactory.getLogger(Resource.class);

    private static String propMin;

    private static String propMax;

    private static String propInc;

    public String getPropMin() {
        return propMin;
    }

    public String getPropMax() {
        return propMax;
    }

    public String getPropInc() {
        return propInc;
    }

    public void setPropMin(String propMin) {
        Resource.propMin = propMin;
    }

    public void setPropMax(String propMax) {
        Resource.propMax = propMax;
    }

    public void setPropInc(String propInc) {
        Resource.propInc = propInc;
    }


    static {
        try {
            Properties prop = new Properties();
            prop.load(Resource.class.getClassLoader().getResourceAsStream("application.properties"));
            propMax = prop.getProperty("max");
            propMin = prop.getProperty("min");
            propInc = prop.getProperty("inc");
        } catch (IOException e) {
            LOGGER.error("Properties file not load correct {} \n {}",e.getMessage(),e.getStackTrace());
        }
    }
}
