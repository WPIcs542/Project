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
		hash.put("3", "1");
		hash.put("4", "1");
		hash.put("5", "1");
		hash.put("6", "1");
		hash.put("7", "1");
		hash.put("8", "1");
		
	}


}
