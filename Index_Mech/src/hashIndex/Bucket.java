/**
 *  This is the Main.java for hash index project_2
 *  Team Members: Fangyu Lin, Hongzhang Cheng, Zhaojun Yang
 *  Date: Oct/26/2015
 */

package hashIndex;

import java.io.Serializable;

public class Bucket implements Serializable{
	// this bucket class is a data structure inside our hash table used for store data in
	//hash table ,we actually use an array in bucket to store the input data, and use link list
	//to store the data with the same key if the space in last bucket is not enough
	private static final long serialVersionUID = -9184164241837197805L;
	private int savebitNumber;  
	public int blockSize = 2;
	private KeyValuePair[] blockContents;
	private Bucket next;
	
	/**
	 * This is constructor of bucket which is used for stored input data in hashtable. 
	 * @param blockBitsNumber
	 */
	public Bucket(){

	 	this.blockContents = new KeyValuePair[blockSize];
	}
	

    /**
     * This setNext method is used for setting the bucket point to the selected bucket
     * @param bucket
     */
	public void setNext(Bucket bucket){
		this.next = bucket;
	}
	/**
	 * This getNext method is used for return the next bucket 
	 * @return
	 */
	public Bucket getNext(){
		return this.next;
	}
	/**
	 * this method is used for getting the wanted BlockContents
	 * @return
	 */
	public KeyValuePair[] getBlockContents(){
		return blockContents;
	}
	
    public void saveBitNum(int n){
		this.savebitNumber = n;
	}
	
	public int getBitNum(){
		return this.savebitNumber;
	}
	
	/**
	 * this method is used for insert data and key to the certain bucket when they have enough space 
	 * then print out put key into index succeed
	 * @param key
	 * @param dataValue
	 */
	public void insert(String key, String dataValue){
		
		for(int i=0 ; i<blockSize ; i++){
			if(this.blockContents[i] ==null||(this.blockContents[i].getKey()==""&&this.blockContents[i].getValue()=="")){
				this.blockContents[i] = new KeyValuePair(key, dataValue);
				System.out.println("Put key into index succeed");
				return;
			}	
		}
		
	}
    /**
     * this method is used for searching key based on datavalue, if the same data value has 
     * different keys collect them all in one string and return it  
     * @param value
     * @return
     */
	public String getKey(String value){
		String localKey = "";
		for(int i=0 ; i<blockSize ; i++){
			if(blockContents[i] != null && this.blockContents[i].getValue().equals(value)){
				if(localKey == ""){
					localKey=new String(blockContents[i].getKey());
				}else{
				localKey+=new String(", "+blockContents[i].getKey());								
				}		
			}
		}
		return localKey;
	}
	/**
	 * this method is used for testing whether there are enough space in the buckets which is empty 
	 * blockcontents elements, if it contain enough space return true, if not return false
	 * @return
	 */
	public boolean ifExistSpace(){
		for(int i = 0; i<blockSize; i++){
			if(blockContents[i] == null||(this.blockContents[i].getKey()==""&&this.blockContents[i].getValue()=="")){	
				return true;
			}
		}
		return false;
	}
	/**
	 * this method is used for remove datavalue and key from the block
	 * @param key
	 * @param blockIndex
	 */
	public void remove(String key, int blockIndex){
			
				this.blockContents[blockIndex] = null;
     			System.out.println("The key \""+ key + "\" has been removed from index!");
				return;
			}
	
	/**
	 * this method is used for refreshing the block,clearing all the data
	 */
	public void refreshBucket(){
		this.blockContents  = new KeyValuePair[blockSize];
	}

//	public void expandSpace(){
//		blockSize *= 2;
//		KeyValuePair newBucket[] = new KeyValuePair[blockSize];
//		for(int n=0; n<blockSize/2; n++){
//			newBucket[n] = this.blockContents[n];
//		}
//		this.incrementLength();
//		this.blockContents = newBucket;
//	}
}

