/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HUI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Anspro
 */
public class Absolute_Utility {
private ArrayList<Itemset> items = new ArrayList<Itemset>();
private ArrayList<String> itemset = new  ArrayList<String>();
    private ArrayList<String> absolute_utility_item = new ArrayList<String>();
    private ArrayList<String> absolute_utility_item_transaction = new ArrayList<String>();
    private ArrayList<String> absolute_utility_itemset = new ArrayList<String>();
    private ArrayList<ArrayList<String>> sc = new ArrayList<ArrayList<String>>();
    
    public Absolute_Utility(ArrayList<Itemset> arr,ArrayList<String> arr1) {
        this.items = arr;
        this.itemset = arr1;
        this.absolute_utility_item();
        this.absolute_utility_itemset();
    }
    
    private void absolute_utility_item()
    {
        int abs = 0;
        for(int i=0;i<items.size();i++)
        {
            abs = 0;
        String name = items.get(i).get_item_name();
        int unit_profit = items.get(i).get_unit_profit();
        ArrayList<Integer> transaction = items.get(i).get_transactions();
        ArrayList<String> tid = items.get(i).get_transactionsId();
        
        for(int j=0;j<transaction.size();j++)
        {
            absolute_utility_item.add(name+"-"+tid.get(j)+"-"+transaction.get(j)*unit_profit);
            abs+=transaction.get(j)*unit_profit;
        }
        absolute_utility_item_transaction.add(name+"-"+abs);
        }
    }
    
    private void absolute_utility_itemset()
    {
       for(int i=0;i<itemset.size();i++)
       {
           int abs = get_single_absolute_item_utility(itemset.get(i));
           if(abs != 0)
           absolute_utility_itemset.add(itemset.get(i)+"-"+abs);
       }
    }
    
    private int get_single_absolute_item_utility(String name)
    {
        int abs_utility = 0,count=0;
        for(int i=0;i<items.size();i++)
        {
            for(int j=0;j<name.length();j++)
            {
                if(items.get(i).get_item_name().equalsIgnoreCase(String.valueOf(name.charAt(j))))
                {
                    sc.add(items.get(i).get_transactionsId());
                }
            }
        }
        
        if(getCommonElements(sc).size()>0)
        {
            ArrayList<Itemset> arr = fetch_itemset(name);
            for(int i=0;i<getCommonElements(sc).size();i++)
            {
                String tid = getCommonElements(sc).get(i);
//                System.out.println("TID= "+tid);
                for(Itemset it:arr)
                {
                    int unit_profit = it.get_unit_profit();
                    for(int j=0;j<it.get_number_of_transactionID();j++)
                    {
                        if(tid.equalsIgnoreCase(it.get_transactionsId().get(j)))
                        {
//                            System.out.println("j= "+j);
//                            System.out.println("Transaction Id= "+it.get_transactionsId().get(j) +"\nTransaction values="+it.get_transactions().get(j)+"\n"+"Unit Porfit="+unit_profit);
                            abs_utility+=it.get_transactions().get(j) *unit_profit;
                        }
                    }
                }
            }
        }
        
        sc.clear();
         count=0;
//         System.out.println(">>>"+abs_utility+"<<<<<<"+name);
        return abs_utility;
    }
    
    private ArrayList<Itemset> fetch_itemset(String name)
    {
        ArrayList<Itemset> arr = new ArrayList<Itemset>();
        for(int i=0;i<name.length();i++)
        {
            for(int j=0;j<items.size();j++)
            {
                if(items.get(j).get_item_name().equalsIgnoreCase(String.valueOf(name.charAt(i))))
                {
                   arr.add(items.get(j));
                }
            }
        }
        return arr;
    }
private static <T> ArrayList<T> getCommonElements(Collection<? extends Collection<T>> collections) {

    ArrayList<T> common = new ArrayList<T>();
    if (!collections.isEmpty()) {
       Iterator<? extends Collection<T>> iterator = collections.iterator();
       common.addAll(iterator.next());
       while (iterator.hasNext()) {
          common.retainAll(iterator.next());
       }
    }
    return common;
}
    
    public ArrayList<String> get_absolute_utility_item()
    {
        return absolute_utility_item;
    }
    
    public ArrayList<String> get_absolute_utility_itemset()
    {
        return absolute_utility_itemset;
    }
    
    public ArrayList<String> get_absolute_utility_items_transaction()
    {
        return absolute_utility_item_transaction;
    }
}
