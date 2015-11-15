CS542 Project_3
Team: Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
Date November/12/2015



 

Key Notes:

This file is an introduction of Query-execution and how it works. 

Relation store:
In this project, we use hashtable to store our relation. Program of project one is used to realize the Relation class. 
The city table and country table in world.sql file are saved into two txt files, done by init() method. By executing open() method in ExeJoin, contents in these two files are read into memory and form two relation: city and country.
In these relations, each tuple's hashcode is the key and tuple string itself is the value. Program splits tuple into different attributes on character "," by method splitoftuple().

Operation:
Our project contains three operator: 
JOIN city and country on countrycode
SELECT tuple where city.pop>0.4*country.pop
PROJECT city.name from tuple 
Therefore, three class, ExeJoin, ExeSelect, ExeProject are realized. 
Since PIPELINE is used in this project (we use a method called pipelineExe() in ExeJoin and ExeSelect to represent this), which means tuples are sent to the next operator as soon as they are ready, SELECT and PROJECT operator do not need to read the data from disk into memory. As a result, open() close() methods are only implemented in ExeJoin.
At runtime, we use a break in join operation. In getNext() method in ExeJoin, when a city finds a country which matches its country code, it directly breaks the loop and skips the comparison with the rest of country. This method can reduce the runtime. 

Testing method and how to use:
In main() method, open(), getNext() and close() methods in ExeJoin are executed one by one. 
Database file exists !
Database file exists !
Database file exists !
Database file exists !
Database file exists !
Database file exists !
The result is stored in a file called projectResult.db. We read the file and print the result:
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
By comparing with the result got from executing the following on world.sql in mysql
select country.Name

from world.city,  world.country
 where city.CountryCode = country.Code and
	
       city.population > 0.4*country.population
we conclude that our result is correct.