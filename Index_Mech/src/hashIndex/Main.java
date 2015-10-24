/**
 *  This is the Main.java for hash index project_2
 *  Team Members: Fangyu Lin, Hongzhang Cheng, Zhaojun Yang
 *  Date: Oct/16/2015
 */
 
package hashIndex;

public class Main {
	static HashIndex hash;
	
	/**
	 * run testing through Hashindex.
	 * @param args
	 */
	public static void main(String[] args) {
		hash = new HashIndex();
		System.out.println("Test line of Hash Index!!");
		hash.put("1", "1");
		hash.put("2", "1");
		hash.put("1", "2");
		hash.put("4", "2");
		hash.put("5", "3");
		hash.put("5", "3");
		hash.put("7", "4");
		hash.put("8", "4");
		
		System.out.println("The value \"2\" has key: " + hash.get("2"));
		System.out.println("The value \"4\" has key: " + hash.get("4"));
		System.out.println("The value \"1\" has key: " + hash.get("1"));
		System.out.println("The value \"5\" has key: " + hash.get("5"));
		
		hash.remove("8");
		hash.remove("6");
		hash.remove("4");
		hash.remove("2");
		hash.remove("3");
		hash.remove("1");
		hash.remove("5");
		hash.remove("7");
		
		hash.saveContents();
		System.out.println();
		System.out.println("Job Done!");
	}


}
