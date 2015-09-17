# project1


A values store (a simplified version of a key-value store). The value store has just three interfaces: void Put(int key, byte[] data); stores data under the given key, byte[] Get(int key); retrieves the data and void Remove(int key); deletes the key. The value byte array can be arbitrarily large, up to 1 MB.
Your solution should be based on a single 5 MB file (4 MB for actual data, 1 MB for any metadata that you may need to store — you'll only need a fraction of the ! MB). Name the file cs542.db. Your solution should be implemented as a library that can be linked into multiple processes. Show the results of running the following validations:

Concurrency Validation. What happens when one caller in one thread is doing a Get() while another caller in another thread does a Put() and replaces the data. Or when one caller does a Remove() and another caller does a Get() with the same key a millisecond later.

Durability Validation. What happens if, after a reboot of the machine after the data has been Put(), a caller does a Get()?

Fragmentation. Put() 4 values, byte arrays of 1 MB each, with keys A, B, C and D. Remove key B. Put() ½ MB in size for key E. Validate that a Put() 1 MB in size for key F fails. Remove C and now validate that a Put() 1 MB in size for key G succeeds. Remove E and try Put() 1 MB in size for key H. With a naive implementation, it will fail even though there is room in store.db. An extra bonus point if you can modify your code such that Put("H", …) succeeds.

Create a shell program show that will show the layout of data in cs542.db. Run the program to do the validations listed above and include a record of your interactions to prove that the validations produced the expected results.
