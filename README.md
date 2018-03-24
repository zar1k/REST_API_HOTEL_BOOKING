## Test task. Create REST application Hotel booking

### With capabilities (each represented by separate endpoint):

1. View list of available rooms (room have a number, category, price, additional options like breakfast, cleaning with additional cost) for specified dates.

2. View rooms filtered by category.

3. Create user.

4. User can book the room for specified days.

5. User can view his booking.

6. User can get the total price of the booking (room for dates period + cost of additional options).

7. View all bookings for the hotel.
 
Tech stack: Java 8, Spring Boot, Spring MVC, Spring Data, Hibernate, H2, maven.

Authentication and authorization are not included in the task.

### Requirements:
1. Source code must be stored on GitHub with access to the repository.

2. The repository must contain file README.MD with instructions for launching the application and any other necessary documentation for the application (in English).

3. Running the application should not require a standalone application server (application should run on embedded tomcat server) or installation of any software except Java and maven. 

4. The project should contain SQL-script for creating database tables and filling them with data necessary to test application.

5. The project should be covered with unit tests.

6. Availability of UML class diagram is a plus.

 ### Evaluation criteria:
* functional correctness according to the technical requirements,

* readability, maintainability, and compliance of the code with OOP and SOLID principles,

* documentation for the application and  the code,

* any non-standard technical solutions,

* any additional features not specified in the technical requirements, but making the application more functional or convenient,

* task execution time.

## Project Configuration

The project contains SQL-script to filling the database tables with the test data needed to test the application. The file is located: ```\src\main\resources\data.sql```.

**Attention: the database tables are created automatically. Used H2 in-memory database**

Before running an ``unit tests``, you need to rename the ``date.sql`` file.
The file is located: ```\src\main\resources\data.sql```.

**The UML class diagram**

![uml](https://user-images.githubusercontent.com/14240692/37155001-c711cf30-22f2-11e8-83de-75b006634f9a.png)

## Hotel Booking API Documentation

**Public API Methods**

**Room**
+ Endpoint: /rooms/status/{status}
+ Method: GET
+ Optional parameters:
    + (String) status - returns a list of available / bookings rooms (room have a number, category, price, additional options like breakfast, cleaning with additional cost) for the hotel. Valid values are:
    `"OCCUPIED"` or `"occupied"`, `"UNOCCUPIED"` or `"unoccupied"`
+ Example: <http://localhost:8080/rooms/status/OCCUPIED>
+ Sample Response:
```JSON
//JSON 
[
    {
        "id": 10004,
        "roomNumber": 204,
        "category": "SINGLE",
        "price": 1000,
        "status": "OCCUPIED",
        "options": [
            {
                "id": 10000,
                "options": "BREAKFAST",
                "price": 100
            },
            {
                "id": 10001,
                "options": "CLEANING",
                "price": 50
            }
        ]
    },
    {
        "id": 10014,
        "roomNumber": 500,
        "category": "PRESIDENTIAL",
        "price": 6000,
        "status": "OCCUPIED",
        "options": [
            {
                "id": 10006,
                "options": "BREAKFAST",
                "price": 500
            },
            {
                "id": 10007,
                "options": "CLEANING",
                "price": 400
            }
        ]
    }
]
```

+ Endpoint: /rooms/category/{category}
+ Method: GET
+ Optional parameters:
    + (String) category - returns rooms filtered by category. Valid values are:
        `"SINGLE"` or `"single"`, `"DOUBLE"` or `"double"`, `"DELUXE"` or `"deluxe"`, `"PRESIDENTIAL"` or `"presidential"`
+ Example: <http://localhost:8080/rooms/category/DOUBLE>
+ Sample Response:
```JSON
//JSON 
[
    {
        "id": 10005,
        "roomNumber": 300,
        "category": "DOUBLE",
        "price": 1500,
        "status": "UNOCCUPIED",
        "options": [
            {
                "id": 10002,
                "options": "BREAKFAST",
                "price": 200
            },
            {
                "id": 10003,
                "options": "CLEANING",
                "price": 100
            }
        ]
    },
    {
        "id": 10006,
        "roomNumber": 301,
        "category": "DOUBLE",
        "price": 1500,
        "status": "UNOCCUPIED",
        "options": [
            {
                "id": 10002,
                "options": "BREAKFAST",
                "price": 200
            },
            {
                "id": 10003,
                "options": "CLEANING",
                "price": 100
            }
        ]
    },
    {
        "id": 10007,
        "roomNumber": 302,
        "category": "DOUBLE",
        "price": 1500,
        "status": "UNOCCUPIED",
        "options": [
            {
                "id": 10002,
                "options": "BREAKFAST",
                "price": 200
            },
            {
                "id": 10003,
                "options": "CLEANING",
                "price": 100
            }
        ]
    },
    {
        "id": 10008,
        "roomNumber": 303,
        "category": "DOUBLE",
        "price": 1500,
        "status": "UNOCCUPIED",
        "options": [
            {
                "id": 10002,
                "options": "BREAKFAST",
                "price": 200
            },
            {
                "id": 10003,
                "options": "CLEANING",
                "price": 100
            }
        ]
    },
    {
        "id": 10009,
        "roomNumber": 304,
        "category": "DOUBLE",
        "price": 1500,
        "status": "UNOCCUPIED",
        "options": [
            {
                "id": 10002,
                "options": "BREAKFAST",
                "price": 200
            },
            {
                "id": 10003,
                "options": "CLEANING",
                "price": 100
            }
        ]
    }
]
```

**Booking**
+ Endpoint: /booking/{customerId}
+ Method: GET
+ Optional parameters:
    + (int) customerId - Customer can view his booking.
+ Example: <http://localhost:8080/booking/10001>
+ Sample Response:
```JSON
//JSON 
[
    {
        "id": 10000,
        "rooms": [
            {
                "id": 10004,
                "roomNumber": 204,
                "category": "SINGLE",
                "price": 1000,
                "status": "OCCUPIED",
                "options": [
                    {
                        "id": 10000,
                        "options": "BREAKFAST",
                        "price": 100
                    },
                    {
                        "id": 10001,
                        "options": "CLEANING",
                        "price": 50
                    }
                ]
            }
        ],
        "startDate": "2017-09-14T06:57:24.124+0000",
        "endDate": "2017-09-20T10:00:00.124+0000",
        "customer": {
            "id": 10001,
            "name": "Olga",
            "surname": "Fedorov",
            "address": "Ukraine, Kharkov"
        }
    }
]
```

+ Endpoint: /booking/price/{customerId}
+ Method: GET
+ Optional parameters:
    + (int) customerId - Customer can get the total price of the booking (room for dates period + cost of additional options).
+ Example: <http://localhost:8080/booking/price/10001>
+ Sample Response:
```JSON
6900
```

+ Endpoint: /booking 
+ Method: POST (body -- booking) -  Customer can book the room for specified days.
+ Example: <http://localhost:8080/booking>
+ Sample Response:
```JSON
//JSON 
[
    {
        "id": 10000,
        "rooms": [
            {
                "id": 10004,
                "roomNumber": 204,
                "category": "SINGLE",
                "price": 1000,
                "status": "OCCUPIED",
                "options": [
                    {
                        "id": 10000,
                        "options": "BREAKFAST",
                        "price": 100
                    },
                    {
                        "id": 10001,
                        "options": "CLEANING",
                        "price": 50
                    }
                ]
            }
        ],
        "startDate": "2017-09-14T06:57:24.124+0000",
        "endDate": "2017-09-20T10:00:00.124+0000",
        "customer": {
            "id": 10001,
            "name": "Olga",
            "surname": "Fedorov",
            "address": "Ukraine, Kharkov"
        }
    }
]
```

**Customer**
+ Endpoint: /customer 
+ Method: POST (body -- customer) -  Create customer.
+ Example: <http://localhost:8080/customer>
+ Sample Response:
```JSON
//JSON 
[
    {
        "id": 1,
        "name": "Andrew",
        "surname": "Zarazka",
        "email":"andreyzarazka@gmail.com"
        "address": "Ukraine, Kharkov"
    }
]
```
