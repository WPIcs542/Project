package query_ex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CS542 Project_3
 * 
 * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
 * @date November/04/2015
 */

public class ExEngineJoin {
	Relation city;
	Relation country;
	String cityfile = "city.txt";
	String countryfile = "country.txt";
	// to store result and to be used in PROJECT operator
	private Relation joinResult = new Relation("joinResult.db");

	public ExEngineJoin() {
	}

	public void init() throws IOException {
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
			while ((templine = b_read.readLine()) != null) {
				city.put(templine.getBytes().hashCode(), templine.getBytes());
			}
			city.saveContents();
			b_read.close();
			isr.close();
			fs.close();

			fs = new FileInputStream(countryfile);
			isr = new InputStreamReader(fs);
			b_read = new BufferedReader(isr);
			while ((templine = b_read.readLine()) != null) {
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

	public void open() {
		city = new Relation("city.db");
		country = new Relation("country.db");
		try {
			this.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// put the whole joined tuple into joinResult
	public void getNext() {

	}

	public void close() {
		// joinResult.saveContents();
	}
}
