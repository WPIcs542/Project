package query_ex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * CS542 Project_3
 * 
 * @author Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
 * @date November/04/2015
 */

/**
 * this class is for projecting the attributes we want in a relation
 * @author 
 *
 */
public class ExEngineProject {
	private Relation projectResult = new Relation("projectResult.db");

	public void open() {
		// useless in pipeline, intentionally left blank
	}
/**
 * this function is get the attribute we want in a tuple and then put it into another relation hash table  
 * @param tuple
 * @throws UnsupportedEncodingException
 */
	public void getNext(String tuple[]) throws UnsupportedEncodingException {
		int key = (int) Double.parseDouble(tuple[15]);
		byte[] value = tuple[16].getBytes();
		projectResult.put(key, value);

	}

	public void close() {
		// useless in pipeline, intentionally left blank

	}
/**
 * the same function used for split a string 
 * @param str
 * @return
 * @throws UnsupportedEncodingException
 */
	public String[] splitOfTuple(String str) throws UnsupportedEncodingException {
		return str.split(","); // ignores commas inside quotation marks
	}

}
