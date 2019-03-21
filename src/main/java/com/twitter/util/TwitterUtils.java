package com.twitter.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TwitterUtils {

	public static String getContentAsString(File file) {
		StringBuilder sb = new StringBuilder("");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
