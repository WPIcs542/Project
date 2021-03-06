package redoLog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * CS542 Project_4
 * 
 * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
 * @date November/30/2015
 */

public class Main {
	static final int citypopindex = 4;
	static final int countrypopindex = 6;
	static Relation country = new Relation("country.db"); // Read into memory
	static Relation city = new Relation("city.db"); // read into memory
	static Relation city2 = new Relation("city_backup.db"); // backup file
	static Relation country2 = new Relation("country_backup.db"); // backup file
	static UpdateOp updateCity = new UpdateOp(citypopindex);
	static UpdateOp updateCountry = new UpdateOp(countrypopindex);

	static UpdateDB updateBackUpCity;
	static UpdateDB updateBackUpCountry;

	public static void main(String[] args) throws IOException {
		// init();
		long time_stamp;
		System.out.println("Here is the test line!");
		long startTime = System.currentTimeMillis();

		// increase population
		updateCity.open(city);
		updateCity.getNext();
		updateCity.close();
		time_stamp = System.currentTimeMillis();
		System.out.println("update city population Time: " + (time_stamp - startTime) + " ms.");

		// edit backup file using log file, run this carefully, takes really
		// long time.
		updateBackUpCity = new UpdateDB("city.log", citypopindex);
		updateBackUpCity.updateBackup(city2);
		System.out.println(
				"update city population backpup file Time: " + (System.currentTimeMillis() - time_stamp) + " ms.");
		time_stamp = System.currentTimeMillis();

		// increase population
		updateCountry.open(country);
		updateCountry.getNext();
		updateCountry.close();
		System.out.println("update country population Time: " + (System.currentTimeMillis() - time_stamp) + " ms.");
		time_stamp = System.currentTimeMillis();

		// edit backup file using log file, run this carefully, takes really
		// long time.
		updateBackUpCountry = new UpdateDB("country.log", countrypopindex);
		updateBackUpCountry.updateBackup(country2);
		System.out.println(
				"update country population backpup file Time: " + (System.currentTimeMillis() - time_stamp) + " ms.");
		time_stamp = System.currentTimeMillis();

		// spot-checks to verify that the country.db and country_backup.db
		// contain identical
		// data and that both show populations that are 2% higher than the
		// original.
		for (int k : country.getKeysArray()) {
			if (!Arrays.equals(country2.get(k), country.get(k))) {
				System.out.println("Error: Relations do not match");
				return; // should not return
			}
		}
		System.out.println(
				"compare country and country_backup files Time: " + (System.currentTimeMillis() - time_stamp) + " ms.");
		time_stamp = System.currentTimeMillis();

		// spot-checks to verify that the city.db and city_backup.db
		// contain identical
		// data and that both show populations that are 2% higher than the
		// original.
		for (int k : city.getKeysArray()) {
			if (!Arrays.equals(city2.get(k), city.get(k))) {
				System.out.println("Error: Relations do not match");
				return; // should not return
			}
		}
		System.out.println(
				"compare city and city_backup files Time: " + (System.currentTimeMillis() - time_stamp) + " ms.");

		System.out.println("Total Time: " + (System.currentTimeMillis() - startTime) + " ms.");
		System.out.println("finish"); // when you see this line,
										// congratulations!
	}

	/**
	 * init() function used for first time initialize the database if you
	 * mission the *.db file Be sure to have the city.txt and country.txt file
	 * ready to load into database.
	 * 
	 * @throws IOException
	 */
	public static void init() throws IOException {
		city = new Relation("city.db");
		country = new Relation("country.db");
		String cityfile = "city.txt";
		String countryfile = "country.txt";
		FileInputStream fs;
		InputStreamReader isr;
		BufferedReader b_read;
		String templine;
		String[] split_temp;
		try {
			fs = new FileInputStream(cityfile);
			isr = new InputStreamReader(fs);
			b_read = new BufferedReader(isr);
			while ((templine = b_read.readLine()) != null) {
				split_temp = templine.split(",");
				city.put(split_temp[0].hashCode(), templine.getBytes());
			}
			city.saveContents();
			b_read.close();
			isr.close();
			fs.close();

			fs = new FileInputStream(countryfile);
			isr = new InputStreamReader(fs);
			b_read = new BufferedReader(isr);
			while ((templine = b_read.readLine()) != null) {
				split_temp = templine.split(",");
				country.put(split_temp[0].hashCode(), templine.getBytes());
			}
			country.saveContents();
			b_read.close();
			isr.close();
			fs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Initialization of \"city.db\" and \"country.db\" successful!");
	}

}