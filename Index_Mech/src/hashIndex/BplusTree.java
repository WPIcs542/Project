/**
 *  This is the Main.java for hash index project_2
 *  Team Members: Fangyu Lin, Hongzhang Cheng, Zhaojun Yang
 *  Date: Oct/16/2015
 */

package hashIndex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
//import java.util.Hashtable;

public class BplusTree {
	private ArrayList<Index> bPlusTree;
	private int length = 10;
	private int depth = 3;
	private static String filename = "cs542.db";
	
	/**
	 * The constructor
	 */
	public BplusTree(){
		File df = new File(filename);
		if(df.exists() && !df.isDirectory()) { 
			System.out.println("Database file exist !");
		}else{
			bPlusTree = new ArrayList<Index>();
			try {
				//if there is no such file, create it.
				ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(filename));
				for(int n=0; n<this.depth; n++){
					bPlusTree.add(new Index(length));
				}
				obout.writeObject(bPlusTree);
				obout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		df.exists();
		
		ObjectInputStream objectInput = null;
		try {
			objectInput = new ObjectInputStream (new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object object = null;
		try {
			object = objectInput.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// finally we put the data into memory space
		@SuppressWarnings("unchecked")
		ArrayList<Index> object2 = (ArrayList<Index>) object;
		this.bPlusTree = object2;
		
		try {
			objectInput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, int value){
		//we are not going to use B+ tree for index. 
	}
	
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, String value){
		
	}
	
	/**
	 * 
	 * @param value
	 * @return String is the key
	 */
	public String get(int value){
		//we are not going to use B+ tree for index.
		return "";
	}
	
	/**
	 * 
	 * @param value
	 * @return String is the key
	 */
	public String get(String value){
		return "";
	}
	
	/**
	 * 
	 * @param key
	 */
	public void remove(String key){
		
	}

}

