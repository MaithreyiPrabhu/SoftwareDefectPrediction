package com.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Apriori {
private ArrayList<String> PartionD = null;
private ArrayList<String>PartionND = null;
private ArrayList<String> frequentItemsetD = new ArrayList<String>();
private int Th;
private ArrayList<Double> PartitionDsc = new ArrayList<Double>();
private ArrayList<Double> PartitionNDsc = new ArrayList<Double>();


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

private ArrayList<String> frequentItemsetND = new ArrayList<String>();

public Apriori(ArrayList<String> ParD,ArrayList<String> ParND,int thresold) {
	if(PartionD == null && PartionND == null)
	{
		this.PartionD = ParD;
		this.PartionND = ParND;
	}
	this.Th = thresold;
	init();
}

	private void init(){
		HashSet<String> hsetD = getSingleData(PartionD);
		HashSet<String> hsetND = getSingleData(PartionND);
		frequentItemsetD = calculateFrequency(hsetD, PartionD);
		frequentItemsetND = calculateFrequency(hsetND, PartionND);
		PartitionDsc = getSupportCount(frequentItemsetD);
		PartitionNDsc = getSupportCount(frequentItemsetND);
	}
	
	private ArrayList<String> calculateFrequency(HashSet<String> hset,ArrayList<String> Partion){
		ArrayList<String> out = new ArrayList<String>();
		int count = 0;
		Iterator<String> itr = hset.iterator();
		while(itr.hasNext()){
			String D = itr.next();
			for(int i=0;i<Partion.size();i++){
				if(D.equalsIgnoreCase(Partion.get(i)) && !D.contains("All")){
					count++;
				}
			}
			if(count>Th){
			out.add(D+"%"+count);
			}
			count = 0;
		}
		return out;
	}
	
	private ArrayList<Double> getSupportCount(ArrayList<String> Data){
		double diff  = 0;
		ArrayList<Double> supportCount = new ArrayList<Double>();
		Iterator<String> itr = Data.iterator();
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
				supportCount.add( (count/diff)*100);
				
			}
		}
		return supportCount;
	}
	
	private HashSet<String> getSingleData(ArrayList<String> data){
		return new HashSet<String>(data);
	}
	
}
