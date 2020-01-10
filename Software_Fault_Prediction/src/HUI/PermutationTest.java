package HUI;
public class PermutationTest
{
   public static void main(String args[])
   {
	   PermutationTest obj=new PermutationTest();
           String[] str = {"T1","T2","T3","T4"};
	   obj.getCombination2(str);
   }
   
   public void  getCombination2(String[] data)
	{
		int len=data.length;
                String d = "";
		for(int i=0;i<len;i++)
		{
			for(int k=i;k<len;k++)
			{
                                                                                    System.out.println(data[k]);
				 d+=data[k]+",";                                
//				System.out.print(c);
			}
                        d = d.substring(0, d.lastIndexOf(","));
//                        if(d.length()>2)
//                        {
			System.out.println(">>>"+d);
//                        }
                        d = "";
		}
	}
}
