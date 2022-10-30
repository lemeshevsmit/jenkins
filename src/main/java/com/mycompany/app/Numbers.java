package com.mycompany.app;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;


public class Numbers {

    private final Resource resource;
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger("com.mycompany.app.Numbers");
    private static final String FORMAT = "| {} * {} = {}";

    public Numbers(boolean status, Resource resource) {
        this.resource = resource;
        if (!status){
            LOGGER.setLevel(Level.OFF);
        }
    }

    public String calculate() throws NullPointerException, NumberFormatException {
        LOGGER.debug("Start check input parameters");
        String propMax = resource.getPropMax();
        String propMin = resource.getPropMin();
        String propInc = resource.getPropInc();
        String type = System.getProperty("type");
        if (type == null) {
            calculateInt(propMax, propMin, propInc);
            return "integer";
        } else {
            switch (type) {
                case "byte": {
                    calculateByte(propMax, propMin, propInc);
                    break;
                }
                case "short": {
                    calculateShort(propMax, propMin, propInc);
                    break;
                }
                case "int": {
                    calculateInt(propMax, propMin, propInc);
                    break;
                }
                case "long": {
                    calculateLong(propMax, propMin, propInc);
                    break;
                }
                case "float": {
                    calculateFloat(propMax, propMin, propInc);
                    break;
                }
                case "double": {
                    calculateDouble(propMax, propMin, propInc);
                    break;
                }
                case "biginteger": {
                    calculateBigDecimal(propMax, propMin, propInc);
                    break;
                }
                case "bigdecimal": {
                    calculateBigInteger(propMax, propMin, propInc);
                    break;
                }
                default: {
                    calculateInt(propMax, propMin, propInc);
                    return "integer";
                }
            }
        }
        LOGGER.debug("End of program");
        return type;
    }

    private void calculateBigDecimal(String propMax, String propMin, String propInc) {
        BigDecimal min = new BigDecimal(propMin);
        BigDecimal max = new BigDecimal(propMax);
        BigDecimal inc = new BigDecimal(propInc);
        for (BigDecimal i = min; i.compareTo(max) <= 0; i = i.add(inc)) {
            for (BigDecimal j = min; j.compareTo(max) <= 0; j = j.add(inc)) {
                LOGGER.info(FORMAT, i, j, i.multiply(j));
            }
            LOGGER.info("");
        }
    }

    private void calculateBigInteger(String propMax, String propMin, String propInc) {
        BigInteger min = new BigInteger(propMin);
        BigInteger max = new BigInteger(propMax);
        BigInteger inc = new BigInteger(propInc);
        for (BigInteger i = min; i.compareTo(max) <= 0; i = i.add(inc)) {
            for (BigInteger j = min; j.compareTo(max) <= 0; j = j.add(inc)) {
                LOGGER.info(FORMAT, i, j, i.multiply(j));
            }
            LOGGER.info("");
        }
    }

    private void calculateDouble(String propMax, String propMin, String propInc) {
        double min = Double.parseDouble(propMin);
        double max = Double.parseDouble(propMax);
        double inc = Double.parseDouble(propInc);
        for (double i = min; i <= max; i = i + inc) {
            for (double j = min; j <= max; j = j + inc) {
                LOGGER.info(FORMAT, i, j, i * j);
            }
            LOGGER.info("");
        }
    }

    private void calculateFloat(String propMax, String propMin, String propInc) {
        float min = Float.parseFloat(propMin);
        float max = Float.parseFloat(propMax);
        float inc = Float.parseFloat(propInc);
        for (float i = min; i <= max; i = i + inc) {
            for (float j = min; j <= max; j = j + inc) {
                LOGGER.info(FORMAT, i, j, i * j);
            }
            LOGGER.info("");
        }
    }

    private void calculateLong(String propMax, String propMin, String propInc) {
        long min = Long.parseLong(propMin);
        long max = Long.parseLong(propMax);
        long inc = Long.parseLong(propInc);
        for (long i = min; i <= max; i = i + inc) {
            for (long j = min; j <= max; j = j + inc) {
                LOGGER.info(FORMAT, i, j, i * j);
            }
            LOGGER.info("");
        }
    }

    private void calculateShort(String propMax, String propMin, String propInc) {
        short min = Short.parseShort(propMin);
        short max = Short.parseShort(propMax);
        short inc = Short.parseShort(propInc);
        for (short i = min; i <= max; i = (short) (i + inc)) {
            for (short j = min; j <= max; j = (short) (j + inc)) {
                LOGGER.info(FORMAT, i, j, (short) (i * j));
            }
            LOGGER.info("");
        }
    }

    private void calculateByte(String propMax, String propMin, String propInc) {
        byte min = Byte.parseByte(propMin);
        byte max = Byte.parseByte(propMax);
        byte inc = Byte.parseByte(propInc);
        for (byte i = min; i <= max; i = (byte) (i + inc)) {
            for (byte j = min; j <= max; j = (byte) (j + inc)) {
                LOGGER.info(FORMAT, i, j, (byte) (i * j));
            }
            LOGGER.info("");
        }
    }

    private void calculateInt(String propMax, String propMin, String propInc) {
        int min = Integer.parseInt(propMin);
        int max = Integer.parseInt(propMax);
        int inc = Integer.parseInt(propInc);
        for (int i = min; i <= max; i = i + inc) {
            for (int j = min; j <= max; j = j + inc) {
                LOGGER.info(FORMAT, i, j, i * j);
            }
            LOGGER.info("");
        }
    }
}
