package com.fix.obd.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IdPropertiesUtil {
	private Properties p;
	public IdPropertiesUtil(){
		try {
			p = new Properties();
			InputStream is=this.getClass().getResourceAsStream("/id2protocol.properties"); 
			p.load(is);  
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getProtocolById(String id){
		return p.getProperty(id);
	}
	
}
