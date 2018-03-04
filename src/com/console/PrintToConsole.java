/**
 * @author Sebastian Wiewióra
 * @date 24.03.2017
 */

package com.console;

import com.binpareto.ParetoObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

public class PrintToConsole {
  private Map <Integer, ParetoObject> pareto;
  
  public PrintToConsole(Map<Integer, ParetoObject> pareto) {
    this.pareto = pareto;
  }
  
  public void print() {
    DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
    
    for (Map.Entry <Integer, ParetoObject> entry : pareto.entrySet()) {
      Boolean booleanType = entry.getValue().getType();
      Integer bin = entry.getKey();
      Integer count = entry.getValue().getCount();
      Double percentage = entry.getValue().getPercentage();
  
      char type;
      if (booleanType) {
        type = 'P';
      } else {
        type = 'F';
      }
      
      System.out.println (type + "\t" + bin + "\t" + count + "\t" + df.format(percentage));
    }
  }
  
  public void printHeader() {
    System.out.println("P/F\t#\tCount\t%");
  }
}
