package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	private Properties properties;
	
	public PropertiesManager() throws IOException {
		this.properties = loadProperties();
	}
	
	public Properties loadProperties() throws IOException {
		properties = new Properties();
		
		String relativePath = "/src/test/resources/utilities/config.properties";
		String directoryPath = System.getProperty("user.dir");
		
		FileInputStream inputStream = new FileInputStream(directoryPath + relativePath);
		
		properties.load(inputStream);
		
		return properties;
	}
	
	public String getProperty(String property) {
		return properties.getProperty(property);
	}
	
}
