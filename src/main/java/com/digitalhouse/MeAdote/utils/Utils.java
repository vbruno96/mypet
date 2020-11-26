package com.digitalhouse.MeAdote.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Utils {
	public static void refreshStaticContent(String filePath) throws IOException {
		String sourcePath = "src/main/resources/static/" + filePath;	
		String destinationPath = "target/classes/static/" + filePath;	
		
//		Path source = Paths.get(sourcePath);
//		Path destination = Paths.get(destinationPath);
		
//		Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
	}
}
