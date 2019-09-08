### How to run:  
 
The Prerequisite is:  
You have already installed java. The minimum version requirement is Java 8.  
You have already installed Maven.  
You have already installed Git.  
Your 8080 port is available ( otherwise you need to modify it in application file ).  

You can pick up one on of the two method to run:      
#####Method 1:  
```
git clone https://github.com/lst0451/novel.git
cd novel
mvn spring-boot:run
```

#####Method 2:

```
mvn package
```

You can find the novel.jar under target directory.  
run:
```
java -jar novel.jar
```

Open your browser access: http://localhost:8080/  
You should see a very simple and ugly page.  
Click the link "Import files" to import the sample file.    
#####The sample file is located at /novel/src/main/resources.  
After you import the sample data, you can use "Novel List","Author List","Publisher List" to access the data.    
#### Use Swagger to access system:
In addition,you can use Swagger UI to use the system:  
http://localhost:8080/swagger-ui.html#/  
If you want to check the data in database, you can access:  
http://localhost:8080/h2-console/  
```
JDBC URL: jdbc:h2:mem:novel  
User name: sa  
Password:sa
```
####Restful API:
To use the RESTFul API, please import the file "NovelManagement.postman_collection.json" to you Postman.  
You can use postman to perform CRUD operations on your data. 

#####About the format of the sample data file "novel.txt":
Each line of data is divided into two parts by a colon. There can be no more than two colons in a row of data.  
Each set of data is divided by at least one blank line.  
"Name","Author","Publisher" are mandatory fields in each set of data.  
Leave at least two blank lines after the last set of data.

#####About DB:
The program uses the in-memory database H2 by default.If you want to switch to MySQL,
modify the "application.properties" file, 
make "spring.profiles.active=mysql",
and then modify "application-mysql.properties", set the data source to suit your situation.  

###About the business logic:
####Create
You can create author, novel and publisher information separately.
or you can create complete information that includes these three content simultaneously.  
Examples:  
Use the AuthorController to create complete information.  
```aidl
curl -X POST "http://localhost:8080/api/author" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"authorName\": \"Peter\", \"introduce\": \"Big author Peter\", \"novelList\": [ { \"name\": \"Peter Novel\", \"publishDate\": \"1954-05-05\", \"price\": 128.00, \"pages\": 567, \"genre\": \"ppp\", \"isbn\": \"7863654\", \"language\": \"English\", \"weight\": 113, \"remark\": \"Best novel of Peter.\", \"productDimensions\": \"13.3 x 0.8 x 20.3 cm\", \"publisher\":{ \"name\":\"Peter PUB\", \"type\":\"ppp\" } } ] }"
```
Use the NovelController to create complete information.  
```
curl -X POST "http://localhost:8080/api/novel" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"name\": \"Alan's novel\", \"publishDate\": \"1954-05-05\", \"price\": 128.00, \"pages\": 999, \"genre\": \"aaa\", \"isbn\": \"666666666\", \"language\": \"English\", \"weight\": 113, \"remark\": \"Alan's best novel.\", \"productDimensions\": \"13.3 x 0.8 x 20.3 cm\", \"author\":{ \"authorName\": \"Alan\", \"introduce\": \"Good Alan.\" }, \"publisher\":{ \"country\": \"CA\", \"name\": \"Alan PUB\", \"remark\": \"Publisher for Alan\", \"type\": \"AAA\" } }"
```
Use the PublisherController to create complete information.
```
curl -X POST "http://localhost:8080/api/publisher" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"country\": \"CA\", \"name\": \"Mike PUB\", \"novelList\": [ { \"author\": { \"authorName\": \"Mike\", \"introduce\": \"Better Mike\" }, \"genre\": \"MMM\", \"isbn\": \"987654555\", \"language\": \"French\", \"name\": \"Mike's novel\", \"pages\": 399, \"price\": 566, \"productDimensions\": \"7*7*7\", \"publishDate\": \"2019-08-29\", \"remark\": \"Mike's best novel\", \"weight\": 666 } ], \"remark\": \"Publisher for Mike\", \"type\": \"MMM\"}"
```
All of the above three methods can be used to add complete information including author, Novel, and Publisher.

You can also add Author and Publisher information separately using AuthorController and PublisherController.
```
curl -X POST "http://localhost:8080/api/author" -H "accept: */*" -H "Content-Type: application/json" -d "{\"authorName\": \"Single Author\",\"introduce\": \"Single Author\"}"
```

```
curl -X POST "http://localhost:8080/api/publisher" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"country\": \"CA\", \"name\": \"No book pub\", \"remark\": \"A no book publisher.\", \"type\": \"NNN\"}"
```
  
If you add the novel information separately, you must specify the author information and Publisher. If the author and Publisher already exist, you only need to specify the AuthorID and PublisherID.
```
curl -X POST "http://localhost:8080/api/novel" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"name\": \"Alan's second novel\", \"publishDate\": \"1954-05-05\", \"price\": 128, \"pages\": 999, \"genre\": \"aaa\", \"isbn\": \"666666666\", \"language\": \"English\", \"weight\": 113, \"remark\": \"Alan's second novel.\", \"productDimensions\": \"13.3 x 0.8 x 20.3 cm\", \"author\": {\t\"id\":2 }, \"publisher\": { \"id\":2 }}"
```
####Query
```
http://localhost:8080/api/novels
http://localhost:8080/api/novel/{novelId}
```
```
http://localhost:8080/api/auhors
http://localhost:8080/api/auhor/{authorId}
```
```
http://localhost:8080/api/publishers
http://localhost:8080/api/publisher/{publisherId}
```
####Delete
If you delete an Author, all the novels under the author are also deleted.

```
curl -X DELETE "http://localhost:8080/api/author/1" -H "accept: */*"
```
If you delete a Novel, only the Novel data will be deleted, and the author and Publisher will not be affected.
```
curl -X DELETE "http://localhost:8080/api/novel/3" -H "accept: */*"
```
If you want to delete a Publisher with novel information, an exception will be thrown.
You must delete all the novels under the Publisher before you can delete the Publisher.  
The above logic is just a convention, and the specific situation can be changed according to the needs.