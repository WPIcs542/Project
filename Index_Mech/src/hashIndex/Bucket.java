package hashIndex;

import java.io.Serializable;
import java.util.HashMap;

public class Bucket implements Serializable{
	private static final long serialVersionUID = -9184164241837197805L;
	private int blockBitsNumber;
	public static final int blockSize = 2;
	private HashMap[] blockContents;
	
	/**
	 * This is constructor. 
	 * @param blockBitsNumber
	 */
	public Bucket(int blockBitsNumber){
		this.blockBitsNumber = blockBitsNumber;
	//	this.blockContents = new HashMap[blockSize];	
	}
	
	public int getLength(){
		return blockBitsNumber;
	}
	
	public void incrementLength(){
		this.blockBitsNumber++;
	}
	
	public void insert(String key, String dataValue){
		for(int i=0 ; i<blockSize ; i++){
			if(blockContents[i] ==null){
				blockContents[i].put(key, dataValue);
				return;
			}	
		}
	}

	
	public boolean ifExistSpace(){
		if(this.blockContents[blockSize-1] == null){
			return true;
		}else return false;
	}
	
}
