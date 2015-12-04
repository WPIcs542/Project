CS542 Project_4
Team: Fangyu Lin; Hongzhang Cheng; Zhaojun Yang
Date November/30/2015

Keynotes:

This file is an introduction of RedoLog and how it works. 

Relation store:
As project 3, the relation store mechanism of project 1 is used in this project to store relation. We have initiated four relations and saved them into four .db files: city.db, city_backup.db, country.db, country_backup.db. 
Note that since the information in these .db files are changed each time we execute the program (populations are increased by 2%), we also created city_original.db and country_original.db to store the original data of world.sql.

Log manage and store:
To create and manage Log files, we created a class called RedoLog, which can write information to Log and save Log to a .log file with given filename. 

Operation:
In UpdateOp class, open() method opens a relation. getNext() method first writes:
<INIT>
<START>
Into log. Then it starts to increase the populations in relation by 2%. Every time a tuple is modified, a line:
<UPDATE>, hashcode of tuple, city/country, old population, new population
is written into log.
After all data have been modified. 
<COMMIT> is added to log.
Then, program starts to use log file to modify backup file, which is accomplished by class UpdateDB. updateBackup(Relation backUp) method gets each tuple of the backup relation and uses the new population in log file to replace the old population of the tuple.

Test procedure:
In main() method of Main class, after all of the above executions are finished, we use two for-loops to compare information in city.db-city_backup.db pair and country.db-country_backup.db pair. If the data aren’t identical. 
"Error: Relations do not match" 
Is printed.

Test result:
Database file exists !
Database file exists !
Database file exists !
Database file exists !
Here is the test line!
update city population Time: 106049 ms.
update city population backpup file Time: 1137288 ms.
update country population Time: 487 ms.
update country population backpup file Time: 60568 ms.
compare country and country_backup files Time: 1 ms.
compare city and city_backup files Time: 1 ms.
Total Time: 1304393 ms.
Finish

Notice that "Error: Relations do not match" is not printed, as a result we can conclude our program generates correct results.
