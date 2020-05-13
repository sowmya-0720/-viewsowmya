package week1.day1;

public class Occuranc_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String  str = "You have no choice other than following me!";
		//replace all characters other than 'o' with space
		String replaceStr = str.replaceAll("[^o]", "").trim();
		
		System.out.println("\nOccurance of o is " + replaceStr.length());
		
	}

}
