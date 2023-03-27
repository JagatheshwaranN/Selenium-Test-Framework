package com.qa.taf.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import org.testng.Assert;

public class FileReaderUtil {

	public static Properties properties = new Properties();

	public static void loadPropertyFile() {

		try (FileInputStream fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + ConstantUtil.Config_File_Path)) {
			properties.load(fileInputStream);
			System.out.println("Configuration property file loaded !!");
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static String getDataFromPropFile(String key) {

		String data = null;
		try {
			if (Optional.ofNullable(key).isPresent()) {
				data = properties.getProperty(key).strip();
			}
		} catch (NullPointerException ex) {
			Assert.fail("The key - " + key + " - is not present in the configuration properties file" + "\n"
					+ ex.getMessage());
		}
		return data;
	}
}
