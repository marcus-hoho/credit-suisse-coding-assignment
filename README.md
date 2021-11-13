# credit-suisse-coding-assignment
How to Run the Program
1. download the project from github with 
2. build the project with maven : mvn clean install
3. Then collect all jar files you use as dependencies : mvn dependency:copy-dependencies
4. copy the logfile.txt to the target folder: cp ./logfile.txt ./target
5. change to target folder : cd target
6. You can then run the java program: java -cp "credit-suisse-coding-assignment-0.0.1-SNAPSHOT.jar:dependency/*" com.creditsuisse.coding.assignment.Application ./logfile.txt false
 
 
 *the 1st argument is the  logfile path 
 *the 2nd argument is boolean[true/false] which control the program to run the dummy test case or not
 
 *************************************************************************
 When the program start , it will create a HSQLDB with the schema.sql
 then it will generate 2 dummy sample records to the DB with the data.sql
 and this logfile.txt will create 3 records to the DB
 **************************************************************************
 
 ##  Next step  ##
 The program is running in the ServerLogThread which is design for running in multi-threading and handling large file,
 In order to do so, 
 1st : divide the gigabytes in to multiple files and assign to multiple ServerLogThread to process
 2nd : the DB schema should be add a lastupdate timestamp, every time update a record to the db, should check the lastupdate timestamp is equal to your retrieved one
 3rd : more Junit testcase
 
