package HUI;
public class Permutation 
{
	
	public static void main (String args[])
	{
		getCombination("kaushal");
		System.out.println();
		System.out.println();

		Permutation obj=new Permutation();
		obj.getCombination1("kaushal");
		System.out.println();
		System.out.println();

		
		obj.getCombination2("kaushal");
	}
	
	public static void  getCombination(String data)
	{
		int len=data.length();
		for(int i=0;i<len;i++)
		{
			for(int k=0;k<=i;k++)
			{
				char c=data.charAt(k);
				System.out.print(c);
			}
			System.out.print(" ");
		}
	}
	
	public void  getCombination1(String data)
	{
		int len=data.length();
		int m=0;

		for(int i=0;i<len-1;i++)
		{
			for(int k=m;k<i+2;k++)
			{
				char c=data.charAt(k);
				System.out.print(c);
				
			}
			System.out.print(" ");
			m++;//
		}
	}
	
	public void  getCombination2(String data)
	{
		int len=data.length();
		for(int i=0;i<len;i++)
		{
			for(int k=i;k<len;k++)
			{
				char c=data.charAt(k);
				System.out.print(c);
				
			}
			System.out.print(" ");
		}
	}


}
