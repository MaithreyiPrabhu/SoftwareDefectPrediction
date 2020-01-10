/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HUI;

import java.util.ArrayList;

/**
 *
 * @author Anspro
 */
public class Itemset {
    private String ITEM_NAME = "";
    private ArrayList<Integer> TRANSACTION = new ArrayList<Integer>();
    private ArrayList<Integer> TRANSACTION_UTILITY = new ArrayList<Integer>();
    private int UNIT_PROFIT = 0;
    private ArrayList<String> TID = new ArrayList<String>();
    
    public Itemset(String name,int profit) {
        this.ITEM_NAME = name;
        this.UNIT_PROFIT = profit;
    }
    
    public void add_transaction(int transaction)
    {
        TRANSACTION.add(transaction);
    }
    
    public void add_transactionID(String tid)
    {
        TID.add(tid);
    }
    
    public void add_transaction_utility(int transaction_utility)
    {
        TRANSACTION_UTILITY.add(transaction_utility);
    }
    
    public String get_item_name()
    {
        return ITEM_NAME;
    }
    
    public int get_unit_profit()
    {
        return UNIT_PROFIT;
    }
    
    public int get_number_of_transaction()
    {
        return TRANSACTION.size();
    }
    
    public int get_number_of_transactionID()
    {
        return TID.size();
    }
    
    public int get_number_of_transaction_utility()
    {
        return TRANSACTION_UTILITY.size();
    }
    
   public ArrayList<Integer> get_transactions()
   {
       return TRANSACTION;
   }
   
   public ArrayList<String> get_transactionsId()
   {
       return TID;
   }
   
   public ArrayList<Integer> get_transaction_utility()
   {
       return TRANSACTION_UTILITY;
   }
   
   public void update_tu(int tu,int position)
   {
       TRANSACTION_UTILITY.set(position, tu);
   }
}
