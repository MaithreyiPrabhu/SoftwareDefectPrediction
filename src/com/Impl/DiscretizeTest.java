package com.Impl;
import java.io.*;

import weka.core.*;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;


public class DiscretizeTest {


  protected static Instances load(String filename) throws Exception {
    Instances       result;
    BufferedReader  reader;

    reader = new BufferedReader(new FileReader(filename));
    result = new Instances(reader);
    result.setClassIndex(result.numAttributes() - 1);
    reader.close();

    return result;
  }

  
  protected static void save(Instances data, String filename) throws Exception {
    BufferedWriter  writer;

    writer = new BufferedWriter(new FileWriter(filename));
    writer.write(data.toString());
    writer.newLine();
    writer.flush();
    writer.close();
  }
  
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
    Instances     inputTrain;
    Instances     outputTrain;
    Discretize    filter;
    
    // load data (class attribute is assumed to be last attribute)
    inputTrain = load(trainpath);
//    inputTest  = load(inputtestData);

    // setup filter
    filter = new Discretize();
    filter.setInputFormat(inputTrain);

    // apply filter
    outputTrain = Filter.useFilter(inputTrain, filter);
//    outputTest  = Filter.useFilter(inputTest,  filter);

    // save output
    save(outputTrain, outputpath);
//    save(outputTest,  outputTest);
  }
}	