package query_ex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CS542 Project_3
 * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
 * @date November/04/2015
 */

public class Ex_Engine {
	Relation city = new Relation("city.db");
	Relation country = new Relation("country.db");
	String cityfile = "city.txt";
	String countryfile = "country.txt";
	
	public Ex_Engine(){}
	
	public void init() throws IOException{
		String cityfile = "city.txt";
		String countryfile = "country.txt";
		FileInputStream fs;
		InputStreamReader isr;
		BufferedReader b_read;
		String templine;
		try {
			fs = new FileInputStream(cityfile);
			isr = new InputStreamReader(fs);
			b_read = new BufferedReader(isr);
			while((templine=b_read.readLine()) != null){
				city.put(templine.getBytes().hashCode(), templine.getBytes());
			}
			city.saveContents();
			b_read.close();
			isr.close();
			fs.close();
			
			fs = new FileInputStream(countryfile);
			isr = new InputStreamReader(fs);
			b_read = new BufferedReader(isr);
			while((templine=b_read.readLine()) != null){
				country.put(templine.getBytes().hashCode(), templine.getBytes());
			}
			country.saveContents();
			b_read.close();
			isr.close();
			fs.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Initialization of \"city.db\" and \"country.db\" successful!");
	}
	
	public void open(){
		
	}
	
	public void getNext(){
		
	}
	
	public void close(){
		
	}
}
