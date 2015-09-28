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
import java.util.Hashtable;

public class Vstore{
	private Hashtable<Integer, byte[]> data;
	private static final String file = "cs542.db";
	private static final int MAX_VALUE_SIZE = 1024 * 1024;
	public Vstore(){
		File df = new File(file);
		if(df.exists() && !df.isDirectory()) { 
			System.out.println("Data file exist !");
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
//		df.exists();
	}
	
	//Java singleton
	private static class VstoreLoader {
        private static final Vstore instance = new Vstore();
    }

	//Java singleton
	private static Vstore getInstance(){
		return VstoreLoader.instance;
	}
	
	public boolean put(int key, byte[] value){
		// synchronized blocks can only have one thread executing at the same time
		synchronized(this) {
			//Check if the value satisfies the size requirement
			if(value.length > MAX_VALUE_SIZE){
				System.out.println("------Value should not be larger than 1 MB!------");
				return false;
			} else {
				data.put(key, value);			
			}
		}
		return true;
	}
	
	public byte[] get(int key){
		byte[] b=null;
                b=data.get(key);
                return b;
	}
	
	public boolean remove(int key){
		// synchronized blocks can only have one thread executing at the same time
		byte[] dd = new byte[1024];
	    synchronized(this){
	    	dd = data.remove(key);
	    }
        saveContents(); 
        if(dd.length == 0){
        	return false;
        }
        return true;
	}
	
	public boolean clear(){
		Hashtable<Integer, byte[]> newdata = new Hashtable<Integer, byte[]>();
		this.data = newdata;
		return true;
	}
	public void saveContents(){
//                public void saveContents(){
                try{
                    FileOutputStream f =new FileOutputStream(file);      
                    ObjectOutputStream objO = new ObjectOutputStream(f);
                    objO.writeObject(data);
                    objO.close();
      
                 }catch(IOException ex){
                        ex.printStackTrace(); 
                 }
	}
}
