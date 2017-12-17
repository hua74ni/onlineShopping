package com.biz.platform.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * properties util
 */
public class PropertiesUtil {
	
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	
	private static final String file_name = "application.properties";
	
	/**
	 * load properties
	 * @param propertyFileName
	 * @return
	 */
	public static Properties loadProperties(String propertyFileName) {
		Properties prop = new Properties();
		InputStreamReader  in = null;
		try {
			URL url = null;
			ClassLoader loder = Thread.currentThread().getContextClassLoader();
			url = loder.getResource(propertyFileName); 
			in = new InputStreamReader(new FileInputStream(url.getPath()), "UTF-8");
			prop.load(in);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return prop;
	}

	public static String getString(String key) {
		Properties prop = loadProperties(file_name);
		if (prop!=null) {
			return prop.getProperty(key);
		}
		return null;
	}

}
