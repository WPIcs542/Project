package redoLog;

/**
 * CS542 Project_4
 * 
 * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
 * @date November/19/2015
 */

public class Main {
	static Relation country = new Relation("country.db"); // Read into memory
	static Relation city = new Relation("city.db");	//read into memory
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Here is the test line!");
		country.listTable();
//		city.listTable();
	}

}
