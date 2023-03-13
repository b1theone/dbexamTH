package com.server.test;

public class TestFunction {

	public static void main(String[] args) {
		String ext = "xxx.jpg".substring("xxx.jpg".lastIndexOf("."));
		ext = ext.replace(".", "");
		
		System.out.println(ext);
	}

}
