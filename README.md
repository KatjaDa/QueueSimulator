# QueueSimulator

For this project you will need to download several packages. This set up guide is for Eclipse.   

## Set up   
	1. Go to eclipse marketplace (help | eclipse marketplace) and download e(fx)clipse-plugin   
	2. Dowload JavaFX plugins: https://gluonhq.com/products/javafx/ (take SDK)  
		a. Uninstall zip for example to same place where you have JDK  
		b. Create a library for JavaFX  
		c. Click user librariews => new => give a name example JavaFX, Add External JARS and add jars that you just dowloaded  
	3. Download and install MariaDB https://mariadb.org/  
	4. Create a new user with your root user another user, example   
	mariaDB> GRANT ALL PRIVILEGES ON *.* TO yourUserNameGoesHere@localhost  
    -> IDENTIFIED BY 'yourPasswordGoesHere' WITH GRANT OPTION;  
	5. Create Database called simulaattori (create database simulaattori; )  
	6. Download JDBC connector (MariaDB Connector/J 2.7.3 => select Universal operating system)   
	7. Add JDBC connector to classpath (Build path | Add Libraries | User Library)   
		a. Click user librariews => new => give a name example MariaDBConnectorJ, Add External JARS   
		and add jars that you just dowloaded (path should propably by something like    
		C:\Program Files/MariaDB 10.6/mariadb-java-client-2.7.3.jar )  
	8. Dowload Hibernate libraries https://hibernate.org/orm/, add jars similarly than other libraries (jars).  
	9. Lastly, modify your user and password that you create to hibernate.cfg.xml file  
	10. And if you wish to run tests, add Junit 5 libraries too.   
## Running the project   
	1. You can run it from default package -> Main.java   
	2. When you run project first time, it will create tables needed.   If you don’t wish drop and create your database again,   
	Change create to validate here:   
	<property name="hbm2ddl.auto">create</property>
## Creators   
This was done as a collaborative project with h-la & IinaLaa, gitrepo: https://github.com/teamOliot/JonoSimulaattori   
