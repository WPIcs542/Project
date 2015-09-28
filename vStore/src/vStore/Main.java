// This is the Main Shell
// Sep.26.2015
// Fangyu Lin, Hongzhang Cheng, Zhaojun Yang

package vStore;

import java.util.Scanner;

public class Main{
    static Vstore store = new Vstore();
    static final int vKey = 428657931;
    
    public static void main(String [] args){
        boolean flag = false;
        boolean running = true;
        Scanner scan = new Scanner(System.in);
        int option = 0;
        
        while(running){
        	if(option==0){
        		System.out.println("------Welcome to the Value Store !------");
        		System.out.println("---------Here is the Main Menu----------");
        		System.out.println("--------Select the number options below:");
        		System.out.println("1) Put data-----------------------------");
        		System.out.println("2) Get Data by Key----------------------");
        		System.out.println("3) Testing get and put at the same time-");
        		System.out.println("4) Testing remove and get at the same time");
        		System.out.println("5) Test reboot, then get data-----------");
        		System.out.println("6) Show database info-------------------");
        		System.out.println("7) Show Me the Main Menu !!-------------");
        		System.out.println("8) Quit Value Store---------------------");
        	}
        	System.out.print("==>");
        	option = scan.nextInt();
        	if(option==8){
        		System.out.println("Thank you for using value store! Bye");
        		running = false;
        		break;
        	}else if(option==7){
        		option=0;
        	}else if(option==1){
        		System.out.println("You are puting data");
        		byte[] vl = new byte[1000]; 
        		vl = "That's put something into it".getBytes();
        		flag = store.put(vKey, vl);
        	}else if(option==2){
        		System.out.println("You are getting data, Enter your key:");
        		byte[] temp = new byte [1024];
        		temp = store.get(vKey);
        		if(temp.length == 0){
        			System.out.println("Not found");
        		}else{
        			System.out.println("Reach Correct Data");
        		}
        	}else if(option==3){
        		System.out.println("Testing get and put");
        	}else if(option==4){
        		System.out.println("Testing remove and get");
        	}else if(option==5){
        		System.out.println("Testing reboot and get");
        	}else if(option==6){
        		System.out.println("Show all data, done!");
        	}else{
        		System.out.println("The number is not correct, try again!");
        	}
        	
        	if(flag){
                System.out.println("Job Done!");
            }else{
                System.out.println("Job Failed, try again.");
            }
        }
        scan.close();
    }
    
    //here is the test get() and put() at the same time
    public static boolean getAndput(int key){
        return true;
    }
    
    //here is the test remove() and get() at the same time
    public static boolean removeAndget(int key){
        return true;
    }
    
    //here is the test of reboot put() and then run get()
    public static boolean rebootAndget(int key){
        return true;
    }
    
    //here is the test of Fragmentation put() A B C D .....
    public static boolean fragmentPut(){
        return true;
    }
    
}