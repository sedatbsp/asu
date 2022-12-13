# ASU (Hexagonal Architecture)

```diff
@@ ASU means 'Auction Shortened URL Service'.@@
```

### Prerequisites
- [Java](https://dev.java/learn/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)
- [MySQL](https://dev.mysql.com/doc/)
- [Lombok](https://projectlombok.org/setup/maven)

### Tools
- IntelliJ IDEA or any preferred IDE
- Postman (or any RESTful API testing tool)

### Run the Application

Clone the source codes into your local system.

```
git clone https://github.com/sedatbsp/asu
```

Then, to run the application, run the following command in a terminal window (in the ``` complete ```) directory:

```
mvn spring-boot:run
```

>  it will run application as spring boot application

or
> run main method from `UrlApplication.java` as spring boot application.

# API Endpoints

You can view all endpoints of **ASU** by reading the README or test it using the Postman RESTful API testing tool.

## Login
````
POST /api/login HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 57

{
    "username":"sedatbsp",
    "password":"123456"
}
````
#### Response 
```properties
{
    "data": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZWRhdGJzcCIsInVzZXJJZCI6MSwiaWF0IjoxNjcwOTYwMDA4LCJleHAiOjE2NzEwNDY0MDh9.tslLTd8vRD-R1uaFwzLjOwoe1beamwQclxl223RK_eg",
    "errors": null,
    "time": "2022-12-13"
}
```

## Create > User
````
POST /api/user HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 144

{
    "firstName":"Sedat",
    "lastName":"Başpınar",
    "username":"sedat_test",
    "email":"test@gmail.com",
    "password":"123456"
}
````
#### Response 
```properties
{
    "data": {
        "id": 4,
        "firstName": "Sedat",
        "lastName": "Başpınar",
        "username": "sedat_test"
    },
    "errors": null,
    "time": "2022-12-13"
}
```

## Create Url

````
POST /api-url/url HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZWRhdGJzcCIsInVzZXJJZCI6MSwiaWF0IjoxNjcwOTU2MTM3LCJleHAiOjE2NzEwNDI1Mzd9.znP-X_u26dO-KKsT4O_iIumDE1A4pw4TR2_cS4NLBFM
Content-Type: application/json
Content-Length: 193

{
    "url":"https://spring.io/projects/spring-boot",
    "description":"Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can just run."
}
````

If you want, you can specify with '**expirationDate**' and set an expiration date. (optional)
- Default expiration date can be set from the related method. (expiration date 6 months)

```properties
private LocalDateTime getExpirationDate(LocalDateTime expirationDate) {
    if(expirationDate == null){
        return LocalDateTime.now().plusMonths(6);
    }
    return expirationDate;
}
```

#### Response 
- For example, **65f6e159** created. <br>This URL ( localhost:8080/api-url/65f6e159 ) will redirect to the https://spring.io/projects/spring-boot  [if the url status is active.]
<br>

```properties
{
    "data": {
        "id": 4,
        "url": "https://spring.io/projects/spring-boot",
        "shortenedUrl": "65f6e159",
        "expirationDate": "2023-06-13 21:29",
        "status": "ACTIVE"
    },
    "errors": null,
    "time": "2022-12-13"
}
```


## Get Shortend url
````
GET /api-url/fb0ee5a5 HTTP/1.1
Host: localhost:8080
````
* When the request is made, it will be redirected to https://github.com/sedatbsp if the url `status` is `active`.

## Get By Id

````
GET /api-url/url?id=1 HTTP/1.1
Host: localhost:8080
````

```properties
{
    "data": {
        "id": 1,
        "url": "https://github.com/sedatbsp",
        "shortenedUrl": "fb0ee5a5",
        "expirationDate": "2022-07-18 16:58",
        "status": "ACTIVE"
    },
    "errors": null,
    "time": "2022-12-13"
}
```

## Get All URLS

````
GET /api-url/urls HTTP/1.1
Host: localhost:8080
````

```properties
{
    "data": {
        "items": [
            {
                "id": 1,
                "url": "https://github.com/sedatbsp",
                "shortenedUrl": "fb0ee5a5",
                "expirationDate": "2022-07-18 16:58",
                "status": "ACTIVE"
            },
            {
                "id": 2,
                "url": "https://www.linkedin.com/in/sedatbaspinar/",
                "shortenedUrl": "1d996eac",
                "expirationDate": "2023-01-15 20:06",
                "status": "DELETED"
            },
            {
                "id": 3,
                "url": "https://www.youtube.com/",
                "shortenedUrl": "5e288a2a",
                "expirationDate": "2023-01-15 22:02",
                "status": "ACTIVE"
            }
        ]
    },
    "errors": null,
    "time": "2022-12-13"
}
```


## Delete by ID

````
DELETE /api-url/url?id=4 HTTP/1.1
Host: localhost:8080
````

```properties
{
    "data": {
        "id": 4,
        "url": "https://spring.io/projects/spring-boot",
        "shortenedUrl": "65f6e159",
        "expirationDate": "2023-06-13 21:29",
        "status": "DELETED"
    },
    "errors": null,
    "time": "2022-12-13"
}
```
