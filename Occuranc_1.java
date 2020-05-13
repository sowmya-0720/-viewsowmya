package week1.day1;

public class Occuranc_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String  str = "You have no choice other than following me!";
		
		//need to find the no. of 'o' in the string
		
		int count = 0;
		// convert string to char array
		char [] array = str.toCharArray();
		for(int i=0; i < array.length ; i++)
		{
			if (array[i] == ('o'))
			{
				count++;
				
			}
		}
		System.out.println("Occurence of o in the string " + count);
		
		
		

	}

}
