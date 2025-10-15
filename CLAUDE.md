# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Java 3 JSP/Servlet learning project that contains multiple lab exercises (lab1-lab7) and an assignment (ASM) implementing an ABC News content management system. The project uses Maven for build management and is designed to run on Apache Tomcat 10+.

**Package**: `com.wangquocthai.java3_jsp_servlet`
**Java Version**: 21
**Build Tool**: Maven
**Server**: Apache Tomcat 10+

## Build and Development Commands

### Build Project
```bash
mvn clean compile
```

### Package WAR File
```bash
mvn clean package
```

The WAR file will be generated at: `target/Java3_JSP_Servlet-1.0-SNAPSHOT.war`

### Run Tests
```bash
mvn test
```

### Clean Build Artifacts
```bash
mvn clean
```

## Project Structure

### Lab Exercises (`src/main/java/.../lab*`)

The project contains progressive lab exercises demonstrating servlet concepts:

- **lab1**: Basic servlets (HelloServlet, HomeServlet, CrudServlet, UrlInfoServlet)
- **lab2**: Form handling and request/response (FormServlet, UserServlet, SharerServlet)
- **lab3**: Data rendering with models (CountryServlet, ProductServlet, NewsServlet)
- **lab4**: File uploads and authentication (LoginServlet, RegisterServlet, UploadServlet, CalculateServlet)
- **lab5**: JavaBeans, email sending, and session management (BeanServlet, EmailServlet, LoginServlet, LogoutServlet, Mailer)
- **lab6**: JDBC database operations (Jdbc utility class, Main_Bai2/3/4)
- **lab7**: DAO pattern implementation (DepartmentDAO, DepartmentDAOImpl, DeparmentServlet)

Each lab has corresponding JSP views in `src/main/webapp/lab*/`

### Main Assignment: ABC News System (`ASM/`)

A complete MVC news management application with three user roles:

**Architecture Pattern**: Model-View-Controller (MVC)

#### Models (`ASM/model/`)
- `User.java`: User accounts with roles (Admin/Reporter/Reader)
- `News.java`: News articles
- `Category.java`: News categories
- `Newsletter.java`: Newsletter subscriptions

#### Controllers (`ASM/controller/`)
- `AdminServlet.java` (`/admin`): User and newsletter management
- `ReporterServlet.java` (`/reporter`): News article CRUD operations
- `ReaderServlet.java` (`/reader`): Public news viewing

#### Views (`ASM/`)
- `reader/`: News list, detail, home, newsletter pages
- `reporter/`: News CRUD interface, dashboard
- `admin/`: User CRUD, category CRUD, newsletter CRUD, dashboard
- `layout/`: Shared header, footer, menu components

### Database Configuration

The project uses MySQL database connectivity with two JDBC utility implementations:

**Connection Details** (in `lab6/Jdbc.java` and `lab7/utils/Jdbc.java`):
- **Driver**: `com.mysql.cj.jdbc.Driver`
- **URL**: `jdbc:mysql://localhost:3306/DB_Java3_Lab6`
- **Username**: `wangquockhanh`
- **Password**: `matkhau123`

When working with database features, update these credentials as needed or externalize to configuration files.

### Servlet Mappings

Most servlets use `@WebServlet` annotations for URL mapping. The `web.xml` currently has ASM servlet mappings commented out and defaults to `thai_homepage.jsp` as the welcome file.

## Key Technologies and Dependencies

- **Jakarta Servlet API 6.1.0**: Core servlet functionality
- **Jakarta JSTL 3.0.0**: Tag libraries for JSP pages
- **MySQL Connector 9.4.0**: MySQL database connectivity
- **Oracle JDBC 23.9.0**: Oracle database support
- **Jakarta Mail API 2.1.3**: Email functionality (used in lab5)
- **Commons BeanUtils 1.11.0**: Bean property utilities
- **Lombok 1.18.38**: Boilerplate code reduction
- **JUnit 5.13.2**: Testing framework

## Development Patterns

### Servlet Pattern
All servlets extend `HttpServlet` and implement `doGet()` and/or `doPost()` methods:
```java
@WebServlet(name = "ExampleServlet", value = "/example")
public class ExampleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set attributes
        request.setAttribute("data", someData);
        // Forward to JSP
        request.getRequestDispatcher("/path/to/view.jsp").forward(request, response);
    }
}
```

### Database Access Pattern
The project uses a static JDBC utility class with parameterized queries:
```java
// Execute update
int rows = Jdbc.executeUpdate("INSERT INTO table VALUES (?, ?)", value1, value2);

// Execute query
ResultSet rs = Jdbc.executeQuery("SELECT * FROM table WHERE id = ?", id);
```

### Model Objects
Models are POJOs with private fields, getters/setters, and helper methods for display formatting (e.g., `getRoleString()`, `getGenderString()`).

### DAO Pattern (lab7)
For more complex data access, use the DAO interface pattern:
- Define DAO interface with CRUD methods
- Implement in concrete class (e.g., `DepartmentDAOImpl`)
- Use dependency injection or factory pattern

## Important Notes

### Mock Data
The ASM project currently uses mock data in servlets (`createMockUsers()`, `createMockNewsletters()`, etc.). Database integration is planned but not yet implemented.

### Email Configuration
The `Mailer` class in lab5 contains hardcoded Gmail credentials at `lab5/Mailer.java:12-13`. These should be externalized to configuration files before production use.

### URL Context Path
The application context path is `/Java3_JSP_Servlet`. All URLs should account for this:
```
http://localhost:8080/Java3_JSP_Servlet/[servlet-path]
```

### Welcome Files
Default landing page is `thai_homepage.jsp` (configured in `web.xml`).

## Common Development Tasks

### Adding a New Lab Exercise
1. Create package under `com.wangquocthai.java3_jsp_servlet.labN`
2. Implement servlet(s) with `@WebServlet` annotation
3. Create corresponding JSP views in `src/main/webapp/labN/`
4. Add link to lab from `thai_homepage.jsp`

### Adding ASM Features
1. Create/modify model in `ASM/model/`
2. Update servlet in `ASM/controller/` to handle new operations
3. Modify JSP view in appropriate `ASM/` subdirectory
4. Use JSTL tags for data rendering

### Database Integration
1. Update connection details in `Jdbc` utility class
2. Create/verify database schema matches entity structure
3. Replace mock data in servlets with DAO calls
4. Test with actual database before committing

## Project Context

This appears to be coursework for Java 3 (JSP/Servlet) with progressive labs building toward a final assignment (ASM). The latest work is on lab6 (based on git commits). When adding features, maintain consistency with the established patterns in earlier labs.