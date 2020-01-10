/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Anspro
 */
public class Closed_Itemset {
    private ArrayList<String> transaction = new ArrayList<String>();
    private ArrayList<String> closed_itemset = new ArrayList<String>();
    private ArrayList<String> CHUI = new ArrayList<String>();
    private ArrayList<String> support_count_itemset = new ArrayList<String>();
    private ArrayList<String> support_count_item = new ArrayList<String>();
    private ArrayList<String> transaction_set = new ArrayList<String>();
    private ArrayList<String> temp = new ArrayList<String>();
    private int absolute_min_utiity = 0;
    private ArrayList<String> absolute_utility = new ArrayList<String>();
    private StringBuilder output =new StringBuilder();
    
    public Closed_Itemset(ArrayList<String> tr,ArrayList<String> sc_itemset,ArrayList<String> sc_item,ArrayList<String> abs_util,int abs) {
        this.transaction = tr;
        this.support_count_itemset = sc_itemset;
        this.support_count_item = sc_item;
        this.absolute_utility = abs_util;
        this.absolute_min_utiity = abs;
        create_closed_itemset();
        insert_CHUI();
    }
    
    private void create_closed_itemset()
    {
        System.out.println("transaction Name= "+transaction);
//        transaction_set_creation(0);
        getCombination2(transaction);
        for(int i=0;i<transaction_set.size();i++)
        {
            String[] d = transaction_set.get(i).split(",");
            Collection g = Arrays.asList(d);
            for(int j=0;j<support_count_itemset.size();j++)
            {
                String[] t = support_count_itemset.get(j).split("-");
                String ele = t[0];
                String sc = t[1];
                t[2] = t[2].replace("[", "");
                t[2] = t[2].replace("]", "");
                t[2] = t[2].replace(" ", "");
                Collection h = Arrays.asList(t[2].split(","));
                
               if(matched_data(g, h))
               {
                   if(Integer.parseInt(sc) == transaction_set.get(i).split(",").length)
                   {
                   temp.add(support_count_itemset.get(j));
                   }
               }
            }
            for(int k=0;k<support_count_item.size();k++)
            {
                String[] t = support_count_item.get(k).split("-");
                String ele = t[0];
                String sc = t[1];
                t[2] = t[2].replace("[", "");
                t[2] = t[2].replace("]", "");
                t[2] = t[2].replace(" ", "");
                Collection h = Arrays.asList(t[2].split(","));
               if(matched_data(g, h))
               {
                   if(Integer.parseInt(sc) == transaction_set.get(i).split(",").length)
                   {
                   temp.add(support_count_item.get(k));
                   }
               }
            }
//            System.out.println("-------");
            if(!temp.isEmpty())
            {
            insert_closed_itemset(temp);
            temp.clear();
            }
        }
        
    }
    
    private void  getCombination2(ArrayList<String> data)
	{
		int len=data.size();
                String d = "";
		for(int i=0;i<len;i++)
		{
			for(int k=i;k<len;k++)
			{
                                                                                    
				 d+=data.get(k)+",";                                
//				System.out.print(c);
			}
                        d = d.substring(0, d.lastIndexOf(","));
                        if(d.length()>1)
                        {
			transaction_set.add(d);
                        }
                        d = "";
		}
	}
    
    private void transaction_set_creation(int start)
    {
         for( int i = start; i < transaction.size(); ++i ){
            output.append( transaction.get(i)+",");
                String t = output.toString().substring(0, output.toString().lastIndexOf(","));
                transaction_set.add(t);
            if ( i < transaction.size() )
            {
            transaction_set_creation( i + 1);
            }
            output.setLength( output.length() - 3 );
        }
    }
    
    private boolean matched_data(Collection c1,Collection c2)
    {
        boolean b = false;
        int counter = 0;
        ArrayList arr1 = new ArrayList(c1);
        ArrayList arr2 = new ArrayList(c2);
        for(int i=0;i<arr1.size();i++)
        {
            for(int j=0;j<arr2.size();j++)
            {
                if(arr1.get(i).toString().contains(arr2.get(j).toString()))
                {
                    counter++;
                }
            }
        }
        if(counter == arr1.size())
        {
            b = true;
        }
        return b;
    }
    
    private void insert_closed_itemset(ArrayList<String> arr)
    {
        String t = arr.get(0).split("-")[0];
        for(int i=0;i<arr.size();i++)
        {
            
//            System.out.println("::"+t);
            for(int j=i;j<arr.size();j++)
            {
                if(t.length()<arr.get(j).split("-")[0].length())
                {
                    t = arr.get(j).split("-")[0];
                }
            }
        }
        closed_itemset.add(t);
    }
    
    private void insert_CHUI()
    {
        for(int i=0;i<closed_itemset.size();i++)
        {
            String t = closed_itemset.get(i);
            for(int j=0;j<absolute_utility.size();j++)
            {
                if(t.equalsIgnoreCase(absolute_utility.get(j).split("-")[0]))
                {
                    int val = Integer.parseInt(absolute_utility.get(j).split("-")[1]);
                    if(val>=absolute_min_utiity)
                    {
                        CHUI.add(t);
                    }
                }
            }
        }
    }
    
    
    
    public ArrayList<String> get_transaction_set()
    {
        return transaction_set;
    }
    
    public ArrayList<String> get_closed_itemset()
    {
        return closed_itemset;
    }
    
    public ArrayList<String> get_CHUI()
    {
        return CHUI;
    }
    
}
