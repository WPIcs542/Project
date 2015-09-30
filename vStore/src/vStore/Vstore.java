// This is the function support file
// Fangyu Lin, Hongzhang Cheng, Zhaojun Yang
// Sep.26.2015

package vStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Set;

public class Vstore{
	private Hashtable<Integer, byte[]> data;
	private static final String file = "cs542.db";
	private static final int MAX_VALUE_SIZE = 1024 * 1024;
	
	public Vstore(){
		File df = new File(file);
		if(df.exists() && !df.isDirectory()) { 
			System.out.println("Database file exist !");
		}else{
			try {
				//if there is no such file, create it.
				ObjectOutputStream obout = new ObjectOutputStream(new FileOutputStream(file));
				obout.writeObject(new Hashtable<Integer, byte[]>());
				obout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		df.exists();
		ObjectInputStream objectInput = null;
		try {
			objectInput = new ObjectInputStream (new FileInputStream(file));
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
		data = (Hashtable<Integer, byte[]>) object;
		try {
			objectInput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Stores data under the given key
	public boolean put(int key, byte[] value){
		// synchronized blocks can only have one thread executing at the same time
		synchronized(this) {
			//Check if the value satisfies the size requirement
			if(value.length > MAX_VALUE_SIZE){
				System.out.println("Value should not be larger than 1 MB!");
				return false;
			} else {
				Set<Integer> keys = data.keySet();
				int size = 0;
		        for(Integer thekey: keys){
		        	size += data.get(thekey).length;
		        }
				if(size + value.length > MAX_VALUE_SIZE*4.1){
					System.out.println("Database has no space to put data! the attempt key=" + key);
					return false;
				}else if(data.containsKey(key)){
					System.out.println("The key exist !");
					return false;
				}
				data.put(key, value);			
			}
		}
		saveContents();
		return true;
	}
	
	public byte[] get(int key){
		if(!data.containsKey(key)){
			System.out.println("The key does not exist!");
        	return new byte[0];	
		}
		else {
			// synchronized blocks can only have one thread executing at the same time
			synchronized(this){
				byte[] b=null;
				b=data.get(key);
				return b;
			}
		}  
	}
	
	public boolean remove(int key){
		//test if the key exists
		if(!data.containsKey(key)){
			System.out.println("The key does not exist!");
        	return false;	
		}
		else {
			// synchronized blocks can only have one thread executing at the same time
			synchronized(this){
				data.remove(key);
			}
		}
        saveContents();
        return true;
	}
	
	//this is used in the reboot testing. 
	public boolean clear(){
		Hashtable<Integer, byte[]> newdata = new Hashtable<Integer, byte[]>();
		this.data = newdata;
		return true;
	}
	
	//this method is to write data into cs542.db and the same as write to commit. 
	public void saveContents(){       
		try{
			FileOutputStream f =new FileOutputStream(file);      
			ObjectOutputStream objOutput = new ObjectOutputStream(f);
			objOutput.writeObject(data);
			objOutput.close();
		}catch(IOException ex){
			ex.printStackTrace(); 
		}
	}
	
	// This is the function to show all the information in database. 
	public boolean listTable(String filename){
		if(!filename.equalsIgnoreCase("542.db")){
			System.out.println("File does not exist !");
			return false;
		}
		Set<Integer> keys = data.keySet();
		int size = 0;
        for(Integer key: keys){
        	size = data.get(key).length;
            System.out.println("Value of Key: " + key + "\t--The size of data: "+ size + " Bytes");
        }
        return true;
	}
}
