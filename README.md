
# Microservices Project: Product, Category, and Kafka Consumer

## Overview

This project consists of three microservices: Product Service, Category Service, and Kafka Consumer Service. These services work together to implement a basic Product-Category relationship while using Kafka for event-driven messaging to log changes in the data.

## Microservices Overview

### 1. **Product Service**
- Handles CRUD operations for products.
- Each product is associated with a category (via a foreign key relationship).
- Produces messages to Kafka for `Create`, `Update`, and `Delete` operations.

### 2. **Category Service**
- Handles CRUD operations for categories.
- Prevents the deletion of a category if it is linked to a product.
- Produces messages to Kafka for `Create`, `Update`, and `Delete` operations.

### 3. **Kafka Consumer Service**
- Consumes messages from Kafka topics produced by the Product and Category services.
- Stores audit logs of operations (Create, Update, Delete) into a dedicated audit table.

## Communication Between Microservices

- **Feign Client**: Product Service and Category Service communicate with each other using Feign Client. This allows the Product Service to fetch category information and perform necessary operations.
- **Kafka Messaging**: Both Product and Category services publish events to Kafka topics upon data changes (create, update, delete). The Kafka Consumer Service listens to these topics and logs the events in the audit table.

## API Routes

### Product Service (Port: 8091)

- **GET /product/v1.0**: Get all products
- **POST /product/v1.0**: Add a new product
- **GET /product/v1.0/{id}**: Get a specific product by ID
- **PUT /product/v1.0/{id}**: Update a product by ID
- **DELETE /product/v1.0/{id}**: Delete a product by ID

### Category Service (Port: 8092)

- **GET /category/v1.0**: Get all categories
- **POST /category/v1.0**: Add a new category
- **GET /category/v1.0/{id}**: Get a specific category by ID
- **PUT /category/v1.0/{id}**: Update a category by ID
- **DELETE /category/v1.0/{id}**: Delete a category by ID


## Database Structure

### Product Service Database

| Table: master_product |
|-----------------------|
| productID (PK)        |
| productName           |
| productDescription    |
| categoryID (FK)       |

### Category Service Database

| Table: master_category |
|------------------------|
| categoryID (PK)        |
| categoryName           |

### Kafka Consumer Service Database

| Table: audit_log      |
|-----------------------|
| id (PK)               |
| message            |

## PostgreSQL Configuration (Local Environment)

Each microservice is configured to use PostgreSQL for database management. The following settings are used in `application.properties` files:

```
spring.datasource.url=jdbc:postgresql://localhost:8090/{database_name}
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Kafka Configuration (Local Environment)

The following Kafka configuration is applied:

```
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=group_id
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.enable-auto-commit=false
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
```

## Additional Information

This project demonstrates microservice architecture with event-driven messaging using Kafka. It ensures data consistency and tracks audit logs of operations for each entity in the system.
