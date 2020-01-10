/**
 * 
 */
package com.algorithm.naivebayes;

/**
 * @author asaha
 *
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NBmain {
private ArrayList<String> FrequentItemsetD = null;
private ArrayList<String> FrequentItemsetND = null;
private Classifier<String, String> bayes = new BayesClassifier<String, String>();

public NBmain(ArrayList<String> updatedfrequentItemD,ArrayList<String> updatedfrequentItemND) throws Exception {
	if(FrequentItemsetD==null && FrequentItemsetND==null){
		this.FrequentItemsetD = updatedfrequentItemD;
		this.FrequentItemsetND = updatedfrequentItemND;
	}
	init();
}


private void init() throws Exception{
	
	// Create a new bayes classifier with string categories and string features.
	

	// Two examples to learn from.

	// Learn by classifying examples.
	// New categories can be added on the fly, when they are first used.
	// A classification consists of a category and a list of features
	// that resulted in the classification in that category.
	bayes.learn("positive", FrequentItemsetD);
	bayes.learn("negative", FrequentItemsetND);


	// Here are two unknown sentences to classify.
//	String[] unknownText1 = "today is a sunny day".split("\\s");
//	String[] unknownText2 = "there will be rain".split("\\s");
//
//	System.out.println( // will output "positive"
//	    bayes.classify(Arrays.asList(unknownText1)).getCategory());
//	System.out.println( // will output "negative"
//	    bayes.classify(Arrays.asList(unknownText2)).getCategory());
//
//	// Get more detailed classification result.
//	((BayesClassifier<String, String>) bayes).classifyDetailed(
//	    Arrays.asList(unknownText1));

	// Change the memory capacity. New learned classifications (using
	// the learn method) are stored in a queue with the size given
	// here and used to classify unknown sentences.
	bayes.setMemoryCapacity(500);
}


public static double calculateMean(ArrayList<Integer> data){
	double MEAN = 0;
	int size = data.size();
	Iterator< Integer> itr = data.iterator();
	while(itr.hasNext()){
		MEAN+=itr.next();
	}
	MEAN = MEAN/size;
	return MEAN;
}
public Set<String> getFeatures()
{
	return bayes.getFeatures();
}
}
