This file is the introduction of value store and how it works. 

Author: Fangyu Lin, Hongzhang Cheng, Zhaojun Yang

In this project, Hashtable is used to store value under given key.

Testing method & result:
Once the program starts to run, the following is printed first:
Database file exist !
------Welcome to the Value Store !------
---------Here is the Main Menu----------
--------Select the number options below:
1) Put String data----------------------
2) Get String Data by Key---------------
3) Testing get and put at the same time-
4) Testing remove and get at the same time
5) Test reboot, then get data----------- 
6) Show all database info---------------
7) Show Me the Main Menu !!-------------
8) Put Data with your key---------------
9) Get Data with your key---------------
10) Remove Data with your key-----------
11) The fragment Test here--------------
12) Quit Value Store--------------------
User can input number to choose function. If number is beyond these functions, "The number is not correct, try again!" is printed.
When user presses "1" while programming running, "You are putting data" is printed and a string "That's put something into it, sounds fun right, awesome!!" is stored into the value store under vkey( a preset key).
When user presses "2", "You are getting data" is printed. The value stored under vkey is retrieved and printed.
When user presses "3", "Testing get and put" is printed. We suspend get() for 250 milliseconds to wait for put() to show what will happen if one thread is doing a Get() while another caller in another thread does a Put() and replaces the data.. If put() replace the value successfully, get() will return "value changed!", otherwise return "That's put something into it, sounds fun right, awesome!!".
When user presses "4", "Testing remove and get" is printed. One thread does a remove() and another caller does a get() with the same key a millisecond later. If remove() has finished before get() starts, get() will find nothing, otherwise get() will return the value.
When user presses "5", "Testing reboot and get" is printed. The program cleans the value store and restart the value store, then get() and prints the string under vkey.
When user presses "6", "Show all data" is printed. After "File name", user type in the file name"cs542.db", and the data in it will be printed.
When user presses "7", the main menu is printed.
When user presses "8", "Please Enter your key to put data: "is printed. User can input an integer and then after "DataString: " user type in the value that need to be stored. If the value is empty, "Empty input" is printed. If the key is not an integer, "This is not an integer: " will be printed.
When user presses "9", "Enter your key to get data: " is printed. User can input an integer key. If there is no value under the given key, flag is false, otherwise the value is printed.
When user presses "10", "Enter the key to remove data: " is printed. User input an integer key and the value under the key (if exists) will be removed.
When user presses "11", "Here is the fragment test: " is printed. The program put() 4 values, byte arrays of 1 MB each, with keys 333, 444, 555 and 666. Remove key 444. put() 0.5 MB in size for key 444. Validate that a put() 1 MB in size for key 777 fails. Remove 555 and now validate that a put() 1 MB in size for key 555 succeeds. Remove 444 and try Put() 1 MB in size for key 444. If all of the above get validated, flag is set to true.
Each of the above option has a flag, if flag is true "Job Done!" is printed, or "job Failed!" is printed. 


Code design explained:

In the class Vstore, we have six methods, they are put(), get(),remove(),clear(), saveContents(), listTable().

For put() method, it has two input variables, the first variable is the key used by HashTable to map and save the data, the second is used for passing the object string which will be saved. At first put() method checks the size of the data which will be put in, if it is larger than the maxValue limit which is 1M, the data will not be put in. if it is less than the maxvalue limit, then check whether the file has enough space to save the data, if space is enough, put in the data and save file. And this method contains synchronized statement, so when one thread running this method, other threads will stop and wait it finish.

For get() method, it has two variable keys, it will check whether database file contain the data mapped by the key in our hashtable, if succeed it will show the data .

For remove(),it has one variable key, it will remove the data mapped by key in our hashtable if it contains. Then save the data.

For clear(), the purpose of it is to build a new empty database with hashstable data structure.
For savecontente(), the purpose of it is to save the changes to the data to the file permanently.

For listtable() the purpose of it is to show all the information in database.

In class main we have eight methods, they are main(), getandput(),removeandget(),rebootandget(),fragmentput(),generation(),showall(),clear().

For main() ,the purpose of it is to give us a big frame of how this program works, which include the configure of the environment and statement of the variables and test our methods in class Vstore.

For getandput(), the purpose of this method is to test the put() and get() at the same time, it has one variable which is used for passing the original data key to the get() methods. When calling on this method, the program will at first do the put() then do the get(), so the data will be changed .we use two threads in this method to control which action we want to do first.

For removeandget(), its purpose is to test remove() and get() at the same time, it has one variable which is used for passing the original data key to the remove() methods. When calling on this method, the program will at first do the remove() then do the get(), so the data wont exist. We use two threads in this method to control which action we want to do first.


For rebootandget() ,its purpose is to test when database was rebooted, check whether the get() method could get what we stored in the database file last time.it has one variable which is used for passing the key of the last saved data in hashtable.

For fragmentput(),its purpose is to test the size control of the database input . when calling on this method, the program will put random size files within maxvalue limit of database file into our database file, then remove one of them, put another in it ,sees its work or not.

For generation(), Its purpose is to create the random size file for testing the fragmentput(), it has one variable which is used for giving the first number of the random function in the method.

For Showall(), Its the function to show all the data in cs542.db,it has one variable filename which is passed from the main shell. 

For clearall(), its purpose is remove all the data in the database file.




