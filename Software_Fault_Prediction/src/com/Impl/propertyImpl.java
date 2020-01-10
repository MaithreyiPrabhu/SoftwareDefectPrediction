package com.Impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
public class propertyImpl {
private static String loadProperties;
private static Properties prop = new Properties();
private static InputStream input = null;


static{
	loadProperties();
}
	public static String getproperty(String propertyName){
		String propertydata = null;
		propertydata = prop.getProperty(propertyName);
		return propertydata;
	}
	public static void setproperty(String propertyName,String propertyValue){
		prop.setProperty(propertyName, propertyValue);
	}
	
	private static void loadProperties(){
		try{
			input = new FileInputStream(new File(Constants.configFileName));
		if(input!=null){
			prop.load(input);
		}
		else{
			System.out.println("Cannot Load the configuration file");
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
//Property class is used to get all system properties as file address,user directory
//used only for finding user directory for fetching datasets
//in Main method and also in Partition.java