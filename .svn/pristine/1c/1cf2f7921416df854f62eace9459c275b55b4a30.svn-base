package com.fix.obd.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProtocolPropertiesUtil {
	private static Properties p;
	public ProtocolPropertiesUtil(){
		try {
			p = new Properties();
			InputStream is=this.getClass().getResourceAsStream("/protocol2id.properties"); 
			p.load(is);  
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getIdByProtocol(String protocol){
		return p.getProperty(protocol);
	}
}
