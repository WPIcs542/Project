// This is the Main Shell
// Sep.26.2015
// Fangyu Lin, Hongzhang Cheng, Zhaojun Yang

package vStore;

public class Main{
    static Vstore store;
    static final int vKey = 428657931;
    
    public static void main(String [] args){
        boolean flag = false;
        flag = getAndput(vKey)&&
            removeAndget(vKey)&&
            rebootAndget(vKey)&&
            fragmentPut();
        if(flag){
            System.out.println("Job Done!");
        }else{
            System.out.println("Not working!");
        }
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