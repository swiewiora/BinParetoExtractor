/**
 * @author Sebastian Wiewióra
 * @date 24.03.2017
 */

package com.console;

import org.apache.commons.cli.*;

import java.util.List;

public class ArgumentHandler {
  private String[] args;
  private String outputFilePath;
  private String inputFilePath;
  private Boolean headerOption;
  private Boolean outputOption;
  
  public ArgumentHandler(String[] args) {
    this.args = args;
  }
  
  public void handleArguments() {
    Options options = new Options();
  
    Option output = new Option("f", "output", true, "output file");
    output.setRequired(false);
    options.addOption(output);
  
    Option header = new Option("h", "header", false, "print headers");
    header.setRequired(false);
    options.addOption(header);
  
    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    CommandLine cmd;
  
    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      formatter.printHelp("WmxmlBinParetoExtractor " +
          "[-f output_file.csv] [-h] input_file.xml", options);
    
      System.exit(1);
      return;
    }
    
    outputOption = cmd.hasOption("f");
    outputFilePath = cmd.getOptionValue("output");
    headerOption = cmd.hasOption("h");
  
    List <String> argList = cmd.getArgList();
    
    if (argList.size() == 1) {
      inputFilePath = argList.get(0);
    } else {
      formatter.printHelp("WmxmlBinParetoExtractor " +
          "[-f output_file.csv] [-h] input_file.xml", options);
  
      System.exit(1);
    }
  }
  
  public String getInputFilePath() {
    return inputFilePath;
  }
  
  public String getOutputFilePath() {
    return outputFilePath;
  }
  
  public Boolean getHeaderOption() {
    return headerOption;
  }
  
  public Boolean getOutputOption() {
    return outputOption;
  }
}
