package week1.day1;

public class Palindrome_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pal = "testleaf";
		String rev = "";
		//extract the characters from the end of the string
		for (int i= pal.length()-1; i >= 0 ; i--)
		{
			rev += pal.charAt(i);
			
			
		}
		System.out.println("Reverse string is" + rev);
		System.out.println(pal.equals(rev) ? "Yes, its a palindrome" : "No, its not a palindrome");
	}

}
