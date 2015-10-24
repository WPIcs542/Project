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

public class HashIndex {
	private ArrayList<Bucket> hashIndex;
	private int decisionBitsNumber = 2; //"i" in textbook
	static int initialBlockBitsNumber = 2; //"j" in text book
	private static String filename = "cs542.db";
	
	/**
	 * The constructor
	 */
	public HashIndex(){
		File df = new File(filename);
		if(df.exists() && !df.isDirectory()) { 
			System.out.println("Database file exist !");
		}else{
			hashIndex = new ArrayList<Bucket>();
			
			for(int n=0; n<Math.pow(2, this.decisionBitsNumber); n++){
				hashIndex.add(new Bucket(initialBlockBitsNumber));
			}
			try {
				//if there is no such file, create it.
				ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(filename));
				
				obout.writeObject(hashIndex);
				obout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		df.exists();
		
		ObjectInputStream objectInput = null;
		try {
			objectInput = new ObjectInputStream (new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Object object = null;
		try {
			object = objectInput.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// finally we put the data into memory space
		@SuppressWarnings("unchecked")
		ArrayList<Bucket> object2 = (ArrayList<Bucket>) object;
		this.hashIndex = object2;	
		try {
			objectInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to put data in hash. Extensible hash tables are used.
	 * @param key
	 * @param value
	 */
	public synchronized void put(String key, String dataValue){
		
		int bucketId = Math.abs(key.hashCode()) % hashIndex.size();
		Bucket bucket = hashIndex.get(bucketId);
		if(bucket.ifExistSpace()){
			bucket.insert(key, dataValue);
		}else{
			//if "i" equals "j"
			if(bucket.getLength() == decisionBitsNumber){
				decisionBitsNumber++;
				bucket.incrementLength();
			    for(int i = 0; i< Math.pow(2, decisionBitsNumber-1);i++ ){
			    	//create a new block
			    	if(i==bucketId){
			    		hashIndex.add(new Bucket(bucket.getLength()));
			    	}
			    	//reference to old block
			    	else{
			    		Bucket bucketTemp = hashIndex.get(i);
			    		hashIndex.add(bucketTemp);
			    	}
			    }
			    	redistribute(bucket);
			    	System.out.println("extend");
			    
			}else{//if "i" > "j", double the block
				bucket.incrementLength();
				int bucketTempId = (int) (bucketId + Math.pow(2, decisionBitsNumber-1));
				Bucket bucketTemp = new Bucket(bucket.getLength());
				hashIndex.set(bucketTempId, bucketTemp);
				redistribute(bucket);
				System.out.println("split");
				}
			put(key, dataValue);
		}
	}
	
	
	/**
	 * Redistribute the elements in each block. 
	 * Clean the block then put the elements in again.
	 * 
	 * @param bucket
	 */
	private void redistribute(Bucket bucket){
		KeyValuePair tempContents[] = bucket.getBlockContents();
		bucket.refreshBucket();
		for(int i = 0; i< bucket.blockSize; i++){
			put(tempContents[i].getKey(), tempContents[i].getValue());
		}
	}	

	/**
	 * 
	 * @param value
	 * @return String is the key
	 */
	public String get(String key){
		
		return "";
	}
	
	
	
	/**
	 * method to remove data in hash
	 * @param key
	 */
	public void remove(String key){
		int bucketId = Math.abs(key.hashCode()) % hashIndex.size();
		Bucket bucket = hashIndex.get(bucketId);
		bucket.remove(key);
		
		  
		}
		
	}


