// This is the function support file
// Fangyu Lin, Hongzhang Cheng, Zhaojun Yang
// Sep.26.2015

package vStore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Hashtable;

public class Vstore{
	private Hashtable<Integer, byte[]> data;
	private static final String file = "cs542.db";
	private static final int MAX_VALUE_SIZE = 1024 * 1024;
	private Vstore(){
		String kk = "myName";
		int vKey = kk.hashCode();
		
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
		byte[] b=NULL;
                b=data.get(key);
                return b;
	}
	
	public boolean remove(int key){
		// synchronized blocks can only have one thread executing at the same time
	        synchronized(this){
                        String dd;
                        dd =data.remove(key);
                }
                saveContents(); 
                if(dd==Null)
                        return false;
                else
                        return true; 
	}
	
	public boolean clear(){
		Hashtable<Integer, byte[]> newdata = new Hashtable<Integer, byte[]>();
		this.data = newdata;
		return true;
	}
	public void saveContents(){
                try{
                    FileOutStream file =new FileOutputStream();      
                    objectOutputStream objO = new ObjectoutputStream(file);
                    objO.writeObject(data);
                    objO.close();
      
                 }catch(IOException ex){
                        ex.printStackTrace(); 
                 }
}
