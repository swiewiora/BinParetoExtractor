# BinParetoExtractor

THe purpose was to create Java command line application which would process attached XML or any other in the same format 
(no DTD or XSD provided) and output "bin pareto" data per specification below.

##Application input: 
-	app signature 'WmxmlBinParetoExtractor' [-f output_file.csv] [-h] input_file.xml
-	application requires one parameter which is path to input file (xml)
-	optionally output file can be provided with -f option in which case the bin pareto is saved into CSV (comma separated values) file. 
  If not provided, data are outputted to console output

##Input file description
-	provided WMXML file is an XML file which contains data collected during processing of chips in factory. 
  Chip is called Die and it resides on the flat almost round plate called Wafer where there are many of them. Each Die is electrically 
   tested and the result (called Bin) is stored in the Bin Map. Each Bin is identified by number and it has property 'pass' or 'fail' 
   (meaning good Die or bad Die). The WMXML contains some parameters of this Wafer and also it contains these Bin Definitions 
   (element BinDefinitions) and this Bin Map (in element BinCodeMap).

##Output specification
-	output is Bin Pareto which means a table/matrix of count and percentage of each Bin Value found in the Bin Map. 


##To build program in IntelliJ:
1. File -> Open...
2. Select project root path
3. Buld -> Build Artifacts...
4. Select WmxmlBinParetoExtractor:jar
5. Select Build

##To configure Artifact in IntelliJ:
1. File -> Save All.
2. Run driver or class with main method.
3. Open the Project Structure dialog (File -> Project Structure).
4. In the left-hand pane of the dialog, select Tab "Artifacts".
5. Click Add (green plus button near top of window).
6. Select JAR from Add drop down menu. Select "From modules with dependencies"
7. Select main class.
8. The radio button should be selecting "extract to the target JAR." Press OK.
9. Check the box "Build on make"
10. Press apply and OK.
11. From the main menu, select the build dropdown.
12. Select the option build artifacts.
13. Select WmxmlBinParetoExtractor:jar and one of the options.

##To add modules in IntelliJ:
1. Open the Project Structure dialog (File -> Project Structure)
2. In the left-hand pane of the dialog, select Modules.
3. In the pane to the right, select WmxmlBinParetoExtractor.
4. In the right-hand part of the dialog, on the Module page, select the Dependencies tab.
5. On the Dependencies tab, click Add (green plus button near top of window) and select Jars or directories.
6. In the dialog that opens, select the necessary files and folders (commons-cli-1.4.jar from lib directory)
7. Select the Export option.
8. Click OK in the Project Structure dialog.

##Description of directories:
- src directory contains source files of the project
- lib directory contains libraries and modules which must be included to project
- out directory contains output files produced by build process.
