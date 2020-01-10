/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HUI;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Anspro
 */
public class delete_it_afterwards {
    private StringBuilder output = new StringBuilder();
    private  String inputstring = "";
    public delete_it_afterwards(String in) {
        this.inputstring = in;
        System.out.println("The input string  is  : " + inputstring);
    }
    
    
    public static void main(String[] args) {
       delete_it_afterwards del = new delete_it_afterwards("12345");
        System.out.println("");
        System.out.println("");
        System.out.println("All possible combinations are :  ");
        System.out.println("");
        System.out.println("");
        del.combine(0);
        System.out.println(">>>>>>"+del.inputstring);
        String name = "ABF";
        System.out.println(">>>"+String.valueOf(name.charAt(1)));
        
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("T1");
        arr.add("T2");
        arr.add("T3");
        arr.add("T4");
        arr.add("T5");
 
        try
        {
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        //System.out.println(delete_it_afterwards.combine(arr));
    }
    
        static void permute(int[] a, int k) 
    {
        if (k == a.length) 
        {
            for (int i = 0; i < a.length; i++) 
            {
                System.out.print(" [" + a[i] + "] ");
            }
            System.out.println();
        } 
        else 
        {
            for (int i = k; i < a.length; i++) 
            {
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;
                permute(a, k + 1);
                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
    }
        
//        public void combine()
//        { 
//            combine(0);
//        }
    private void combine(int start )
    {
        for( int i = start; i < inputstring.length(); ++i ){
            output.append( inputstring.charAt(i) );
            if(output.length()>1)
            System.out.println(output.toString());
            if ( i < inputstring.length() )
            {
            combine( i + 1);
            }
            output.setLength( output.length() - 1 );
        }
    }
    
   
}
