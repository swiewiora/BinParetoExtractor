/**
 * @author Sebastian Wiewióra
 * @date 24.03.2017
 */

package com.binpareto;

import com.console.ArgumentHandler;
import com.console.PrintToConsole;
import com.fileio.ReadXML;
import com.fileio.WriteCSV;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileNotFoundException;
import java.util.*;

public class BinParetoCalculator {
  private Map <Integer, ParetoObject> pareto;
  private ReadXML readXML;
  private ArgumentHandler argHandler;
  
  public BinParetoCalculator (String[] args) {
    argHandler = new ArgumentHandler(args);
    argHandler.handleArguments();
    
    pareto = new HashMap <> ();
    
    readXML();
  
    readBinDefinitions();
    countBins();
    calculatePercentage();
    
    print();
  
    if (argHandler.getOutputOption() ) {
      writeCSV();
    }
  }
  
  private void readXML() {
    readXML = new ReadXML (argHandler.getInputFilePath() );
    readXML.buildDocument();
  }
  
  private void print() {
    PrintToConsole console = new PrintToConsole (pareto);
    if (argHandler.getHeaderOption() ) {
      console.printHeader();
    }
    console.print();
  }
  
  private void writeCSV() {
    WriteCSV writeCSV;
    try {
      writeCSV = new WriteCSV (pareto, argHandler.getOutputFilePath() );
      if (argHandler.getHeaderOption() ) {
        writeCSV.writeHeader();
      }
      writeCSV.buildCSV();
      writeCSV.writeToFile();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
  
  private void readBinDefinitions() {
    NodeList binDefinitions = readXML.getBinDefinitions();
    for (int i = 0; i < binDefinitions.getLength(); i++) {
      Node bin = binDefinitions.item(i);
      if (bin.getNodeType() == Node.ELEMENT_NODE) {
        // get Bin number
        Integer key = Integer.parseInt(bin.getTextContent());
        
        // get Bin type
        Element eElement = (Element) bin;
        String typeString = eElement.getAttribute("Type");
        Boolean type = Objects.equals(typeString, "Pass");
        
        // Write everything to Map
        pareto.put(key, new ParetoObject (type));
      }
    }
  }
  
  private void countBins() {
    List <Integer> bins = new ArrayList <> ();
    NodeList binCodeMap = readXML.getBinCodeMap();
    
    for (int i = 0; i < binCodeMap.getLength(); i++) {
      Node row = binCodeMap.item(i);
      bins.clear();
      if (row.getNodeType() == Node.ELEMENT_NODE) {
        String rowString = row.getTextContent();
        String[] tokens = rowString.split(" ");
      
        for (String token : tokens) {
          if (isInteger(token) ) {
            bins.add(Integer.parseInt(token) );
          }
        }
  
        for (Integer bin : bins) {
          ParetoObject element = pareto.get(bin);
          if (element != null) {
            element.incrementCount ();
          }
        }
      }
    }
  }
  
  private void calculatePercentage() {
    int sum = 0;
    for (Map.Entry <Integer, ParetoObject> entry : pareto.entrySet() ) {
      sum += entry.getValue().getCount();
    }
  
    for (Map.Entry <Integer, ParetoObject> entry : pareto.entrySet()) {
      Double count = entry.getValue().getCount().doubleValue();
      entry.getValue().setPercentage (count / sum * 100);
    }
  }
  
  private static boolean isInteger(String str) {
    if (str == null) {
      return false;
    }
    int length = str.length();
    if (length == 0) {
      return false;
    }
    
    for (int i = 0; i < length; i++) {
      char c = str.charAt(i);
      if (c < '0' || c > '9') {
        return false;
      }
    }
    return true;
  }
}
