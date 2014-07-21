package com.fix.obd.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fix.obd.web.model.util.FaultCodeIterator;

public class FaultCodeXMLUtil {
private Document document;
	
	public FaultCodeXMLUtil(){
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(new File(getClass().getClassLoader().getResource("faultcode_beta1.0.xml").getPath()));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<FaultCodeIterator> parseByIndex(String index){
		ArrayList<FaultCodeIterator> list = new ArrayList<FaultCodeIterator>();
		Element root = document.getRootElement();
		Iterator<Element> iter = root.elementIterator();
		while(iter.hasNext()){
			Element t = iter.next();
			FaultCodeIterator fc = new FaultCodeIterator();
			String[] strs = t.getStringValue().split("\n");
			fc.setIndex(strs[1].trim());
			fc.setFaultDetail(strs[2].trim());
			fc.setClassify(strs[3].trim());
			fc.setPriorty(strs[4].trim());
			fc.setSolution(strs[5].trim());
			list.add(fc);
		}
		ArrayList<FaultCodeIterator> result_list = new ArrayList<FaultCodeIterator>();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				if(list.get(i).getIndex().equals(index))
					result_list.add(list.get(i));
			}
		}
		return result_list;
	}
}
