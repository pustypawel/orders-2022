# orders-2022

## OpenApi

Linki:  
https://code.visualstudio.com/  
https://spec.openapis.org/oas/v3.1.0  
https://oai.github.io/Documentation/best-practices.html      
https://redoc.ly/  
https://marketplace.visualstudio.com/items?itemName=Redocly.openapi-vs-code    
https://editor.swagger.io/  
https://redoc.ly/docs/cli/resources/built-in-rules/

## Next steps

### 1. Properties

- @ConfigurationProperties
- @Value

### 2. Database

- H2
- Spring JDBC
- Spring Data JPA
- TestContainers?

#### 2.1 Database design/schema

Tables: orders, order_items

orders:

| id | state | amount |
| --- | --- |---|
| f4860292-6b26-48a0-b1e7-365e2b3a1af8 | CREATED | 10.00 |

order_items:

| id | order_id | product_id | quantity | amount |
| --- | --- | --- | --- |---|
| 8cc033a8-83f8-4eee-afd1-23a80350193f | f4860292-6b26-48a0-b1e7-365e2b3a1af8 | fb069987-e8c8-40cb-8336-7d6a14d0f635 | 1.0 | 10.00 |

## 3. Web

- HTML + CSS
- Ajax
- VueJS?

