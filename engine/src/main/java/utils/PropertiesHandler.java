/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import engine.Entity.LoanGiver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
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
        Properties prop = new Properties();
	OutputStream output = null;

	try {            
            log.info("Looking for properties file");
            String filename = "application.properties";
            String workingDir = System.getProperty("user.dir");
            Path path = Paths.get(workingDir, "build", "resources", "main", filename);
            output = new FileOutputStream(path.toString());
            log.info("Successfully found properties file");
                     
            // set the properties value
            prop.setProperty(propertyName, propertyValue);

            // save properties to project root folder
            log.info("Saving new value to properties file");
            prop.store(output, null);
            log.info("Successfully saved new value to properties file");

	} catch (IOException io) {
            io.printStackTrace();
	} finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

	}
    }
    
    public String getProperty(final String propertyName){
        Properties prop = new Properties();
    	InputStream input = null;
    	String result = "";
        
    	try {
            log.info("Looking for properties file");
            String filename = "application.properties";
            String workingDir = System.getProperty("user.dir");
            Path path = Paths.get(workingDir, "build", "resources", "main", filename);
            input = new FileInputStream(filename);
            log.info("Successfully found properties file");

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            log.info("Reading value from properties file");
            result = prop.getProperty(propertyName);

    	} catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return result;
    }
}
