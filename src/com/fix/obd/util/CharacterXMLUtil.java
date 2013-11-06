package com.fix.obd.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fix.obd.web.model.util.CharacterIterator;

public class CharacterXMLUtil {
	private Document document;
	
	public CharacterXMLUtil(String filepath){
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(new File(filepath));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<CharacterIterator> parse(){
		ArrayList<CharacterIterator> list = new ArrayList<CharacterIterator>();
		Element root = document.getRootElement();
		Iterator<Element> iter = root.elementIterator();
		while(iter.hasNext()){
			Element t = iter.next();
			CharacterIterator cha = new CharacterIterator();
			cha.setCid(t.attributeValue("id"));
			String[] strs = t.getStringValue().split("\n");
			cha.setCname(strs[1].trim());
			cha.setClength(strs[2].trim());
			cha.setCdecode(strs[3].trim());
			cha.setCnotice(strs[4].trim());
			list.add(cha);
		}
		return list;
	}
//	public static void main(String args[]){
//		CharacterXMLUtil xml = new CharacterXMLUtil("src/OBD_Character.xml");
//		ArrayList<CharacterIterator> list = xml.parse();
//		System.out.println(list.get(50).getCnotice());
//	}
}