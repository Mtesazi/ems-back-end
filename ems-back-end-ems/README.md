# Employee Management System (EMS) - Backend

A robust REST API backend for managing employee information, built with **Spring Boot 3.3.0** and **Java 21**. This application provides complete CRUD operations for employee records with MySQL database integration and Swagger API documentation.

## 🎯 Features

- **Employee CRUD Operations**: Create, read, update, and delete employee records
- **RESTful API**: Clean and intuitive REST endpoints
- **Swagger Documentation**: Interactive API documentation with Swagger UI
- **MySQL Database**: Persistent data storage with JPA/Hibernate ORM
- **Input Validation**: Email uniqueness and data integrity constraints
- **Error Handling**: Custom exception handling with ResourceNotFoundException
- **Lombok Integration**: Reduced boilerplate code with annotations
- **Spring Boot DevTools**: Hot reload during development

## 📋 Prerequisites

- **Java 21** or higher
- **Maven 3.8.7** or higher
- **MySQL 8.0** or higher
- **Git**

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Mtesazi/ems-back-end.git
cd ems-back-end-ems
```

### 2. Configure MySQL Database

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ems
spring.datasource.username=root
spring.datasource.password=your_password
```

Ensure the database `ems` is created:

```sql
CREATE DATABASE IF NOT EXISTS ems;
```

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Endpoints

All endpoints are prefixed with `/api/employees`

### Create Employee
- **POST** `/api/employees`
- Request body:
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "emailAddress": "john.doe@example.com",
  "contactNumber": "1234567890"
}
```
- Response: `201 Created`

### Get All Employees
- **GET** `/api/employees`
- Response: `200 OK` with list of employees

### Get Employee by ID
- **GET** `/api/employees/{id}`
- Response: `200 OK` with employee details

### Update Employee
- **PUT** `/api/employees/{id}`
- Request body: Updated employee fields
- Response: `200 OK` with updated employee

### Delete Employee
- **DELETE** `/api/employees/{id}`
- Response: `200 OK` with success message

## 📖 Swagger API Documentation

Once the application is running, access the Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

## 🏗️ Project Structure

```
src/
├── main/java/com/mtesazi/ems/
│   ├── controller/
│   │   └── EmployeeController.java
│   ├── service/
│   │   ├── EmployeeService.java
│   │   └── impl/
│   │       └── EmployeeServiceImpl.java
│   ├── repository/
│   │   └── EmployeeRepository.java
│   ├── model/
│   │   └── Employee.java
│   ├── dto/
│   │   └── EmployeeDto.java
│   ├── mapper/
│   │   └── EmployeeMapper.java
│   ├── exception/
│   │   └── ResourceNotFoundException.java
│   ├── swagger/
│   │   └── Swagger.java
│   └── EmsApplication.java
└── resources/
    └── application.properties
```

## 🔧 Technology Stack

- **Framework**: Spring Boot 3.3.0
- **Language**: Java 21
- **Database**: MySQL 8.0+
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven
- **Documentation**: Swagger/OpenAPI 3.0
- **Utilities**: Lombok, Spring DevTools

## 📦 Dependencies

- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-tomcat
- springdoc-openapi-starter-webmvc-ui
- springfox-swagger2 & springfox-swagger-ui
- mysql-connector-java
- lombok
- spring-boot-devtools

## ⚙️ Configuration

Key properties in `application.properties`:

```properties
# Database
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/ems
spring.datasource.username=root
spring.datasource.password=password

# JPA/Hibernate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format.sql=true
```

## 🐛 Troubleshooting

### Connection Refused Error
Ensure MySQL is running and the database `ems` exists with correct credentials.

### TypeTag.UNKNOWN Error
This project uses Java 21. Ensure your Java version matches in `pom.xml`:
```xml
<java.version>21</java.version>
```

## 📝 Employee Model

| Field | Type | Constraints |
|-------|------|-------------|
| id | Long | Primary Key, Auto-generated |
| firstName | String | - |
| lastName | String | - |
| emailAddress | String | Unique, Not Null |
| contactNumber | String | - |

## 🤝 Contributing

1. Create a feature branch (`git checkout -b feature/AmazingFeature`)
2. Commit your changes (`git commit -m 'Add AmazingFeature'`)
3. Push to the branch (`git push origin feature/AmazingFeature`)
4. Open a Pull Request

## 📄 License

This project is open source and available under the MIT License.

## 👤 Author

**Mtesazi**
- GitHub: [@Mtesazi](https://github.com/Mtesazi)

---

**Last Updated**: July 2026
