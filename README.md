How to run:  
  
The Prerequisite is:  
You have already installed java. The minimum version requirement is Java 8.  
You have already installed Maven.  
You have already installed Git.  
Your 8080 port is available.    
Method 1:  
```
git clone https://github.com/lst0451/novel.git
cd novel
mvn spring-boot:run
```

Method 2:

```
mvn package
```

You can find the novel.jar under target directory.  
run:
```
java -jar novel.jar
```

Open your browser access: http://localhost:8080/  
You can see a very simple and ugly page.  
Click the link "Import files" to import the sample file.    
The sample file is located at /novel/src/main/resources.  
After you import the sample data, you can use "Novel List","Author List","Publisher List" to access the data.    
In addition, you can use Swagger UI to use the system:  
http://localhost:8080/swagger-ui.html#/  
If you want to check the data in database, you can access:  
http://localhost:8080/h2-console/  
```
JDBC URL: jdbc:h2:mem:novel  
User name: sa  
Password:sa
```

To use the RESTFul API, please import the file "NovelManagement.postman_collection.json" to you Postman.  
You can use postman to perform CRUD operations on your data. 

About the format of the sample data file "novel.txt":
Each line of data is divided into two parts by a colon. There can be no more than two colons in a row of data.  
Each set of data is divided by at least one blank line.  
"Name","Author","Publisher" are mandatory fields in each set of data.  
Leave at least two blank lines after the last set of data.


The program uses the in-memory database H2 by default.If you want to switch to MySQL,
modify the "application.properties" file, 
make "spring.profiles.active=mysql",
and then modify "application-mysql.properties", set the data source to suit your situation.  
