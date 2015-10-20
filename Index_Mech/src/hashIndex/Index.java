package hashIndex;

import java.io.Serializable;

public class Index implements Serializable{
	private static final long serialVersionUID = -9184164241837197805L;
	private static int totalLength;
	
	/**
	 * This is constructor. 
	 * @param totalLength
	 */
	public Index(int totalLength){
		this.totalLength = totalLength;
	}
	
	public int getLength(){
		return totalLength;
	}
	
}
