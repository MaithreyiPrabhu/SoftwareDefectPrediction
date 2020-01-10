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
public class Apriori {
private ArrayList<String> apriori_hc = new ArrayList<String>();
private ArrayList<String> apriori_hcD = new ArrayList<String>();
private ArrayList<String> HTWUI =new ArrayList<String>();
private ArrayList<String> SC = new ArrayList<String>();
private ArrayList<String> itemset = new ArrayList<String>();
private ArrayList<String> unpromising_items = new ArrayList<String>();
private ArrayList<Itemset> Items = new ArrayList<Itemset>();

    public Apriori(ArrayList<String> HT,ArrayList<String> sc,ArrayList<String> itm_set,ArrayList<String> unpromise_items,ArrayList<Itemset> itms) {
        this.Items = itms;
        this.HTWUI = HT;
        this.SC = sc;
        this.itemset = itm_set;
        this.unpromising_items = unpromise_items;
        this.AprioriHC();
//        this.DGU();
    }
    
    private void AprioriHC()
    {
        int length = HTWUI.size();
        Iterator itr = itemset.iterator();
        while(itr.hasNext())
        {
            String set = itr.next().toString();
            if(set.length()>length)
            {
                apriori_hc.add(set);
            }
        }
    }
    
    private void DGU()
    {
        Iterator itr_unpromise = unpromising_items.iterator();
        ArrayList<String> un_TR = new ArrayList<String>();
        int un_profit = 0;
        while(itr_unpromise.hasNext())
        {
          String unpromise = itr_unpromise.next().toString();
            System.out.println(">>>"+fetch_unpromise_profit(unpromise)+"<<<<"+unpromise);
         un_TR = fetch_unpromise_transaction_details(unpromise);
         un_profit = fetch_unpromise_profit(unpromise);
        Iterator itr_items = Items.iterator();
        while(itr_items.hasNext())
        {
            Itemset items = (Itemset) itr_items.next();
            if(items.get_item_name().equalsIgnoreCase(unpromise))
            {
                Items.remove(unpromise);
               
            }
            else
            {
                for(int i=0;i<items.get_number_of_transaction_utility();i++)
                {
                    int tu = items.get_transaction_utility().get(i);
                    tu = tu-un_profit;
                    items.update_tu(tu, i);
                }
            }
        }
          un_TR.clear();
        un_profit = 0;
        }
      
    }
    
    private ArrayList<String> fetch_unpromise_transaction_details(String unpromise_items)
    {
        ArrayList<String> arr = null;
        String[] u = unpromise_items.split(" ");
        Iterator itr = Items.iterator();
        while(itr.hasNext())
        {
            Itemset items = (Itemset) itr.next();
            if(items.get_item_name().equalsIgnoreCase(u[0]))
            {
                 arr = new ArrayList<String>(items.get_transactionsId());
            }
        }
       return arr;
    }
    
    private void remove_tid(ArrayList<String> arr,int un_profit)
    {
        Iterator itr = Items.iterator();
        while(itr.hasNext())
        {
            
        }
    }
    
    private int fetch_unpromise_profit(String unpromise_items)
    {
        int profit = 0;
        String[] u = unpromise_items.split(" ");
        Iterator itr = Items.iterator();
        while(itr.hasNext())
        {
            Itemset items = (Itemset) itr.next();
            if(items.get_item_name().equalsIgnoreCase(u[0]))
            {
                 profit = items.get_unit_profit();
            }
        }
       return profit;
    }
    
    private void IIDS()
    {
        
    }
    
    
    public ArrayList<String> get_AprioriHC()
    {
        return apriori_hc;
    }
    
    public ArrayList<String> get_AprioriHCD()
    {
        return apriori_hcD;
    }
    
}
