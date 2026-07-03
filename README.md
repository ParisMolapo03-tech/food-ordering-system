====================================================
PART 4: PROJECT INVESTIGATION
1. What is Spring Boot?
    * Spring Boot is a framework built on top of Java that makes it easier and faster to
      build web applications. It removes the need for complex configuration by providing
      sensible defaults so developers can focus on writing business logic instead of setup.
   
2. What is Maven?
   * Maven is a build and dependency management tool for Java projects. It automates the 
     process of downloading libraries, compiling code, 
     and packaging the application into a runnable file.
   
3. What is the purpose of pom.xml?
   * The pom.xml file is the heart of a Maven project. It defines the project details, 
     the Java version being used, and all the dependencies the project needs such as Spring Web, 
     Spring Data JPA, and MySQL Driver.
   
4. What is the purpose of application.properties?
   * The application.properties file contains configuration settings for the Spring Boot application 
     such as the database URL, username, password, and server port.
   
5. What does @SpringBootApplication do?
   * It marks the main class of a Spring Boot application and combines auto-configuration, component scanning,
     and configuration into one annotation.
   
6. Why do developers use dependency management tools such as Maven?
   * Without Maven, developers would have to manually download every library and manage versions themselves. 
     Maven automates this by reading the pom.xml and downloading everything automatically.
   
7.  What is a REST API?
   * A REST API is a way for applications to communicate with each other over the internet using HTTP requests.

8. What is JSON?
   * JSON stands for JavaScript Object Notation. It is a lightweight format used to send and receive data between 
     a client and a server.
9. What is Dependency Injection?
   * Dependency Injection is a design pattern where a class receives the objects it needs from outside instead of 
     creating them itself. This makes the code flexible, easier to test, and easier to maintain.
====================================================
     PART 5: PACKAGE STRUCTURE
1. Controller: This package contains the REST controllers that handle incoming HTTP requests from the client. 
   It acts as the entry point of the application.

2. Service: This package contains the business logic of the application. It processes data and communicates 
   with the repository layer.

3. Repository: This package handles all database communication using Spring Data JPA.

4. Entity: This package contains Java classes that represent the database tables.

5. DTO: This package contains Data Transfer Objects that control what data is sent to and received from the client.

6. Config: This package contains configuration classes for the application.

7. Exception: This package handles errors and returns meaningful messages to the client.

     ====================================================

Category API completed and tested successfully.

## API Response Format

Every endpoint returns the same JSON structure:

​```json
{
    "statusCode": 200,
    "message": "Category retrieved",
    "data": {
        "id": 1,
        "name": "Fast Food",
        "description": "Quick and easy meals"
    },
    "timestamp": "2026-06-20T08:42:11"
}
​```

| Field | Type | Description |
|---|---|---|
| statusCode | int | HTTP status code e.g. 200, 201, 404 |
| message | String | Human readable result message |
| data | T | The actual response data |
| timestamp | LocalDateTime | When the response was generated |

====================================================
## MENU MODULE

### Menu Endpoints

| Method | Path | Description | Query Params |
|--------|------|-------------|--------------|
| POST | /api/menu | Create a new menu item | - |
| GET | /api/menu | Get all menus (filterable, paginated) | categoryId, search, page, size, sort |
| GET | /api/menu/{id} | Get a single menu item by ID | - |
| PUT | /api/menu/{id} | Update a menu item by ID | - |
| DELETE | /api/menu/{id} | Delete a menu item by ID | - |

### Category Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | /api/categories | Get all categories |
| GET | /api/categories/{id} | Get a single category by ID |
| POST | /api/categories | Create a new category |
| PUT | /api/categories/{id} | Update a category |
| DELETE | /api/categories/{id} | Delete a category (409 if menus exist) |

---

### Example GET /api/menu with all query params
---

### Example Page<MenuDto> Response

```json
{
    "statusCode": 200,
    "message": "Menus retrieved",
    "data": {
        "content": [
            {
                "id": 1,
                "name": "Cheese Burger",
                "description": "Beef patty with melted cheese",
                "price": 49.99,
                "imageUrl": "https://placehold.co/300",
                "categoryId": 1,
                "categoryName": "Fast Food"
            }
        ],
        "totalElements": 7,
        "totalPages": 2,
        "number": 0,
        "size": 5,
        "first": true,
        "last": false
    },
    "timestamp": "2026-07-02T13:44:57.736"
}
```
====================================================