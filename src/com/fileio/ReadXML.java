package com.fileio;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ReadXML {
  private String filename;
  private Document doc;
  
  public ReadXML (String filename) {
    this.filename = filename;
  }
  
  public NodeList getBinDefinitions() {
    return doc.getElementsByTagName("Bin");
  }
  
  public NodeList getBinCodeMap() {
    return doc.getElementsByTagName("Row");
  }
  
  public void buildDocument() {
    File fXmlFile = new File (filename);
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = null;
    try {
      dBuilder = dbFactory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
    try {
      if (dBuilder != null) {
        doc = dBuilder.parse(fXmlFile);
      }
    } catch (SAXException | IOException e) {
      e.printStackTrace();
    }
    doc.getDocumentElement().normalize();
  }
}
