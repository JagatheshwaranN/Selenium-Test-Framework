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
		if (Optional.ofNullable(key).isPresent()) {
			data = properties.getProperty(key).strip();
		} else {
			System.out.println("Key - " + key + " is not present in the Configuration.Properties file");
			Assert.fail();
		}
		return data;
	}
}
