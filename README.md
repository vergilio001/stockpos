# 📦 Project Name: StockPOS

A lightweight inventory management system built with Spring Boot and H2 for managing stock items via CSV import and search functionality.

## 🚀 Features

- Import stock data from CSV files
- Search stock by SKU or name
- Detect and flag out-of-stock items
- In-memory H2 database for fast prototyping
- RESTful API endpoints

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- H2 Database
- JPA/Hibernate
- OpenCSV

## 📂 Project Structure
src/
└── main/
    ├── java/
    │   └── com/
    │       └── example/
    │           └── stockpos/
    │               ├── controller/
    │               ├── service/
    │               ├── model/
    │               └── repository/
    └── resources/
        ├── application.properties
        └── sql/
            └── CreateStockTableRuntime.sql

Accessing the H2 Console
Once the application is running, you can access the H2 web console at:
http://localhost:8080/h2-console


JDBC URL: jdbc:h2:mem:stockpos
Username: sa
Password: (leave blank)


Base URL: /api/stockpos

POST /v1/importStocks
Description:
Imports stock data from a CSV file located on the server. The file is parsed and saved to the database.
Request:
No request body is needed. The CSV file path is resolved internally.
Responses:
- 200 OK — Stocks imported successfully
- 404 Not Found — CSV file not found
- 400 Bad Request — CSV validation failed
- 500 Internal Server Error — Unexpected error occurred

GET /v1/getAllStocks
Description:
Retrieves all stock records from the database.
Response Example:
{
  "status": "success",
  "data": [
    {
      "sku": "SKU123",
      "name": "Apple Juice",
      "stockQuantity": 25
    }
  ]
}
GET /v1/getStocks
Description:
Searches for stock records by SKU or product name. Returns matching records and notes any out-of-stock items.
Query Parameters:
- sku (optional): SKU to search
- name (optional): Product name to search
Response Example:
{
  "message": "Data Found Successfully",
  "data": [
    {
      "sku": "SKU123",
      "name": "Apple Juice",
      "stockQuantity": 0
    }
  ],
  "Note": "The following items are out of stock: Apple Juice"
}




