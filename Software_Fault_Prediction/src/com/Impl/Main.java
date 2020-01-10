package com.Impl;
//declare the package where file belongs

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
//importing java libraries for dynamic arrays,scanner(to read and tokenise input data)
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.gui.beans.DataSource;
import weka.gui.beans.Loader;
//import weka library for Algorithms-NB

public class Main {
	//Main method CLass
	private Scanner scan = null;
	//create and intialise scanner object
	private HorizontalPartition partition = null;
	//create and initialise partition object for partitioning of datasets
	private Apriori apriori = null;
	//create and initialise apriori object to implement apriori methods and data
	
	public static void main(String[] args) throws Exception {
		String dataset_path = System.getProperty(Constants.system_dir);
		//dataset_path contains user directory path
		dataset_path = dataset_path+File.separator+"dataset";
		//dataset_path contains user directory path for datasets
		
		new Main(dataset_path);
		//call Main Constructor with dataset_path as parameter
	}
	
public Main(String path) throws Exception {
	//path=dataset_path is argumment
	scanData(path);
	//scandata method fetches datasets and Discretizes the data
	partition = new HorizontalPartition(path);
	//partition object is updated with partitioned data as partitionD and partitionND
	//using HorizontalPartition() method
	ArrayList<String> partitionD = partition.getPartitionD();
	ArrayList<String> partitionND = partition.getPartitionND();
	//Declare two ArrayLists for storing defective and Non defective partirioned data
	//ArrayList<Datatype> List_Name=Values
	//ArrayList is Dynamic array stored as List
	apriori = new Apriori(partitionD, partitionND, 15);//threshold for the apriori
	//Create apriori object for calling apriori methods
	//Once Data is Discretised,Partitioned,Call Apriori to get Frequent itemsets
	ArrayList<String> frequentItemD = apriori.getFrequentItemsetD();
	ArrayList<String> frequentItemND = apriori.getFrequentItemsetND();
	//Declare two ArrayLists for storing def and Ndef Frequent Itemsets
	System.out.println("\n\n*********************************************************************************\n\n");
	System.out.println("-------------------------APRIORI(Frequent Itemset Defective)------------------------------------");
	frequentItemD.forEach(System.out::println);
/*	System.out.println(frequentItemD);
	System.out.println(frequentItemND);*/
	System.out.println("\n\n*********************************************************************************\n\n");
	System.out.println("-------------------------APRIORI(Frequent Itemset Ndefective)------------------------------------");
	frequentItemND.forEach(System.out::println);
	//Display Frequent Itemsets-DEf and NOn-DEf
	System.out.println("\n\n*********************************************************************************\n\n");
	
	System.out.println("---------------------------SUPPORT COUNT Defective------------------------------------");
	//Display Support COunt of All frequent Itemsets 
	ArrayList<Double> Dsc = apriori.getPartitionDsc();
	ArrayList<Double> NDsc = apriori.getPartitionNDsc();
	//Dsc and NDsc contain support count for all def and NonDef frequent itemsets
	Dsc.forEach(System.out::println);
/*	System.out.println(Dsc);
	System.out.println(NDsc); */
	System.out.println("---------------------------SUPPORT COUNT NonDefective------------------------------------");
	Dsc.forEach(System.out::println);
	
	ArrayList<Double> updatedDsc = new ArrayList<Double>();
	ArrayList<Double> updatedNDsc = new ArrayList<Double>();
	ArrayList<String> updatedfrequentItemD = new ArrayList<String>();
	ArrayList<String> updatedfrequentItemND = new ArrayList<String>();
	//Above Variables are used to store Itemsets and Support count Values above Threshold 
	double scth = 1000.0000;
	//support count threshold value
	for(int i=0;i<Dsc.size();i++){//iterate over set of Defective Frequent itemsets,size given by Dsc.size()
		if(Dsc.get(i)>scth){//if support count greater then threshold add to new List and UPdate frequent itemset
			updatedDsc.add(Dsc.get(i));
			updatedfrequentItemD.add(frequentItemD.get(i));
		}
	}
	for(int i=0;i<NDsc.size();i++){//iterate over set of Non Defective frequent itemsets,size given by Dsc.size()
		if(NDsc.get(i)>scth){//if Support count greater then threshold add to new List and UPdate frequent itemset
			updatedNDsc.add(NDsc.get(i));
			updatedfrequentItemND.add(frequentItemND.get(i));
		}
	}
	
	//Display selected(Above threshold) Frequent Itemsets-DEf and NOn-DEf
	System.out.println("-------------------------------------After Updating---------------------------");
	System.out.println("-------------------------------------Frequent Itemset-Updated: Defective---------------------------");
	//System.out.println(updatedfrequentItemD);
	updatedfrequentItemD.forEach(System.out::println);
	System.out.println("-------------------------------------Frequent Itemset-Updated: Non-Defective---------------------------");
	//System.out.println(updatedfrequentItemND);
	updatedfrequentItemND.forEach(System.out::println);
	
	//Dsc and NDsc contain support count for Selected(Above threshold) def and NonDef frequent itemsets
	System.out.println("-------------------------------------SUPPORT COUNT-Updated: Defective---------------------------");
	System.out.println(updatedDsc);
	updatedDsc.forEach(System.out::println);
	
	System.out.println("-------------------------------------SUPPORT COUNT-Updated: Defective---------------------------");
	System.out.println(updatedNDsc);
	updatedNDsc.forEach(System.out::println);
	
	//Implement Naive Bayes algorithm for Updated Frequent Itemset(FOcused itemset)
	System.out.println("--------------------------------------NAIVE BAYES--------------------------------");
	com.algorithm.naivebayes.NBmain NBmain = new com.algorithm.naivebayes.NBmain(updatedfrequentItemD, updatedfrequentItemND);
	System.out.println(NBmain.getFeatures());
}
	
	
	//ScanData method for Dataset  address and Discretization
	private void scanData(String path){
		try{
		File file = new File(path);
		//Create file object and initialise for given file path address
		File[] f = file.listFiles();
		//creat file array f conatining list of all files in 'file' object (files present in path) 
		//if(f.length == 5){
			
		for(File files:f){
			//iterate for each files in list
			String name = files.getName();
			//get the file names iteratively
			String format = name.substring(name.indexOf('.'), name.length());
			name = name.substring(0, name.indexOf("."));
			String Fpath = files.getAbsolutePath();
			String Fout = files.getParent()+File.separator+name+"_out"+format;
			//above steps get the file name and add "out" keyword to the new-file-name for (to store)Discretised dataset file
			File fout = new File(Fout);
			if(!fout.isFile()){
				fout.createNewFile();
				DiscretizeTest.run(Fpath, Fout);
				//create new file with the name created above(as Fout) and pass to DiscretizeTest.run(Fpath, Fout) to store in new file
			}
//			break;
		}
		//}
		System.out.println("**********************Discretization done********************");
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(scan!=null)
				scan.close();
		}
	}
	
}
