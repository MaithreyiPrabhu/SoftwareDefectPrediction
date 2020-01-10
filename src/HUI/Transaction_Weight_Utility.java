/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HUI;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Anspro
 */
public class Transaction_Weight_Utility {
    private ArrayList<Itemset> items = new ArrayList<Itemset>();
    private ArrayList<String> itemset = new ArrayList<String>();
    private ArrayList<String> TWU_ITEM = new ArrayList<String>();
    private ArrayList<String> TWU_ITEMSET = new ArrayList<String>();
    public Transaction_Weight_Utility(ArrayList<Itemset> arr,ArrayList<String> itm_set) {
        this.items = arr;
        this.itemset = itm_set;
        TWU_item();
        TWU_itemset();
    }
    
    private void TWU_item()
    {
        int twu=0;
        for(int i=0;i<items.size();i++)
        {
           for(int j=0;j<items.get(i).get_number_of_transaction_utility();j++)
           {
               twu+=items.get(i).get_transaction_utility().get(j);
           }
           TWU_ITEM.add(items.get(i).get_item_name()+" "+twu);
           twu = 0;
        }
    }
    
    private void TWU_itemset()
    {
        Iterator itr = itemset.iterator();
        int twu = 0;
        while(itr.hasNext())
        {
            String set = itr.next().toString();
            twu = 0;
            String[] temp = set.split("");
            for(int i=0;i<temp.length;i++)
            {
                Iterator it = items.iterator();
                while(it.hasNext())
                {
                    Itemset t = (Itemset) it.next();
                    if(t.get_item_name().equalsIgnoreCase(temp[i]))
                    {
                        for(int j=0;j<t.get_number_of_transaction_utility();j++)
                        {
                            twu+=t.get_transaction_utility().get(j);
                        }
                    }
                }
            }
            TWU_ITEMSET.add(set+" "+twu);
        }
    }
    
    public ArrayList<String> get_TWU()
    {
        return TWU_ITEM;
    }
    public ArrayList<String> get_TWU_ITEMSET()
    {
        return TWU_ITEMSET;
    }
}
