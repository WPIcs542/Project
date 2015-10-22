package hashIndex;

import java.io.Serializable;

public class KeyValuePair implements Serializable{
	private String key;
	private String dataValue;
	
    public KeyValuePair(String key, String dataValue) {
	this.key = key;
	this.dataValue = dataValue;
    }
    
}
