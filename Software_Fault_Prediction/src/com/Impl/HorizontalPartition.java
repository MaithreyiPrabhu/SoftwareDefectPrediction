package com.Impl;
//declare the package where file belongs

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
//importing java libraries for dynamic arrays,scanner(to read and tokenise input data)

public class HorizontalPartition {
private String file_Path = null;
private Scanner scan = null;
private ArrayList<String> PartitionD = new ArrayList<String>();
private ArrayList<String> PartitionND = new ArrayList<String>();
//Initiaiise all necesary data elements
//PartitionD and PartitionND are list to store def and Ndef instances

	public HorizontalPartition(String path) {
		//constructor
		this.file_Path = path;
		//file_Path intialised to discretised dataset path
		init();
		//init() preforms partitioning of data
		showdata();
		//showdata prints pratitions od def and Ndef data
		
	}
	
	private void init(){
		try{
		File F = new File(file_Path);
		File[] files = F.listFiles();
		//read all files from dataset folder based on address in file_path
		for(File file:files){
			String name = file.getName();
			if(name.contains("out")){
				System.out.println(name);
				//fetch each discretised dataset file (that contain "out" keyword in the name
				scan = new Scanner(file);
				//scan reads data from file
				while(scan.hasNext()){
					String D = scan.nextLine();
					//until file is not empty,keep reading from file to string D-----------------------------
					if(D.equalsIgnoreCase("@data")){
						while(scan.hasNextLine()){
							String data = scan.nextLine();
							String sta = data.substring(data.lastIndexOf(",")+1, data.length());
							data = data.substring(0, data.lastIndexOf(","));
							String[] Dd = data.split(",");
							if(sta.equalsIgnoreCase("true")||sta.equalsIgnoreCase("y")){
								for(String s:Dd){
									s = s.replaceAll("'", "");
									s = s.replaceAll("\\\\", "");
								PartitionD.add(s.trim());
								}
							}
							//aboveif condition finds all defective instances groups as PartitionD
							else if(sta.equalsIgnoreCase("n")||sta.equalsIgnoreCase("false")){
								for(String s:Dd){
									s = s.replaceAll("'", "");
									s = s.replaceAll("\\\\", "");
									PartitionND.add(s.trim());
								}
							}
							//aboveif condition finds all non defective instances groups as PartitionND
							
						}
					}
				}
			}
		}
		
		//ignore since itis for exception handling
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(scan!=null)
				scan.close();
		}
	}
	
	
	//below functions return partition lists of Def and Ndef instances
	public ArrayList<String> getPartitionD() {
		return PartitionD;
	}
	public ArrayList<String> getPartitionND() {
		return PartitionND;
	}

	//display data----
	private void showdata(){
		System.out.println("---------------------------------D-----------------------------");
		System.out.println(PartitionD);
		System.out.println("---------------------------------ND---------------------------");
		System.out.println(PartitionND);
	}
}
