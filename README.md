# Microservices billing application

Spring Boot microservices for managing customers, products, and invoices, with Eureka discovery, Spring Cloud Gateway, Spring Cloud Config, OpenFeign, and an Angular client.

## Services

| Service | Port | Purpose |
| --- | ---: | --- |
| config-service | 9999 | Central configuration from `config-repo` |
| discovery-service | 8761 | Eureka registry |
| gateway-service | 8888 | Static and discovery-based API routes |
| customer-service | 801 | Customer management |
| inventory-service | 802 | Product management |
| billing-service | 803 | Invoices and Feign lookups |
| Angular client | 4200 | Customer, product, and invoice screens |

## Run

Use Java 17 or newer and Maven. Start each Spring Boot service in a separate terminal, in this order: `config-service`, `discovery-service`, `customer-service`, `inventory-service`, `billing-service`, and `gateway-service`.

From the project root, run a service with `mvn -pl <service-directory> spring-boot:run`. For example, `mvn -pl discovery-service spring-boot:run`.

Start the Angular client from `angular-client` with `npm install` followed by `npm start`. The client sends requests through `http://localhost:8888`.

## API

- `GET/POST /customers`, `GET/PUT/DELETE /customers/{id}`
- `GET/POST /products`, `GET/PUT/DELETE /products/{id}`
- `GET/POST /bills`, `GET /bills/{id}`

The gateway exposes these paths as well as discovery routes under `/<service-id>/**` (for example `/customer-service/customers`). H2 databases are local to their owning services. The billing service stores customer and product IDs, then obtains their details from the owning services through OpenFeign.
