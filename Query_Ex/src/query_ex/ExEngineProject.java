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
public class ExEngineProject {
	private Relation projectResult = new Relation("projectResult.db");

	public void open() {
		// useless in pipeline, intentionally left blank
	}

	public void getNext(String tuple[]) throws UnsupportedEncodingException {
		int key = (int) Double.parseDouble(tuple[15]);
		byte[] value = tuple[16].getBytes();
		projectResult.put(key, value);

	}

	public void close() {
		// useless in pipeline, intentionally left blank

	}

	public String[] splitOfTuple(String str) throws UnsupportedEncodingException {
		return str.split(","); // ignores commas inside quotation marks
	}

}
