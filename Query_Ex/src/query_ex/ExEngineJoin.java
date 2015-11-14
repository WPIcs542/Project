package query_ex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/**
 * CS542 Project_3
 * 
 * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
 * @date November/04/2015
 */

/**
 * this class is the function of join 
 * 
 */
public class ExEngineJoin {
	Relation city;
	Relation country;
	private ExEngineSelect exeSelect = new ExEngineSelect();

	public ExEngineJoin() {
	}
/**
 *  this is the object initialize, it read the data from the city.txt 
 *  and country.txt then turn them into two relation objects.
 *  
 * @throws IOException
 */

	public void init() throws IOException {
		city = new Relation("city.db");
		country = new Relation("country.db");
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
/**
 * this is the initialize of our two existing relations
 */
	public void open() {
		city = new Relation("city.db");
		country = new Relation("country.db");
	}
	
/**
 * this is the function used for get every tuple of the two joined relation and do 
 * the comparison based on the country code, we split the string we get from our
 * relation class and then compared based on these string, after the comparison,
 * we pipeline our results to ExEngineSelect class and do other function.
 * @throws UnsupportedEncodingException
 */
	public void getNext() throws UnsupportedEncodingException {
		String[] tumpleofcity = null;
		String[] tumpleofcountry = null;

		Enumeration<byte[]> enumofcity = city.getValuesEnum();
		Enumeration<byte[]> enumofcountry;
		while (enumofcity.hasMoreElements()) {
			byte[] city = enumofcity.nextElement();
			try {
				tumpleofcity = splitoftuple(city);
			} catch (UnsupportedEncodingException exp) {
				exp.printStackTrace();
			}
			enumofcountry = country.getValuesEnum();

			while (enumofcountry.hasMoreElements()) {
				byte[] country = enumofcountry.nextElement();
				try {
					tumpleofcountry = splitoftuple(country);
				} catch (UnsupportedEncodingException exp) {
					exp.printStackTrace();
				}
				if (tumpleofcity[2].equals(tumpleofcountry[0])) {
					String tuple = new String(country, StandardCharsets.UTF_8) + ","
							+ new String(city, StandardCharsets.UTF_8);
					pipelineExe(tuple);
				}
			break;}
		}
	}
	/**
	 * this function is used for split the input byte array, at first we transform
	 * our byte array to a string then we split the existing string based on the commas
	 * @param tuple
	 * @return string array
	 * @throws UnsupportedEncodingException
	 */
	public String[] splitoftuple(byte[] tuple) throws UnsupportedEncodingException {
		String str = new String(tuple, StandardCharsets.UTF_8);
		return str.split(","); // ignores commas inside quotation marks
	}
/**
 * this function used for connect the exeSelect object with the joinResult,so we don't need
 * to do the open when we need to do the next operation
 * @param joinResult
 * @throws UnsupportedEncodingException
 */
	public void pipelineExe(String joinResult) throws UnsupportedEncodingException {
		exeSelect.getNext(joinResult);	
	}
/**
 * clear the relation we read in 
 */
	public void close() {
		city.clear();
     	country.clear();
	}
}
