package com.Impl;
//declare the package where file belongs

import java.io.*;
//java library for IO operations
import weka.core.*;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;
//Weka library for Discretization 

public class DiscretizeTest {
//DiscretizeTest.run() method is invoked by scanData method of Main

  protected static Instances load(String filename) throws Exception {
	  
    Instances       result;
    BufferedReader  reader;
	//Instances and BufferedReader are classes present in Weka
    reader = new BufferedReader(new FileReader(filename));
	//read dataset into buffer (reader is object of BufferedReader)
    result = new Instances(reader);
	//result contains all instances present in buffer (reader is object of BufferedReader)
    result.setClassIndex(result.numAttributes() - 1);
    reader.close();

    return result;
  }//load method basically loads the instances from dataset and returns them

  
  protected static void save(Instances data, String filename) throws Exception {
    BufferedWriter  writer;

    writer = new BufferedWriter(new FileWriter(filename));
    writer.write(data.toString());
    writer.newLine();
    writer.flush();
    writer.close();
	//take Discretised values as "Data" then convert to string,write to new file,add newline and close file.
  }//Save writes the Discretized data as string into new file(discretized dataset file)
  
  /**
   * Takes four arguments:
   * <ol>
   *   <li>input train file</li>
   *   <li>input test file</li>
   *   <li>output train file</li>
   *   <li>output test file</li>
   * </ol>
   *
   * @param args        the commandline arguments
   * @throws Exception  if something goes wrong
   */
  public static void run(String trainpath,String outputpath) throws Exception {
	  //run() method called by scandata with two file names as attributes
	  //example: DiscretizeTest.run("ar4.arff", "ar4.out.arff");
    Instances     inputTrain;
    Instances     outputTrain;
    Discretize    filter;
    //Discretize and Instances are classes of Weka
    // load data (class attribute is assumed to be last attribute)
    inputTrain = load(trainpath);
	//trainpath is address of dataset
	//load stores instances od datset into inputTrain
//    inputTest  = load(inputtestData);

    // setup filter
    filter = new Discretize();
	filter.setInputFormat(inputTrain);

    // apply filter
    outputTrain = Filter.useFilter(inputTrain, filter);
	
//    outputTest  = Filter.useFilter(inputTest,  filter);

    // save output
    save(outputTrain, outputpath);
	//all Above steps discretize according to weka tool 
	//ans save method stores discretised data as string into outputfile
	//outputpath is output file address (i.e.,ar4.out.arff )
//    save(outputTest,  outputTest);
  }
}	