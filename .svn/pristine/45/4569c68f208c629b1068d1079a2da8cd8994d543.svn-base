package com.fix.obd.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class CityNumPropertiesUtil {
	private Properties p;
	public CityNumPropertiesUtil(){
		try {
			p = new Properties();
			InputStream is=this.getClass().getResourceAsStream("/citynum.properties"); 
			p.load(is);  
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getCityNumByCity(String city){
		return p.getProperty(this.toUnicode(city));
	}
	
	private static String toUnicode(String s) {
		String s1 = "";
		for (int i = 0; i < s.length(); i++) {
			s1 += Integer.toHexString(s.charAt(i) & 0xffff);

		}
		return s1;
	}
}
