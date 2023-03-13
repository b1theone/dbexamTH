package com.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtil {

	public static Element getElement(String path, String id) {
		try {
			ClassPathResource cpr = new ClassPathResource(path);

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(cpr.getFile());

			doc.getDocumentElement().normalize();

			Element e = doc.getElementById(id);

			return e;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static NamedNodeMap getAttributes(Element e, String tagName) {
		try {
			NodeList nl = e.getElementsByTagName(tagName);

			Node n = nl.item(0);
			NamedNodeMap attributes = n.getAttributes();

			return attributes;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String getNodeValue(Element e, String tagName, String attrName) {
		try {
			return (getAttributes(e, tagName)).getNamedItem(attrName).getNodeValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
