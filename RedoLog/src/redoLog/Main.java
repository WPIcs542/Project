package redoLog;

import java.io.IOException;

/**
 * CS542 Project_4
 * 
 * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
 * @date November/19/2015
 */

public class Main {
	static Relation country = new Relation("country.db"); // Read into memory
	static Relation city = new Relation("city.db");	//read into memory
	static RedoLog citylog = new RedoLog();
	static RedoLog countrylog = new RedoLog();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Here is the test line!");
		country.listTable();

		//this is only need to be ran once to initialization log files.
//		try {
//			citylog.init("city.log");
//			countrylog.init("country.log");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			citylog.readLargerTextFile("city.log");
			countrylog.readLargerTextFile("country.log");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		citylog.printlog();
		countrylog.printlog();
		
	}

}
