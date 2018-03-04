/**
 * @author Sebastian Wiewióra
 * @date 24.03.2017
 */

package com.fileio;

import com.binpareto.ParetoObject;

import java.io.PrintWriter;
    
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

/**
 * Write generated Bin Paerto to file in CSV format
 */
public class WriteCSV {
  private PrintWriter pw;
  private StringBuilder sb;
  private Map <Integer, ParetoObject> pareto;
  
  public WriteCSV (Map <Integer, ParetoObject> pareto, String filename) throws FileNotFoundException,
      NullPointerException {
    if (pareto == null) {
      throw new NullPointerException("list is empty");
    }
  
    this.pareto = pareto;
    pw = new PrintWriter(new File(filename));
    sb = new StringBuilder();
  }
  
  public void writeHeader() {
    sb.append("P/F,#,Count,%");;
  }
  
  public void buildCSV(){
  
    DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
  
    for (Map.Entry <Integer, ParetoObject> entry : pareto.entrySet()) {
      Boolean booleanTypa = entry.getValue().getType();
      Integer bin = entry.getKey();
      Integer count = entry.getValue().getCount();
      Double percentage = entry.getValue().getPercentage();
  
      char type;
      if (booleanTypa) {
        type = 'P';
      } else {
        type = 'F';
      }
  
      if (sb.length() != 0) {
        sb.append(',');
        sb.append(System.lineSeparator());
      }
      
      sb.append(type);
      sb.append(',');
      sb.append(bin);
      sb.append(',');
      sb.append(count);
      sb.append(',');
      sb.append(df.format(percentage));
    }
  }
  
  public void writeToFile(){
    pw.write(sb.toString());
    pw.close();
  }
}
