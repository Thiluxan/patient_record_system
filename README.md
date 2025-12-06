# Running the Application JAR

This guide explains how to run the Spring Boot application using the generated **JAR file** and how to pass runtime configuration parameters such as database settings and the server port.

---

## ✅ 1. Prerequisites

Before running the JAR, ensure you have:

- **Java 17** installed  
- **PostgreSQL** running  
- The application JAR built using:

```bash
mvn clean package
```

The built JAR will be located in:

```
target/<your-app-name>.jar
```

---

## ✅ 2. Run the JAR With Default Configuration

Run the application with the settings from `application.properties`:

```bash
java -jar target/your-app.jar
```

Default URL:

```
http://localhost:8081
```

---

## ✅ 3. Run the JAR With Custom Parameters

### 🔹 3.1 Change the Server Port

```bash
java -jar target/your-app.jar --server.port=8085
```

---

### 🔹 3.2 Pass Database Configuration

**Windows:**

```bash
java -jar target/your-app.jar ^
  --spring.datasource.url=jdbc:postgresql://localhost:5432/patientdb ^
  --spring.datasource.username=postgres ^
  --spring.datasource.password=postgres
```

**Linux/macOS:**

```bash
java -jar target/your-app.jar   --spring.datasource.url=jdbc:postgresql://localhost:5432/patientdb   --spring.datasource.username=postgres   --spring.datasource.password=postgres
```

---

### 🔹 3.3 Combine Multiple Parameters

```bash
java -jar target/your-app.jar ^
  --server.port=8085 ^
  --spring.datasource.url=jdbc:postgresql://localhost:5432/patientdb ^
  --spring.datasource.username=postgres ^
  --spring.datasource.password=postgres
```

---

## 🎯 Summary

1. Build the jar:  
   ```bash
   mvn clean package
   ```
2. Run with defaults:  
   ```bash
   java -jar target/your-app.jar
   ```
3. Run with configs:  
   ```bash
   java -jar target/your-app.jar --server.port=8085 --spring.datasource.username=postgres ...
   ```

The application should now be running with the desired configuration.
