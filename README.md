# QueueSimulator

For this project you will need to download several packages. This set up guide is for Eclipse.   

## Set up   
* Go to eclipse marketplace (help | eclipse marketplace) and download e(fx)clipse-plugin   
* Dowload JavaFX plugins: https://gluonhq.com/products/javafx/ (take SDK)  
    * Extract zip for example to same place where you have JDK  
    * Create a library for JavaFX  
    * Click user librariews => new => give a name example JavaFX, Add External JARS and add jars that you just dowloaded  
* Download and install MariaDB https://mariadb.org/  
* Create a new user with your root user another user, example   
	mariaDB> GRANT ALL PRIVILEGES ON *.* TO yourUserNameGoesHere@localhost  
    -> IDENTIFIED BY 'yourPasswordGoesHere' WITH GRANT OPTION;  
* Create Database called simulaattori (create database simulaattori; )  
* Download JDBC connector (MariaDB Connector/J 2.7.3 => select Universal operating system)   
* Add JDBC connector to classpath (Build path | Add Libraries | User Library)   
    * Click user librariews => new => give a name example MariaDBConnectorJ
    * Add External JARS and add jars that you just dowloaded
    * example path C:\Program Files/MariaDB 10.6/mariadb-java-client-2.7.3.jar
* Dowload Hibernate libraries https://hibernate.org/orm/, add jars similarly than other libraries (jars).  
* Lastly, modify your user and password that you create to hibernate.cfg.xml file  
* And if you wish to run tests, add JUnit 5 libraries too.  
## Running the project   
* You can run it from default package -> Main.java   
* When you run project first time, it will create tables needed.
* If you don’t wish drop and create your database again, change create to validate here:   
<property name="hbm2ddl.auto">create</property>
## Creators   
This was done as a collaborative project with h-la & IinaLaa, gitrepo: https://github.com/teamOliot/JonoSimulaattori   
