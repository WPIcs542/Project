package redoLog;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

import javax.imageio.IIOException;

public class UpdateOp {
	Relation city = new Relation("city.db");
	Relation country = new Relation("country.db");
	Relation r;
	Relation oldcity =city;
	Relation oldcountry= country;
	int popindex;
	byte[] newenumofr=null;
	RedoLog log;
	
	
	public UpdateOp()  {
//	 try{	
//		this.r=r;
//		if(j==4){
//			popindex=4;	
//			log= new RedoLog("city.log");
//		}else{
//			popindex=6;
//			log= new RedoLog("country.log");
//		}
//	 }catch(IOException e){
//		 e.printStackTrace();
//	 }
	}
		
	
	public void UpdateOP(Relation r,int j){
		try{	
			this.r=r;
			if(j==4){
				popindex=4;	
				log= new RedoLog("city.log");
			}else{
				popindex=6;
				log= new RedoLog("country.log");
			}
		 }catch(IOException e){
			 e.printStackTrace();
		 }
	
		String[] tumpleofr = null;
		Enumeration<byte[]> enumofr = r.getValuesEnum();
		while (enumofr.hasMoreElements()){
			byte[] rtupleofbyte = enumofr.nextElement();
			
			try {
				tumpleofr = splitoftuple(rtupleofbyte);
				
			} catch (UnsupportedEncodingException exp) {
				exp.printStackTrace();
			}
			Double tupleP = Double.parseDouble(tumpleofr[popindex]);
			Double newtumpleP=tupleP*1.2;
			tumpleofr[popindex]=newtumpleP.toString();
           try{
        	   newenumofr=unsplitoftuple(tumpleofr);
           }catch(UnsupportedEncodingException e){
        	   e.printStackTrace();
        	   
           }
           r.put(tumpleofr[0].hashCode(), newenumofr);
           
           log.writeUpdate(tumpleofr[1], tupleP, newtumpleP);

             
		}
		r.saveContents();
        try{log.savelog();}catch(IOException e){e.printStackTrace();}
        log.clearlog();
		
	}
	
	public void open(){
		
	}
	
	public void getNext(){
		if(popindex==4){r=oldcity;}else{r=oldcountry;};
		byte[] newenumofr=null;
		
		String[] tumpleofr = null;
		Enumeration<byte[]> enumofr = r.getValuesEnum();
		while (enumofr.hasMoreElements()){
			byte[] rtupleofbyte = enumofr.nextElement();			
			try {
				tumpleofr = splitoftuple(rtupleofbyte);
			} catch (UnsupportedEncodingException exp) {
				exp.printStackTrace();
			}
			Double tupleP = Double.parseDouble(tumpleofr[popindex]);
			tumpleofr[popindex]=(tupleP*=1.02).toString();
           try{
        	   newenumofr=unsplitoftuple(tumpleofr);
           }catch(UnsupportedEncodingException e){
        	   e.printStackTrace();
        	   
           }
        
           r.put(tumpleofr[0].hashCode(), newenumofr);
           }
	    r.saveContents();
	    }
		
	
	
	public void close(){
     log.writeCommit();
	 try{log.savelog();}catch(IOException e){e.printStackTrace();}
	 log.clearlog();
	 this.getNext();
	 
		
	}
	
	public String[] splitoftuple(byte[] tuple) throws UnsupportedEncodingException {
		String str = new String(tuple, StandardCharsets.UTF_8);
		return str.split(","); // ignores commas inside quotation marks
	}
	protected static byte[] unsplitoftuple(String[] splitArray)throws UnsupportedEncodingException {
			String retString = null;
			for (String str : splitArray) {
			    if (retString == null) {
				retString = str;
			    }
			    else {
				retString = retString + "," + str;
			    }
			}
			
			return retString.getBytes("UTF-8");
		    }
}
