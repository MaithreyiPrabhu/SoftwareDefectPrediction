package com.Impl;
//this program is not used..We'll check this tomo
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import HUI.Itemset;

public class Itemset_support {
    private ArrayList<Itemset> Items = new ArrayList<Itemset>();
    private ArrayList<String> Itemset = new ArrayList<String>();
    private ArrayList<ArrayList<String>> sc = new ArrayList<ArrayList<String>>();
    private ArrayList<String> support_count_itemset = new ArrayList<String>();
    private ArrayList<String> support_count_item = new ArrayList<String>();
    
    public Itemset_support(ArrayList<Itemset> it,ArrayList<String> arr) {
        this.Items = it;
        this.Itemset = arr;
        this.support_count_itemset();
        this.support_count_item();
    }
    
    
  
    private void support_count_itemset()
    {
        int count = 0;
        
        for(int i=0;i<Itemset.size();i++)
        {
            for(int j=0;j<Items.size();j++)
            {
                for(int k=0;k<Itemset.get(i).length();k++)
                {
                if(Items.get(j).get_item_name().equalsIgnoreCase(String.valueOf(Itemset.get(i).charAt(k))))
                {
                    sc.add( Items.get(j).get_transactionsId());
                }
                }
            }
         count = getCommonElements(sc).size();
         if(count!=0)
         {
        support_count_itemset.add(Itemset.get(i)+"-"+count+"-"+getCommonElements(sc).toString());
         }
        sc.clear();
         count=0;
        }
    }
    
    private void support_count_item()
    { 
        for(int i=0;i<Items.size();i++)
        {
            support_count_item.add(Items.get(i).get_item_name()+"-"+Items.get(i).get_number_of_transactionID()+"-"+Items.get(i).get_transactionsId());
        }
    }
    
    private static <T> Set<T> getCommonElements(Collection<? extends Collection<T>> collections) {

    Set<T> common = new LinkedHashSet<T>();
    if (!collections.isEmpty()) {
       Iterator<? extends Collection<T>> iterator = collections.iterator();
       common.addAll(iterator.next());
       while (iterator.hasNext()) {
          common.retainAll(iterator.next());
       }
    }
    return common;
}
    
    public ArrayList get_support_count_itemset()
    {
        return support_count_itemset;
    }
    
    public ArrayList get_support_count_item()
    {
        return support_count_item;
    }
}
