package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	
	public static Properties loadProperties(String fileName) throws IOException {
		Properties properties = new Properties();
		
		String relativePath = "/src/test/resources/utilities/" + fileName;
		String directoryPath = System.getProperty("user.dir");
		
		FileInputStream inputStream = new FileInputStream(directoryPath + relativePath);
		
		properties.load(inputStream);
		
		return properties;
	}
	
	public static String getProperty(Properties properties, String property) {
		return properties.getProperty(property);
	}
	
}
