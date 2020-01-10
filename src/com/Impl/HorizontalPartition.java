package com.Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class HorizontalPartition {
private String file_Path = null;
private Scanner scan = null;
private ArrayList<String> PartitionD = new ArrayList<String>();
private ArrayList<String> PartitionND = new ArrayList<String>();

	public HorizontalPartition(String path) {
		this.file_Path = path;
		init();
		showdata();
	}
	
	private void init(){
		try{
		File F = new File(file_Path);
		File[] files = F.listFiles();
		for(File file:files){
			String name = file.getName();
			if(name.contains("out")){
				System.out.println(name);
				scan = new Scanner(file);
				while(scan.hasNext()){
					String D = scan.nextLine();
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
							else if(sta.equalsIgnoreCase("n")||sta.equalsIgnoreCase("false")){
								for(String s:Dd){
									s = s.replaceAll("'", "");
									s = s.replaceAll("\\\\", "");
									PartitionND.add(s.trim());
								}
							}
						}
					}
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(scan!=null)
				scan.close();
		}
	}
	
	public ArrayList<String> getPartitionD() {
		return PartitionD;
	}
	public ArrayList<String> getPartitionND() {
		return PartitionND;
	}

	private void showdata(){
		System.out.println("---------------------------------D-----------------------------");
		System.out.println(PartitionD);
		System.out.println("---------------------------------ND---------------------------");
		System.out.println(PartitionND);
	}
}
