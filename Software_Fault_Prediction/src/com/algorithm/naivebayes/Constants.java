/**
 * 
 */
package com.algorithm.naivebayes;

public interface Constants {
String csv = "csv";
String txt = "txt";
String xml = "xml";
String Tab = "\t";
String comma = ",";
String semicolon = ";";
String regex = "[$&+:;=?@#|'<>.^*()%!-\"\\s]";


public <T extends Object> T cleanData(T data)throws Exception;

}
