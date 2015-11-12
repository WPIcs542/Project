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
	private Relation joinResult;
	private Enumeration<byte[]> joinResultEnume;
	private Relation projectResult = new Relation("projectResult.db");
	private ArrayList<String> finalResult = new ArrayList<String>();
	private String tupleResult;

	public void open() {
	//	Relation joinResult = new Relation("joinResult.db");
	//	joinResultEnume = joinResult.getValuesEnum();
	//	tupleResult = joinResult;
	}

	public void getNext(String tuple[]) throws UnsupportedEncodingException {
		int key = (int)Double.parseDouble(tuple[15]);
		byte[] value = tuple[16].getBytes();
		projectResult.put(key, value);
		
	}

	public void close() {
		Enumeration<byte[]> projectResultEnume = projectResult.getValuesEnum();
		while (projectResultEnume.hasMoreElements()) {
			finalResult.add(new String(projectResultEnume.nextElement()));
		}
	}

	public ArrayList<String> getFinalResult() {
		return finalResult;
	}

	public String[] splitOfTuple(String str) throws UnsupportedEncodingException {	
		return str.split(","); // ignores commas inside quotation marks
	}
	
}
