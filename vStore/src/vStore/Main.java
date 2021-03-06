/**
 *  This is the Main Shell
 *  Sep.26.2015
 *  Fangyu Lin, Hongzhang Cheng, Zhaojun Yang
 */

package vStore;

import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main{
    static Vstore store = new Vstore();  	//Initialize value store object
    static final int vKey = 428657931;		//this key is used for testing only
    private static final int MAX_VALUE_SIZE = 1024 * 1024;  //set maximum size 1MB 
    
    
    /**
     * KeyNote: This is the value store main shell. The idea is to use print out line to 
     * show the Menu information. And using scanner read in the keyboard character that 
     * the user choose. After that, the shell will go into the lower level shell for 
     * other option choice. The option 1) to 5) are used for testing small byte[] array. 
     * And option 11) is used for testing fragmentation management in database. We decide
     * menu as human kind to bring option 7) to show the Menu again, and option 12) to 
     * quit the main shell safely. 
     * @param args
     */
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
        		System.out.println("Select the number options below:--------");
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
        		System.out.println("You are getting data");
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
        		byte[] val = new byte[MAX_VALUE_SIZE]; 
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
        	
        	/**
        	 *  this section is to tell me if the test is succeed or not.
        	 *  It will check the boolean value that return from the previous test.
        	 */
        	if(flag){
                System.out.println("Job Done!");
            }else{
                System.out.println("Job Failed!");
            }
        }
        scan.close();
        scan1.close();
    }
    
    /**
     * here is the test get() and put() at the same time
     * The idea is to use thread method to run the get() and the put() method 
     * at the same time with the same key.
     * @param key
     * @return boolean
     */
    public static boolean getAndput(int key) {
    	//create a thread to get string with the key
    	Thread t1 = new Thread(
    			new Runnable(){
    				public void run(){
	    				try {
							String text = new String(store.get(key), "UTF-8");
							System.out.println("The value is: " + text);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    				}        
    			}, "t1"
    	);
    	
    	//create a thread to put and replace string with the same key
    	Thread t2 = new Thread(
    			new Runnable(){
    				public void run(){
        				try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        				store.put(key,"value changed!".getBytes());
    				}  }, "t2"
    	); 	
    	t1.start();
    	t2.start();
    	return true;
 
    }
    
    /**
     * here is the test remove() and get() at the same time
     * it is similar as the previous function. We are using the same key
     * to do remove and get actions at the same time. The result shows 
     * that the remove will be executed before the get action. 
     * @param key
     * @return boolean
     */
    public static boolean removeAndget(int key){
    	//create a thread to remove the value with the key
    	Thread t1 = new Thread(
    			new Runnable(){
    				public void run(){
    				store.remove(key);
    				}  }, "t1");

    	//create a thread to get the value with the same key
    	Thread t2 = new Thread(
    			new Runnable(){
    				public void run(){
	    				try {
							Thread.sleep(50);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    				String text = "";
						try {
							text = new String(store.get(key), "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("The value is: " + text);
    				}        
    			}, "t2"
    	);
    	t1.start();
    	t2.start();
        return true;
    }
    
    /**
     * here is the test of reboot put() and then run get()
     * The idea is to remove all information in the memory. 
     * Then, re-start the value store, which means to initialize 
     * the value-store again. It will read all information from 542.db 
     * file from the disk and put them into memory again. In this case,
     * we will know if the database is in the disk or not. The result shows
     * that all the information are in the disk before the restart. 
     * @param key
     * @return boolean
     */
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
    
    /**
     * here is the test of Fragmentation put() A B C D .....
     * It is tricky that we should generate the different byte arrays to avoid 
     * the space sharing. If the space is shared with other values, the total 
     * size will not increase so we can still put into key and valve. The order of 
     * testing is to put "a b c d" 4MB total into database. And then, remove 1.0 MB 
     * and put 0.5 MB which will succeed. Next putting 1.0 MB again which will fail 
     * because there is not enough space. Next, remove 1.0 MB and put into 1.0 MB succeed.
     * Next, remove 0.5 MB and put into 1 MB succeed, because there will be 1.0 MB 
     * space available in memory space. 
     * @return boolean
     */
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
    	
    	// The boolean value is used to catch the running succeed or fail
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
    
    /**
     * This is a function to create the size of byte for testing.
     * It is useful the use random() function from the library 
     * to avoid the same result of sharing memory space as value.
     * This function is used for testingfragmentation sonly. 
     * @param size
     * @return boolean 
     */
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
    
    /**
     * This is the function to show all the data in cs542.db
     * The filename is passed from the main shell. 
     * However, the default filename is cs542.db 
     * You can change the filename above. 
     * @param filename
     * @return boolean
     */
    public static boolean showAll(String filename){
    	return store.listTable(filename);
    }
    
    /**
     * For the fragmentation test only to clear memory
     */
    public static void clearAll(){
    	store.remove(333);
    	store.remove(444);
    	store.remove(555);
    	store.remove(666);
    }
}