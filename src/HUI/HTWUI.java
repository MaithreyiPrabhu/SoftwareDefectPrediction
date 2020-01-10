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
public class HTWUI {
private ArrayList<String> htwui = new ArrayList<String>();
private ArrayList<String> twu = new ArrayList<String>();
private ArrayList<String> unpromising_items = new ArrayList<String>();
private int abs_min_utility = 0;
    public HTWUI(ArrayList<String> arr,int threshold) {
        this.twu = arr;
        this.abs_min_utility = threshold;
        this.generate();
    }
    
    private void generate()
    {
        for(String str:twu)
        {
            String[] s = str.split("-");
            int twu_value = Integer.parseInt(s[1]);
            if(twu_value>abs_min_utility)
            {
                htwui.add(s[0]+" "+twu_value);
            }
            else
            {
                unpromising_items.add(s[0]+" "+twu_value);
            }
        }
    }
    
    
    public ArrayList<String> get_htwui()
    {
        return htwui;
    }
    
    public ArrayList<String> get_unpromising_items()
    {
        return unpromising_items;
    }
}
