/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author adas
 */
public class PropertiesHandler {
    
    private static final Logger log = LoggerFactory.getLogger(PropertiesHandler.class);
    
    public void setProperty(final String propertyName, final String propertyValue){
        
        FileOutputStream input = null;
        
        handleFile(input, new HandleStream<FileOutputStream>() {
            @Override
            public String handleStream(FileOutputStream output, Properties prop, String path) throws IOException {
                log.info("Looking for properties file");
                output = new FileOutputStream(path);
                log.info("Successfully found properties file");

                // set the properties value
                prop.setProperty(propertyName, propertyValue);

                // save properties to project root folder
                log.info("Saving new value to properties file");
                prop.store(output, null);
                log.info("Successfully saved new value to properties file");
                
                return "";
            }
        });
    }
    
    public String getProperty(final String propertyName){
        
        FileInputStream input = null;
        
        return handleFile(input, new HandleStream<FileInputStream>() {
            @Override
            public String handleStream(FileInputStream input, Properties prop, String path) throws IOException {
                log.info("Looking for properties file");
                input = new FileInputStream(path);
                log.info("Successfully found properties file");

                //load a properties file from class path, inside static method
                prop.load(input);

                //get the property value and print it out
                log.info("Reading value from properties file");
                return prop.getProperty(propertyName);
            }
        });
        
    }
    
    @FunctionalInterface
    private interface HandleStream<T>{
        String handleStream(T stream, Properties prop, String path) throws IOException;
    }
    
    private <T> String handleFile(T stream, HandleStream<T> streamHandler){
        Properties prop = new Properties();
        String result = "";
        try {
            String filename = "config.properties";
            String workingDir = System.getProperty("user.dir");
            Path path = Paths.get(workingDir, "build", "resources", "main", filename);
            
            result = streamHandler.handleStream(stream, prop, path.toString());
            
    	} catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(stream!=null){
                try {
                    stream.getClass().getMethod("close", null).invoke(stream, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
