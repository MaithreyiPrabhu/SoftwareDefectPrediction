/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

import com.Impl.Itemset_support;

/**
 *
 * @author Anspro
 */
public class Main {
    ArrayList<Itemset> items = new ArrayList<Itemset>();
    ArrayList<String> itemset = new ArrayList<String>();
    ArrayList<String> temp = new ArrayList<String>();
    ArrayList<String> transaction_id = new ArrayList<String>();
    private StringBuilder output =new StringBuilder();
    
    
    public void upload_data(String database_path,String profit_path) throws FileNotFoundException, IOException
    {
        FileInputStream fin_database = new FileInputStream(new File(database_path));
        byte[] b = new byte[fin_database.available()];
        fin_database.read(b);
        fin_database.close();
        
        FileInputStream fin_profit = new FileInputStream(new File(profit_path));
        byte[] b1 = new byte[fin_profit .available()];
        fin_profit.read(b1);
        fin_profit.close();
        
        String profit = new String(b1);
        String database = new String(b);
        
//        System.out.println("database= "+database);
//        System.out.println("profit= "+profit);
        String[] each = database.split("\n");
        String itset = "";
        for(int i=0;i<each.length;i++)
        {
            itset = "";
            String[] partition = each[i].split(" ");
            String tid = partition[0];
            String transaction = partition[1];
            String transaction_utility = partition[2];
            System.out.println(tid+">>>");
            transaction_id.add(tid);
            String[] t = transaction.split(",");
            for(int j=0;j<t.length;j++)
            {
                String name = t[j].split("-")[0];
                String transaction_each = t[j].split("-")[1];
                itset += name+"";
//                System.out.println("name= "+name);
//                System.out.println(transaction_each);
                if(!items.isEmpty())
                {
//                    System.out.println(">>>"+items.size());
                    int counter = 0;
                    for(int k=0;k<items.size();k++)
                    {
//                        System.out.println("item name= "+items.get(k).get_item_name());
                        if(items.get(k).get_item_name().equalsIgnoreCase(name))
                        {
                            items.get(k).add_transaction(Integer.parseInt(transaction_each));
                            items.get(k).add_transactionID(tid);
                            items.get(k).add_transaction_utility(Integer.parseInt(transaction_utility.trim()));
                        }
                        else
                        {
                          counter++;
                        }
                    }
                    if(counter == items.size())
                    {
                         int unit_profit = fetch_profit(name, profit);
                           Itemset itm = new Itemset(name, unit_profit);
                           itm.add_transaction(Integer.parseInt(transaction_each));
                           itm.add_transactionID(tid);
                           itm.add_transaction_utility(Integer.parseInt(transaction_utility.trim()));
                           items.add(itm);
                    }
                }
                else
                {
                     int unit_profit = fetch_profit(name, profit);
                           Itemset itm = new Itemset(name, unit_profit);
                           itm.add_transaction(Integer.parseInt(transaction_each));
                           itm.add_transactionID(tid);
                           itm.add_transaction_utility(Integer.parseInt(transaction_utility.trim()));
                           items.add(itm);
                }
            }
            temp.add(itset);
        }
//        itemset_creation(0);
        for(int i = 0;i<temp.size();i++)
        {
            getCombination2(temp.get(i));
        }
    }
    
    
     private void  getCombination2(String data)
	{
		int len=data.length();
                String d = "";
		for(int i=0;i<len;i++)
		{
			for(int k=i;k<len;k++)
			{
                                                                                    
				 d+=Character.toString(data.charAt(k));                                
//				System.out.print(c);
			}
                        if(d.length()>1)
                        {
			itemset.add(d);
                        }
                        d = "";
		}
	}
     
    public int fetch_profit(String name,String profit_table)
    {
            int profit = 0;
        try{
       String[] p = profit_table.split("\n");
       for(int i = 0;i<p.length;i++)
       {
           String[] n = p[i].split("-");
           if(n[0].equalsIgnoreCase(name))
           {
               profit = Integer.parseInt(n[1].trim());
           }
       }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
       return profit;
    }
    
    public static LinkedHashSet<String> run(String database_path,String profit_path, int abs_min_utility) throws FileNotFoundException, IOException 
    {
//        String path = System.getProperty("user.dir");
//        
//        String database_path = path+"\\Dataset\\DATABASE.txt";
//        String profit_path = path+"\\Dataset\\PROFIT.txt";
        
        Main man = new Main();
        man.upload_data(database_path, profit_path);
        System.out.println("items size= "+man.items.size());
        System.out.println("itemset size= "+man.itemset.size());
        System.out.println("--------------------------------Items Information------------------------------------------");
        for(int i=0;i<man.items.size();i++)
        {
            System.out.println("Item Name= "+man.items.get(i).get_item_name());
            System.out.println("Unit Profit= "+man.items.get(i).get_unit_profit());
            System.out.println("Number of Transaction= "+man.items.get(i).get_number_of_transaction());
            System.out.println("Number of Transaction ID= "+man.items.get(i).get_number_of_transactionID());
            System.out.println("Number of Transaction Utility= "+man.items.get(i).get_number_of_transaction_utility());
            ArrayList<Integer> transaction = man.items.get(i).get_transactions();
            ArrayList<String> tid = man.items.get(i).get_transactionsId();
            ArrayList<Integer> transaction_utility = man.items.get(i).get_transaction_utility();
            System.out.println("TransactionID values");
            for(int j=0;j<tid.size();j++)
            {
                System.out.print(tid.get(j)+"\t");
            }
            System.out.println();
            System.out.println("Transaction Values");
            for(int k=0;k<transaction.size();k++)
            {
                System.out.print(transaction.get(k)+"\t");
            }
            System.out.println();
            System.out.println("Transaction Utility");
            for(int l=0;l<transaction.size();l++)
            {   
                System.out.print(transaction_utility.get(l)+"\t");
            }
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------");
          
        }
        System.out.println("-----------------------------------------Transaction Ids-------------------------------------");
        for(int i=0;i<man.transaction_id.size();i++)
        {
            System.out.println(man.transaction_id.get(i));
        }
        
        System.out.println("------------------------------------------Itemset Creation-----------------------------------");
        for(int i=0;i<man.itemset.size();i++)
        {
            System.out.println(man.itemset.get(i).toString());
        }
          
            Itemset_support sc = new Itemset_support(man.items,man.itemset);
           ArrayList<String> support_count_itemset = sc.get_support_count_itemset();
           ArrayList<String> support_count_item = sc.get_support_count_item();
           System.out.println("----------------------------------------Support Count Itemset----------------------------------------");
            for(int i=0;i<support_count_itemset.size();i++)
            {
                System.out.println(support_count_itemset.get(i));
            }
            
            System.out.println("-----------------------------------------Support Count Item------------------------------------------");
            for(int i=0;i<support_count_item.size();i++)
            {
                System.out.println(support_count_item.get(i));
            }
            
            Absolute_Utility abs = new Absolute_Utility(man.items,man.itemset);
            ArrayList<String> absolute_utility_item = abs.get_absolute_utility_item();
            ArrayList<String> absolute_utility_itemset = abs.get_absolute_utility_itemset();
            ArrayList<String> absolute_utility_items_transaction = abs.get_absolute_utility_items_transaction();
            System.out.println("---------------------------Absolute Utility Item------------------------------------------");
            for(int i=0;i<absolute_utility_item.size();i++)
            {
                System.out.println(absolute_utility_item.get(i));
            }
            
            System.out.println("----------------------------Absolute Utility Itemset---------------------------------------");
            for(int i=0;i<absolute_utility_itemset.size();i++)
            {
                System.out.println(absolute_utility_itemset.get(i));
            }
            
            System.out.println("------------------------------Absolute Utility Items based on transaction-------------------------");
            for(int i=0;i<absolute_utility_items_transaction.size();i++)
            {
                System.out.println(absolute_utility_items_transaction.get(i));
            }
            
            Transaction_Utility TU = new Transaction_Utility(absolute_utility_item, man.transaction_id);
            System.out.println("-----------------------------------------Transaction Utility-----------------------------------"); 
            for(int i=0;i<TU.get_transaction_utility().size();i++)
            {
                System.out.println(TU.get_transaction_utility().get(i));
            }
      
            System.out.println("-----------------------------------------Total Utility------------------------------------------");
            System.out.println(TU.get_total_utility());
            
             Transaction_Weight_Utility TWU = new Transaction_Weight_Utility(man.items,man.itemset);
            System.out.println("---------------------------------------TWU(Items)--------------------------------------------------------------");
            for(int i=0;i<TWU.get_TWU().size();i++)
            {
                System.out.println(TWU.get_TWU().get(i));
            }
            System.out.println("----------------------------------------TWU(Itemset)------------------------------------------------------------");
            for(int i=0;i<TWU.get_TWU_ITEMSET().size();i++)
            {
                System.out.println(TWU.get_TWU_ITEMSET().get(i));
            }
//            = 20;
            System.out.println("abs_min_utility = "+abs_min_utility);
            HTWUI htwui = new HTWUI(absolute_utility_itemset, abs_min_utility);
            System.out.println("-----------------------------------------HTWUI-------------------------------------------------------------------"+htwui.get_htwui().size());
            for(int i=0;i<htwui.get_htwui().size();i++)
            {
                System.out.println(htwui.get_htwui().get(i));
            }
            System.out.println("------------------------------------------Unpromising Items-------------------------------------------------------");
            for(int i=0;i<htwui.get_unpromising_items().size();i++)
            {
                System.out.println(htwui.get_unpromising_items().get(i));
            }
            
            ArrayList all_abs_utility = new ArrayList();
            all_abs_utility.addAll(absolute_utility_itemset);
            all_abs_utility.addAll(absolute_utility_items_transaction);
            Closed_Itemset closed = new Closed_Itemset(man.transaction_id, support_count_itemset,support_count_item,all_abs_utility,abs_min_utility);
            System.out.println("-----------------------------------Transaction Set-----------------------------------------------------");
            for(int i=0;i<closed.get_transaction_set().size();i++)
            {
                System.out.println(closed.get_transaction_set().get(i));
            }
            System.out.println("------------------------------------Closed Itemset------------------------------------------------------");
            for(int i=0;i<closed.get_closed_itemset().size();i++)
            {
                System.out.println(closed.get_closed_itemset().get(i));
            }
            System.out.println("--------------------------------------CHUI---------------------------------------------------------------");
            for(int i=0;i<closed.get_CHUI().size();i++)
            {
                System.out.println(closed.get_CHUI().get(i));
            }
            
            System.out.println("----------------------------------------Apriori------------------------------------------------------------");
            Apriori apriori = new Apriori(htwui.get_htwui(),support_count_itemset,man.itemset,htwui.get_unpromising_items(),man.items);
            for(int i=0;i<apriori.get_AprioriHC().size();i++)
            {
                System.out.println(apriori.get_AprioriHC().get(i));
            }
            
            for(int i=0;i<man.items.size();i++)
        {
            System.out.println("Item Name= "+man.items.get(i).get_item_name());
            System.out.println("Unit Profit= "+man.items.get(i).get_unit_profit());
            System.out.println("Number of Transaction= "+man.items.get(i).get_number_of_transaction());
            System.out.println("Number of Transaction ID= "+man.items.get(i).get_number_of_transactionID());
            System.out.println("Number of Transaction Utility= "+man.items.get(i).get_number_of_transaction_utility());
            ArrayList<Integer> transaction = man.items.get(i).get_transactions();
            ArrayList<String> tid = man.items.get(i).get_transactionsId();
            ArrayList<Integer> transaction_utility = man.items.get(i).get_transaction_utility();
            System.out.println("TransactionID values");
            for(int j=0;j<tid.size();j++)
            {
                System.out.print(tid.get(j)+"\t");
            }
            System.out.println();
            System.out.println("Transaction Values");
            for(int k=0;k<transaction.size();k++)
            {
                System.out.print(transaction.get(k)+"\t");
            }
            System.out.println();
            System.out.println("Transaction Utility");
            for(int l=0;l<transaction.size();l++)
            {
                System.out.print(transaction_utility.get(l)+"\t");
            }
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------");
          
        }
            System.out.println("---------------------------------------------------------------------------------------DAHU--------------------------------------");
            DAHU dahu = new DAHU(htwui.get_htwui(), support_count_itemset);
            LinkedHashSet<String> Dahu = dahu.get_DAHU();
//            dahu.clear();
            System.out.println(Dahu);
//            for(int i=0;i<Dahu.size();i++)
//            {
//                System.out.println(Dahu.get(i));
//            }
            return Dahu;
    }
    
}
