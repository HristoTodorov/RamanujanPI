package com.rsa.automation.core.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class InitializeData {
	public Properties init() throws IOException {
		Properties defaultProperties = new  Properties();
		InputStream is = getClass().getClassLoader()
				.getResourceAsStream(CommonConstants.DEFAULT_CONFIG_FILE);
		if (is != null) {
			defaultProperties.load(is);
		} else {
			throw new FileNotFoundException("Property file '" + CommonConstants.DEFAULT_CONFIG_FILE + "' not found in the classpath");
		}
		return defaultProperties;
	}
}
