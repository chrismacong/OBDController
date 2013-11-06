package com.fix.obd.util.obd;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLReader {
	private Document document;
	private Map<Integer , OBDElement> elementMap;
	
	public XMLReader(String filename){
		SAXReader saxReader = new SAXReader();
		elementMap = new HashMap<Integer , OBDElement>();
		try {
			document = saxReader.read(new File(getClass().getClassLoader().getResource(filename).getPath()));
			parse();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parse(){
		Element root = document.getRootElement();
		Iterator<Element> iter = root.elementIterator();
		while(iter.hasNext()){
			Element t = iter.next();
			OBDElement ele = new OBDElement();
			String[] values = t.getStringValue().split("\n");

			ele.id = Integer.parseInt(t.attributeValue("id").trim());
			ele.name = values[1].trim();
			ele.length = Integer.parseInt(values[2].trim());
			ele.handler = values[3].trim();
			ele.introduction = values[4].trim();
			
			elementMap.put(ele.id, ele);
		}
	}
	
	public int getMapSize(){
		return elementMap.size();
	}
	
	public String getElementName(int index) {
		// TODO Auto-generated method stub
		if(elementMap.containsKey(index)){
			return elementMap.get(index).name;
		}
		return null;
	}

	public int getElementLength(int index) {
		// TODO Auto-generated method stub
		if(elementMap.containsKey(index)){
			return elementMap.get(index).length;
		}
		return -1;
	}

	public String getElementHandler(int index) {
		// TODO Auto-generated method stub
		if(elementMap.containsKey(index)){
			return elementMap.get(index).handler;
		}
		return null;
	}
	
	public String getElementIntroduction(int index) {
		// TODO Auto-generated method stub
		if(elementMap.containsKey(index)){
			return elementMap.get(index).introduction;
		}
		return null;
	}
}
class OBDElement{
	public int id;
	public String name;
	public int length;
	public String handler;
	public String introduction;
}
