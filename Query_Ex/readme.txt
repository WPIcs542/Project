CS542 Project_3
Team: Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
Date November/12/2015



 

Key Notes:

This file is an introduction of Query-execution and how it works. 

In this project, we use hashtable to store our relation, we used the project we made before to implement the function of our hashtable,
after we get the relation we are going to do the operation on, we at first read them out,then split them ,doubleparse the attribute we want 
in it and compared tuple and select the attribute based on them. in the procedure, we used a break in join operation, when a city find a 
country which match its country code, it gona directly break the loop and skip the comparison with the rest of country. this method could
reduce the runtime. besides we used the pipeline fuction which could connected our two operation, so we dont need to save data in a new relation


Testing method and how to use:
Once the program starts to run, the following is printed first:
Database file exists !
Database file exists !
Database file exists !
Database file exists !
Database file exists !
Database file exists !
our program initialize the ExEnginejoin object, the using the function of the open, getnext and close of this object
then you will see the results and how many tuples had been joined show as below:
Value of Key:	2973	---The size of data:	6 Bytes	value: 'Doha'
Value of Key:	2316	---The size of data:	8 Bytes	value: 'Bantam'
Value of Key:	763	---The size of data:	9 Bytes	value: 'Stanley'
Value of Key:	2507	---The size of data:	20 Bytes	value: 'Dalap-Uliga-Darrit'
Value of Key:	3067	---The size of data:	14 Bytes	value: 'Saint-Pierre'
Value of Key:	938	---The size of data:	14 Bytes	value: 'Longyearbyen'
Value of Key:	553	---The size of data:	13 Bytes	value: 'George Town'
Value of Key:	585	---The size of data:	10 Bytes	value: 'Djibouti'
Value of Key:	3208	---The size of data:	11 Bytes	value: 'Singapore'
Value of Key:	583	---The size of data:	8 Bytes	value: 'Avarua'
Value of Key:	2454	---The size of data:	7 Bytes	value: 'Macao'
Value of Key:	3206	---The size of data:	10 Bytes	value: 'Victoria'
Value of Key:	2453	---The size of data:	11 Bytes	value: 'El-Aai�Bn'
Value of Key:	148	---The size of data:	8 Bytes	value: 'Nassau'
Value of Key:	915	---The size of data:	11 Bytes	value: 'Gibraltar'
Value of Key:	3538	---The size of data:	20 Bytes	value: 'Citt? del Vaticano'
Value of Key:	2881	---The size of data:	7 Bytes	value: 'Koror'
Value of Key:	2912	---The size of data:	11 Bytes	value: 'Adamstown'
Size: 18
our program at first read data from the relation tables and then transform and split them, after that, do the comparison
tuple by tuple based on the attribute condition.
