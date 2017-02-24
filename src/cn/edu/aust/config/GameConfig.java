package cn.edu.aust.config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig {
	private List<DialogConfig> dialogsConfig;
	
	

	public GameConfig() throws Exception{
		SAXReader reader=new SAXReader();
		Document doc=reader.read("config/config.xml");
		Element game=doc.getRootElement();
		List<Element> dialogs=game.elements("dialog");
		dialogsConfig =new ArrayList<DialogConfig>();
		
		for(Element dialog: dialogs){
			DialogConfig dc=new DialogConfig();
			dc.setClassName(dialog.attributeValue("className"));
			dc.setX(Integer.parseInt(dialog.attributeValue("x")));
			dc.setY(Integer.parseInt(dialog.attributeValue("y")));
			dc.setW(Integer.parseInt(dialog.attributeValue("w")));
			dc.setH(Integer.parseInt(dialog.attributeValue("h")));
			dialogsConfig.add(dc);
			
		}
	}
	
	public List<DialogConfig> getDialogsConfig() {
		return dialogsConfig;
	}
}
