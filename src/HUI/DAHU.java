/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 *
 * @author Anspro
 */
public class DAHU {
private ArrayList<String> apriori_hc = new ArrayList<String>();
private ArrayList<String> SC = new ArrayList<String>();
private LinkedHashSet<String> DAHU = new LinkedHashSet<String>();
    public DAHU(ArrayList<String> ap,ArrayList<String> sc) {
        this.apriori_hc = ap;
        this.SC = sc;
        initiate();
    }
    
    private void initiate()
    {
        Iterator itr = apriori_hc.iterator();
        while(itr.hasNext())
        {
            String hc = itr.next().toString();
            String[] h = hc.split(" ");
            Iterator itr1 = SC.iterator();
            while(itr1.hasNext())
            {
                String dc = itr1.next().toString();
//                System.out.println("dc= "+dc+"\nhc="+hc);
                String[] d = dc.split("-");
//                System.out.println(h[0]+">>>"+d[0]);
                if(h[0].equalsIgnoreCase(d[0]))
                {
                    DAHU.add(dc);
                }
            }
        }
    }
    
    public LinkedHashSet<String> get_DAHU()
            {
                return DAHU;
            }
    public void clear()
    {
        DAHU.clear();
    }
}
