package com.Impl;

import java.util.ArrayList;
import java.util.Arrays;

public class EquiFrequencyBinning {
private double[] data = null;
private int NumberOfBins;	
public EquiFrequencyBinning(double[] D) {
	this.data = D;
}
	private void init(){
		if(data != null){
			Arrays.sort(data);
			
		}
	}
	
}
//Not used