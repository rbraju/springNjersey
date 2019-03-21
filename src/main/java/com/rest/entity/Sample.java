package com.rest.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

public class Sample {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		String filename = "/Users/rajbhoop/Documents/workspace_github/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringNJersey/WEB-INF/classes/db/users.txt";
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filename)));
		Map<Integer, User> x = (Map<Integer, User>) ois.readObject();
		System.out.println(x);
		ois.close();
	}
}