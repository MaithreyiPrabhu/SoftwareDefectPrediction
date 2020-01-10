package com.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
//java classes for dynamic array(list), hashset for unique elements(remove redundant data) 
//and Iterator for reading and removing objects from Arraylist or hashset

public class Apriori {
private ArrayList<String> PartionD = null;
private ArrayList<String>PartionND = null;
private ArrayList<String> frequentItemsetD = new ArrayList<String>();
private ArrayList<String> frequentItemsetND = new ArrayList<String>();

private int Th;//to store threshold
private ArrayList<Double> PartitionDsc = new ArrayList<Double>();
private ArrayList<Double> PartitionNDsc = new ArrayList<Double>();
//variables to store partioned data,supoort counts and frequent itemsets of defective and non defective instances


public ArrayList<Double> getPartitionDsc() {
	return PartitionDsc;
}

public ArrayList<Double> getPartitionNDsc() {
	return PartitionNDsc;
}

public ArrayList<String> getFrequentItemsetD() {
	return frequentItemsetD;
}

public ArrayList<String> getFrequentItemsetND() {
	return frequentItemsetND;
}
//All above methods are used to retrieve Support counts and Frequent itemsets

public Apriori(ArrayList<String> ParD,ArrayList<String> ParND,int thresold) {
	//Apriori constructor called by Main method
	//ex: 	apriori = new Apriori(partitionD, partitionND, 15);//15-threshold for the apriori
	if(PartionD == null && PartionND == null)
	{
		this.PartionD = ParD;
		this.PartionND = ParND;
	}
	this.Th = thresold;
	//above code verifies if partioned data is not NULL
	init();
}

	private void init(){
		//invoked by constructor
		HashSet<String> hsetD = getSingleData(PartionD);
		HashSet<String> hsetND = getSingleData(PartionND);
		//create two hashsets to contain only unique partitioned data
		frequentItemsetD = calculateFrequency(hsetD, PartionD);
		frequentItemsetND = calculateFrequency(hsetND, PartionND);
		//find the count of elements in frequent itemset and suffixes the value with delimiter
		PartitionDsc = getSupportCount(frequentItemsetD);
		PartitionNDsc = getSupportCount(frequentItemsetND);
		//variables store support count--getSupportCount gives SC values
	}
	
	private ArrayList<String> calculateFrequency(HashSet<String> hset,ArrayList<String> Partion){
		ArrayList<String> out = new ArrayList<String>();
		int count = 0;
		Iterator<String> itr = hset.iterator();
		//iterate through whole partition data and calculate count 
		//then write count with the instance,seperated by delimiter
		while(itr.hasNext()){
			String D = itr.next();
			for(int i=0;i<Partion.size();i++){
				if(D.equalsIgnoreCase(Partion.get(i)) && !D.contains("All")){
					count++;
				}
			}
			if(count>Th){
			out.add(D+"%"+count);
			//this is --instance frm hashset--%(delimit)--count(i.e., 'out')
			}
			count = 0;
		}
		return out;
		
	}
	
	private ArrayList<Double> getSupportCount(ArrayList<String> Data){
		//calculate SC of "data"argument 
		//Ex:PartitionDsc = getSupportCount(frequentItemsetD);
		double diff  = 0;
		ArrayList<Double> supportCount = new ArrayList<Double>();
		//array to store support count--which is numerical--so double
		Iterator<String> itr = Data.iterator();
		//iterator initialised to iterate through frequentItemsets(i.e., method argument)
		while(itr.hasNext()){
			String D = itr.next();
			if(!D.contains("All")){
				String[] d = D.split("%");
				int count = Integer.parseInt(d[1]);
				String f = d[0].replace("(","");
				f = f.replace(")", "");
				if(f.contains("]")){
					f = f.replace("]","");
				}
				if(f.contains("[")){
					f = f.replace("[", "");
				}
				if(f.contains("inf")){
					f = f.replace("inf", "");
					f = f.replaceAll("-","");
					diff = Double.parseDouble(f);
				}
				else{
					String [] dff = f.split("-");
					diff = Double.parseDouble(dff[1])-Double.parseDouble(dff[0]);
				}
				//All above code removes-{All,(,),[,],-) and relace with nothing
				//then find difference between Range.
				supportCount.add( (count/diff)*100);
				//find support count as in formula
				//i.e.,: support(i)=count(i)*100)/mod(p[i])
				
			}
		}
		return supportCount;
	}
	
	private HashSet<String> getSingleData(ArrayList<String> data){
		return new HashSet<String>(data);
	}
	
}
