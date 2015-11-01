CS542 Project2
Team Members: Fangyu Lin, Hongzhang Cheng, Zhaojun Yang
Oct/16/2015

Key Notes:

This file is an introduction of HashIndex and how it works. 

In this project, we use static hashing as our index structure. The hash function used is String.hashcode mod size of buckets.
Size of buckets is set to 10 by us. Each bucket contains two blocks. If both of the blocks are full, a link with extra bucket is added after the current bucket.
With the help of index, this program can get and remove the keylist of the datavalue(index) we put in efficiently.

Testing method and how to use:
Once the program starts to run, the following is printed first:
Database file exist !
Test line of Hash Index!!
Then our program reads data from the already existing file cs542.txt automatically and puts them in the Hashtable,
you will see "Put key into index succeeds" when the hash function successfully put a key in the Hashtable.
After reading and putting all the data from the ".txt" file, the program begins to test the get method of the Hashtable.
With different types of input, the test result is different. When only String of Year is used as argument of get method, 
our program will show the movies of that year regardless of the type of that movie; when "year|format" is taken as argument, 
our program will show the movies fit for that condition. If datavalues without matched key in our hashtable are used, 
our program will show "Key not Found".
If all work are finished, our program will show "Job Done!"

Since there is a remove method for HashIndex to remove the key and datavalue, we have tested it in this project.
If you want to test the remove, please remove the comment in the main shell. 
Method called saveContents() is also provided to save the data into a file.
