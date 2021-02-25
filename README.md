# ordering-products-system
<h3>RESTful App that allows you to manage products and orders</h3>

Project built using Java 15 and the following tools:<br>
<ul>
<li>Maven tool for automatic building </li>
<li>Spring Boot as server side framework </li>
<li>Hibernate as ORM / JPA implementation </li>
<li>MySQL as database implementation </li>
<li>Spring Data JPA as the top layer over Hibernate </li>
<li>Postman for testing </li>
</ul>

# Application Structure

### Model
Model is organized in the model package.
Model consists of entity classes. Entities use various annotations that describe the relationships between each other. All these annotations are used by JPA in order to map entities to database tables.

### Repository
Repositories are organized in the repository package. 
They are interfaces that are responsible for data persistence. The repository layer is an abstraction that provides all CRUD functionality and keeps hidden the data related information (e.g. specific database implmentation) from the other layers.


### Service
Service is organized in the service package. 
Service layer depends on the repository layer and provides separation of concern, encapsulating all the business logic implementation. It is there to apply business rules on data sent to and from the repository layer. Service layer does not care about the specific database implementation. This technique makes the application flexible in a possible data source replacement.

### Controller
Controllers are organized in the controller package.
Their layer depends on the repository layer and is responsible for the incoming requests and the outgoing responses. A controller determines available endpoints that client side is able to call. This layer should not apply logic on the receiving or returning data.

# Quick Start

## INTRODUCTION

#### 1. Create a MySQL database
```CREATE DATABASE ordertheproduct``` <br>
You may also import to MySQL provided file ```ordertheproduct.sql``` <br> <br>
In case you want to use a different database name, follow the next steps: <br>
                
```CREATE DATABASE DB_NAME;``` <br>
Open ```src/main/resources/application.properties``` file <br>
Change ```db.name``` property to match your preferred database name DB_NAME <br>


#### 2. Modify MySQL username and password 

Open ```src/main/resources/application.properties``` file <br>
Change ```spring.datasource.username``` and ```spring.datasource.password``` properties to match your MySQL connection <br>

## BUILD & RUN THE PROJECT
To launch the application, either right-click the public static void main in ```OrderProductApplication``` and select ```Run``` from your IDE, or: <br>

If you use Maven, you can run the application by using:
```
./mvnw clean spring-boot:run
```

Alternatively, you can build the JAR file with ````./mvnw clean package```` and then run the JAR file, as follows:
```
java -jar target/order-product-0.0.1-SNAPSHOT.jar
```

The server will start running at http://localhost:8080.

## AFTER RUNNING
For your convenience, I suggest using Postman app to check provided endpoints.
The following REST endpoints are available upon deployment:
| HTTP Verb        | URL           | Description  | Status Codes |
| ------------- |-------------|:-----| ----|
| `GET` | `http://localhost:8080/products?orderBy={id/name/price; default=name}` | Obtains a list of all existing products | <ul><li>`200 OK`</li></ul> |
| `GET` | `http://localhost:8080/products/{productId}` | Obtains the single product corresponding to the provided product id | <ul><li>`200 OK` if product exists</li><li>`404 Not Found` if product does not exist</li></ul> |
| `GET` | `http://localhost:8080/orders` | Obtains a list of all existing orders | <ul><li>`200 OK`</li></ul> |
| `GET` | `http://localhost:8080/orders/{from}/{to}` | Obtains finalized orders corresponding to the provided dates of order (date format: yyyy-mm-dd) | <ul><li>`200 OK`</li></ul> |
| `POST` | `http://localhost:8080/products` | Creates a new product based on the data contained in the request body - JSON { "name": "provided_name", "price": provided_price }| <ul><li>`201 Created` if product successfully created</li></ul> |
| `POST` | `http://localhost:8080/orders` | Creates a new order without provided request body | <ul><li>`201 Created` if order successfully created</li></ul> |
| `POST` | `http://localhost:8080/orders/{orderId}/products?productId={productId}&productId={productId}&...` | Adds a new products to chosen order based on the provided orderId and productIds | <ul><li>`200 OK`</li></ul> |
| `PUT` | `http://localhost:8080/orders/{orderId}` | Updates an existing order as finalized based on provided orderId | <ul><li>`202 Accepted` if order succesfully updated</li><li>`404 Not Found` if order does not exist</li></ul> |
| `PUT` | `http://localhost:8080/products/{productId}` | Updates an existing product with the data contained in the request body - JSON { "name": "provided_name",    "price": provided_price }| <ul><li>`202 Accepted` if product succesfully updated</li><li>`404 Not Found` if order does not exist</li></ul> |
| `PUT` | `http://localhost:8080/orders/{orderId}/products` | Updates an existing order (only new, not finalized orders) with updated products prices based on provided orderId | <ul><li>`200 OK` if order succesfully updated</li><li>`500` And exception is thrown if order does not exist</li></ul> |
