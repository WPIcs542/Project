// This is the Main Shell
// Sep.26.2015
// Fangyu Lin, Hongzhang Cheng, Zhaojun Yang

package vStore;

import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main{
    static Vstore store = new Vstore();
    static final int vKey = 428657931;
    static final int testKey = 31415924;
    private static final int MAX_VALUE_SIZE = 1024 * 1024;
    
    public static void main(String [] args){
        boolean flag;
        boolean running = true;
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);;
        int option = 0;
        
        while(running){
        	if(option==0){
        		System.out.println("------Welcome to the Value Store !------");
        		System.out.println("---------Here is the Main Menu----------");
        		System.out.println("--------Select the number options below:");
        		System.out.println("1) Put String data----------------------");
        		System.out.println("2) Get String Data by Key---------------");
        		System.out.println("3) Testing get and put at the same time-");
        		System.out.println("4) Testing remove and get at the same time");
        		System.out.println("5) Test reboot, then get data-----------");
        		System.out.println("6) Show all database info---------------");
        		System.out.println("7) Show Me the Main Menu !!-------------");
        		System.out.println("8) Put Data with your key---------------");
        		System.out.println("9) Get Data with your key---------------");
        		System.out.println("10) Remove Data with your key-----------");
        		System.out.println("11) The fragment Test here--------------");
        		System.out.println("12) Quit Value Store--------------------");
        	}
        	flag = false;
        	System.out.print("==>");
        	try{
        		option = scan.nextInt();
        	}catch(InputMismatchException e){
        		option = 0;
        		System.out.println("This is not an integer:" + e);
        		break;
        	}
        	
        	if(option==12){
        		System.out.println("Thank you for using value store! Bye");
        		running = false;
        		break;
        	}else if(option==7){
        		option=0;
        	}else if(option==1){
        		System.out.println("You are putting data");
        		byte[] vl = new byte[MAX_VALUE_SIZE]; 
        		vl = "That's put something into it, sounds fun right, awesome!!".getBytes();
        		flag = store.put(vKey, vl);
        	}else if(option==2){
        		System.out.println("You are getting data, Enter your key:");
        		byte[] temp = new byte [MAX_VALUE_SIZE];
        		temp = store.get(vKey);
        		if(temp.length == 0){
        			flag = false;
        		} else {
	    			String text = "";
	    			try {
	    				text = new String(temp, "UTF-8");
	    			} catch (UnsupportedEncodingException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			System.out.println("The value is: " + text);
	    			flag = true;
        		}
        	}else if(option==3){
        		System.out.println("Testing get and put");
        		flag = getAndput(vKey);
        	}else if(option==4){
        		System.out.println("Testing remove and get");
        		flag = removeAndget(vKey);
        	}else if(option==5){
        		System.out.println("Testing reboot and get");
        		flag = rebootAndget(vKey);
        	}else if(option==6){
        		System.out.println("Show all data");
        		System.out.println("File name ==>");
        		String name = scan1.nextLine();
        		flag = showAll(name);
        	}else if(option==8){
        		System.out.println("Please Enter your key to put data: ");
        		int k = -1;
        		try{
            		k = scan.nextInt();
            	}catch(InputMismatchException e){
            		option = 0;
            		System.out.println("This is not an integer: " + e);
            		break;
            	}
        		System.out.println("DataString: ");
        		String vals = scan1.nextLine();
        		byte[] val = new byte[MAX_VALUE_SIZE]; //The maximum size is 1MB dataStream.
        		val = vals.getBytes();
        		if(val.length==0){
        			System.out.println("Empty input");
        			flag = false;
        		}else{
        			flag = store.put(k, val);
        		}
        	}else if(option==9){
        		System.out.println("Enter your key to get data: ");
        		int k = -1;
        		try{
            		k = scan.nextInt();
            	}catch(InputMismatchException e){
            		option = 0;
            		System.out.println("This is not an integer: " + e);
            		break;
            	}
        		byte[] result = new byte[MAX_VALUE_SIZE];
        		result = store.get(k);
        		if(result.length == 0){
        			flag = false;
        		} else {
	        		String text = "";
	        		try {
	        			text = new String(result, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		System.out.println("The value is: " + text);
	        		flag = true;
        		}
        	}else if(option==10){
        		System.out.println("Enter the key to remove data: ");
        		int k = -1;
        		try{
            		k = scan.nextInt();
            	}catch(InputMismatchException e){
            		option = 0;
            		System.out.println("This is not an integer: " + e);
            		break;
            	}
        		flag = store.remove(k);
        	}else if(option==11){
        		System.out.println("Here is the fragment test: ");
        		flag = fragmentPut();
        	}else{
        		System.out.println("The number is not correct, try again!");
        	}
        	
        	if(flag){
                System.out.println("Job Done!");
            }else{
                System.out.println("Job Failed!");
            }
        }
        scan.close();
        scan1.close();
    }
    
    //here is the test get() and put() at the same time
    public static boolean getAndput(int key) {
  
    	Thread t1 = new Thread(
    			new Runnable(){
    				public void run(){
    				store.put(key,"getAndput".getBytes());
    				}  }, "t1");

    	Thread t2 = new Thread(
    			new Runnable(){
    				public void run(){
	    				try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    				try {
							String text = new String(store.get(key), "UTF-8");
							System.out.println("The value is: " + text);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				}        
    			}, "t2"
    	);
    	t1.start();
    	t2.start();
    	return true;
 
    }
    
    //here is the test remove() and get() at the same time
    public static boolean removeAndget(int key){
    	Thread t1 = new Thread(
    			new Runnable(){
    				public void run(){
    				store.remove(key);
    				}  }, "t1");

    	Thread t2 = new Thread(
    			new Runnable(){
    				public void run(){
	    				try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    				store.get(key);
    				}        
    			}, "t2"
    	);
    	t1.start();
    	t2.start();
        return true;
    }
    
    //here is the test of reboot put() and then run get()
    public static boolean rebootAndget(int key){
    	byte [] test = new byte [MAX_VALUE_SIZE];
    	store.clear();
    	System.out.println("Value Store re-Start");
    	store = new Vstore();
    	test = store.get(key);
    	String text = "";
		try {
			text = new String(test, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("The key: " + key + " and the value: " + text);
        return true;
    }
    
    //here is the test of Fragmentation put() A B C D .....
    public static boolean fragmentPut(){
    	byte [] a = new byte[MAX_VALUE_SIZE];
    	byte [] b = new byte[MAX_VALUE_SIZE];
    	byte [] c = new byte[MAX_VALUE_SIZE];
    	byte [] d = new byte[MAX_VALUE_SIZE];
    	byte [] e = new byte[MAX_VALUE_SIZE/2];
    	byte [] f = new byte[MAX_VALUE_SIZE];
    	byte [] g = new byte[MAX_VALUE_SIZE];
    	byte [] h = new byte[MAX_VALUE_SIZE];
    	
    	a = generation(MAX_VALUE_SIZE);
    	b = generation(MAX_VALUE_SIZE);
    	c = generation(MAX_VALUE_SIZE);
    	d = generation(MAX_VALUE_SIZE);
    	e = generation(MAX_VALUE_SIZE/2);
    	f = generation(MAX_VALUE_SIZE);
    	g = generation(MAX_VALUE_SIZE);
    	h = generation(MAX_VALUE_SIZE);
    	
    	boolean a1 = store.put(333, a); //put a
    	boolean b1 = store.put(444, b); //put b
    	boolean c1 = store.put(555, c); //put c
    	boolean d1 = store.put(666, d); //put d
    	boolean b11 = store.remove(444); //remove b
    	boolean e1 = store.put(444, e); //put e = 0.5MB
    	boolean f1 = store.put(777, f); //put f = 1.0MB fail
    	boolean c11 = store.remove(555); //remove c
    	boolean g1 = store.put(555, g); //put g = 1.0MB succeed
    	boolean e11 = store.remove(444); //remove e
    	boolean h1 = store.put(444, h); //put h = 1.0MB succeeds
    	clearAll();
    	return a1 && b1 &&c1 &&d1 &&b11&& e1&& (!f1) && c11&& g1&& e11&& h1;
    }
    
    // This is a function to create the size of byte for testing.
    public static byte[] generation(int size){
    	byte [] value = new byte[size];
    	new Random().nextBytes(value); //create some random bytes
    	//this is to print out the test, no need for it.
//    	String text = "";
//		try {
//			text = new String(value, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	System.out.println("Test: "+ text);
    	
    	return value;
    }
    
    // this is the function to show all the data in cs542.db
    public static boolean showAll(String filename){
    	return store.listTable(filename);
    }
    
    // for the fragment test only
    public static void clearAll(){
    	store.remove(333);
    	store.remove(444);
    	store.remove(555);
    	store.remove(666);
    }
}