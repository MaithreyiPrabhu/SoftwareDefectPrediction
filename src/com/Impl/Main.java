package com.Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.gui.beans.DataSource;
import weka.gui.beans.Loader;

public class Main {
	private Scanner scan = null;
	private HorizontalPartition partition = null;
	private Apriori apriori = null;
	
	public static void main(String[] args) throws Exception {
		String dataset_path = System.getProperty(Constants.system_dir);
		dataset_path = dataset_path+File.separator+"dataset";
		new Main(dataset_path);
	}
	
public Main(String path) throws Exception {
	scanData(path);
	partition = new HorizontalPartition(path);
	ArrayList<String> partitionD = partition.getPartitionD();
	ArrayList<String> partitionND = partition.getPartitionND();
	apriori = new Apriori(partitionD, partitionND, 15);//threshold for the apriori
	ArrayList<String> frequentItemD = apriori.getFrequentItemsetD();
	ArrayList<String> frequentItemND = apriori.getFrequentItemsetND();
	System.out.println("-------------------------APRIORI(Frequent Itemset)------------------------------------");
	System.out.println(frequentItemD);
	System.out.println(frequentItemND);
	System.out.println("--------------------SUPPORT COUNT-------------------------------");
	ArrayList<Double> Dsc = apriori.getPartitionDsc();
	ArrayList<Double> NDsc = apriori.getPartitionNDsc();
	System.out.println(Dsc);
	System.out.println(NDsc);
	
	ArrayList<Double> updatedDsc = new ArrayList<Double>();
	ArrayList<Double> updatedNDsc = new ArrayList<Double>();
	ArrayList<String> updatedfrequentItemD = new ArrayList<String>();
	ArrayList<String> updatedfrequentItemND = new ArrayList<String>();
	double scth = 1000.0000;//support count threshold
	for(int i=0;i<Dsc.size();i++){
		if(Dsc.get(i)>scth){
			updatedDsc.add(Dsc.get(i));
			updatedfrequentItemD.add(frequentItemD.get(i));
		}
	}
	for(int i=0;i<NDsc.size();i++){
		if(NDsc.get(i)>scth){
			updatedNDsc.add(NDsc.get(i));
			updatedfrequentItemND.add(frequentItemND.get(i));
		}
	}
	
	System.out.println("-------------------------------------After Updating---------------------------");
	System.out.println("-------------------------------------Frequent Itemset---------------------------");
	System.out.println(updatedfrequentItemD);
	System.out.println(updatedfrequentItemND);
	System.out.println("-------------------------------------SUPPORT COUNT---------------------------");
	System.out.println(updatedDsc);
	System.out.println(updatedNDsc);
	System.out.println("--------------------------------------NAIVE BAYES--------------------------------");
	com.algorithm.naivebayes.NBmain NBmain = new com.algorithm.naivebayes.NBmain(updatedfrequentItemD, updatedfrequentItemND);
	System.out.println(NBmain.getFeatures());
}
	
	private void scanData(String path){
		try{
		File file = new File(path);
		File[] f = file.listFiles();
		if(f.length == 5){
		for(File files:f){
			String name = files.getName();
			String format = name.substring(name.indexOf('.'), name.length());
			name = name.substring(0, name.indexOf("."));
			String Fpath = files.getAbsolutePath();
			String Fout = files.getParent()+File.separator+name+"_out"+format;
			File fout = new File(Fout);
			if(!fout.isFile()){
				fout.createNewFile();
				DiscretizeTest.run(Fpath, Fout);
			}
//			break;
		}
		}
		System.out.println("Discretization done");
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(scan!=null)
				scan.close();
		}
	}
	
}
