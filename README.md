# Students-Reporting-System Application

####  Students-Reporting-System Application is a software system designed to manage and report student information. The application is typically used by educational institutions such as schools, colleges, and universities to maintain records of students, their academic performance, attendance, and other related data.

## Tech Stack

* Spring Boot 
* JSP 
* Kebanin 
* Elasticsearch 

### Spring Boot 

Spring Boot is a popular Java-based framework that makes it easy to create standalone, production-grade applications. It provides a wide range of features such as auto-configuration, embedded servers, and easy dependency management. With Spring Boot, developers can focus on writing business logic rather than configuring the application.

### JSP
JavaServer Pages (JSP) is a technology used to create dynamic web pages that can generate HTML, XML, or other types of documents. It is a popular choice for creating server-side web applications as it allows developers to embed Java code directly into the HTML pages.

### Kebanin
Kebanin is an open-source data access framework for Java. It provides a simple and lightweight approach to interacting with databases by using annotations and reducing boilerplate code. With Kebanin, developers can easily map database tables to Java objects and perform CRUD operations without writing SQL queries.

### Elasticsearch
Elasticsearch is a powerful and scalable search engine based on the Lucene library. It is commonly used to search, analyze, and visualize large datasets in real-time. With Elasticsearch, developers can perform full-text searches, aggregations, and geospatial queries on structured or unstructured data.

### Explanation
This project is a web application built using Spring Boot and JSP as the front-end technology. Kebanin is used for data access and Elasticsearch is used for indexing and searching data. The application allows users to search for products and view detailed product information. The products are stored in a database and indexed in Elasticsearch for faster search results. Kebanin is used to interact with the database and Elasticsearch is used to perform search operations. JSP is used to render the HTML pages and display the search results to the user.

### Installation & Run configuration
```

spring.mvc.view.prefix = /views/
elasticsearch.host=localhost
elasticsearch.port=9200

elasticsearch.clustername=Sabira
spring.data.elasticsearch.cluster-nodes=localhost:9300
spring.data.elasticsearch.repositories.enabled=true
```
