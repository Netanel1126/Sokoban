# Sokoban
A Sokoban game that uses Hibernate to get data from SQL database, mvc design pattern, javaFx, facade design pattern In the future: the Sokoban game will use the searchLib + stripsLib to solve Sokoban problem

Sokoban by Idan & Netanel:
In order to run the program you will need to do several things:
1)create the following database in microsoft management studio
the database: 
create database SOKOBAN

create table ScoreBoard(
			ScoreId INT IDENTITY(1,1) not null,
			UserName NVARCHAR(200) not null,
			Time_ToComplete int,
			Steps int,
			Level_Name NVARCHAR(200)
			PRIMARY KEY (ScoreId)
			)
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
2)Put the following folder hibernate-release-5.2.9.Final in C:\Hibernate
you can downlode from http://hibernate.org/orm/downloads
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
3)Put the following folder sqljdbc_6.0 in C:\Hibernate\Microsoft JDBC Driver 6.0 for SQL Server
you can downlode from https://www.microsoft.com/en-us/download/details.aspx?id=11774
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
4)Add to the build path all everything in C:\Hibernate\hibernate-release-5.2.9.Final\hibernate-release-5.2.9.Final\lib\required 
+ C:\Hibernate\Microsoft JDBC Driver 6.0 for SQL Server\sqljdbc_6.0\enu\jre8
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
5)Go to Run->Run Configurations Choose the 'Arguments' tab for your class
Add the below code in VM arguments: -Djava.library.path="C:\Microsoft JDBC Driver 6.0 for SQL Serve\sqljdb_6.0\enu\auth\x64"

Now you can run the the program :)
