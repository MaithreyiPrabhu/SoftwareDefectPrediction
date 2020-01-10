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
public class Transaction_Utility {
private ArrayList<String> tid = new ArrayList<String>();
private ArrayList<String> absolute_utility = new ArrayList<String>();
private ArrayList<String> Transaction_utility = new ArrayList<String>();
private String total_utility = "";
    public Transaction_Utility(ArrayList<String> arr,ArrayList<String> arr1) {
        this.absolute_utility = arr;
        this.tid = arr1;
        initiate();
    }
    
    private void initiate()
    {
        int tu = 0;
        for(int i=0;i<tid.size();i++)
        {
            tu=0;
            String tids = tid.get(i);
            for(int j=0;j<absolute_utility.size();j++)
            {
                if(tids.equalsIgnoreCase(absolute_utility.get(j).split("-")[1]))
                {
                    tu+=Integer.parseInt(absolute_utility.get(j).split("-")[2]);
                }
            }
            Transaction_utility.add(tids+" "+tu);
        }
        total_utility();
    }
    
    private void total_utility()
    {
        int tot = 0;
        for(int i=0;i<Transaction_utility.size();i++)
        {
            tot+=Integer.parseInt(Transaction_utility.get(i).split(" ")[1]);
        }
        total_utility = "Total Utility "+tot;
    }
    
    public ArrayList<String> get_transaction_utility()
    {
        return Transaction_utility;
    }
    
    public String get_total_utility()
    {
        return total_utility;
    }
}
