# project1 (see in the vStore folder)

A values store (a simplified version of a key-value store). The value store has just three interfaces: void Put(int key, byte[] data); stores data under the given key, byte[] Get(int key); retrieves the data and void Remove(int key); deletes the key. The value byte array can be arbitrarily large, up to 1 MB.
Your solution should be based on a single 5 MB file (4 MB for actual data, 1 MB for any metadata that you may need to store — you'll only need a fraction of the ! MB). Name the file cs542.db. Your solution should be implemented as a library that can be linked into multiple processes. Show the results of running the following validations:

Concurrency Validation. What happens when one caller in one thread is doing a Get() while another caller in another thread does a Put() and replaces the data. Or when one caller does a Remove() and another caller does a Get() with the same key a millisecond later.

Durability Validation. What happens if, after a reboot of the machine after the data has been Put(), a caller does a Get()?

Fragmentation. Put() 4 values, byte arrays of 1 MB each, with keys A, B, C and D. Remove key B. Put() ½ MB in size for key E. Validate that a Put() 1 MB in size for key F fails. Remove C and now validate that a Put() 1 MB in size for key G succeeds. Remove E and try Put() 1 MB in size for key H. With a naive implementation, it will fail even though there is room in store.db. An extra bonus point if you can modify your code such that Put("H", …) succeeds.

Create a shell program show that will show the layout of data in cs542.db. Run the program to do the validations listed above and include a record of your interactions to prove that the validations produced the expected results.

# Project2 (see in the Index_Mech folder)

An indexing mechanism. Our index has just three interfaces:
void Put(string key, Number data_value); or void Put(string key, string data_value); adds the index entry.
string Get(Number data_value); or string Get(string data_value); retrieves the key given the index and
void Remove(string key); deletes the index.

Technically it could be counted as five interfaces because of Number vs. string. The Number inputs only apply if you are working with B+ Trees. You have a choice of index type: B+ tree or hash. Additional assumptions will be required to complete this assignment and limit the scope of the exercise. That is OK, but you must enumerate those assumptions.

#Project3 (see in the Query_Ex folder)

A query execution engine. You may have realized that the functions of exercise 1 are actually methods of a Relation class. In a real database, we will have multiple instances of Relation, each representing one table. Modify your answer from exercise 1 to create the class and also add open(), getNext() and close() methods to it. Use the class to create and populate city and country tables. The data may be obtained from the MySQL World Database sample. Use the class to find all cities whose population is more than 40% of the population of their entire country.

Your answer should specify the definition of the Relation class (in the programming language of your choice). 

Each operator in your query execution plan must be implemented as a class with open(),  getNext() and close() methods. It will also have members that specify inputs — whether tables or previous operators. In Design Patterns parlance, pipelines of elements that send data as soon as it's ready is referred to as the publish/subscribe pattern. in case you want to read up on it. 

Show the code that connects the various elements of the pipeline. Also show the output of the last operator. Is the result what you were expecting? 

#Project4 (see in the RedoLog folder)

Undo/Redo Logging. Over time, the populations of cities and countries changes. We will change the population of each by 2% to represent the passage of a year. The purpose of this programming assignment is to programmatically make this change in all records and to generate undo/redo logs as we are doing it. Then we will move those logs to another machine that has a copy of the same data and apply the logs and observe that the data has changed.

In the style of project 3, create an operator to update the population values in a table. You may choose to write one operator that applies to both the city and the country tables, or two separate ones. As a side effect, the operator should produce a log file. 

To test, Make a backup copy of cs542.db, say cs542A.db,
Run the update that increases the populations by 2%, <
Apply the log file to cs542A.db.
Run spot-checks to verify that the cs542.db and cs542A.db contain identical data and that both show populations that are 2% higher than the original.
