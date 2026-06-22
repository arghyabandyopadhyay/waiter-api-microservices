# Waiterr API Microservices

A microservices-based banking and restaurant management backend built with Spring Boot and Spring Cloud. The system provides customer management, JWT authentication, payment transactions, OTP verification, and Kitchen Order Ticket (KOT) comment suggestions.

## Architecture

```
Client → API Gateway (JWT Auth + Routing) → Microservices → MySQL
                        ↕
                  Eureka Server (Service Discovery)
```

## Microservices

| Service                  | Port | Description                              |
|--------------------------|------|------------------------------------------|
| EurekaServer             | -    | Service registry and discovery           |
| apiGatewayService        | -    | API Gateway with JWT auth and circuit breaker |
| authservice              | -    | User registration and login (JWT issuing)|
| customerservice          | -    | Customer CRUD and account management     |
| transactionservice       | -    | Payment processing and transaction history |
| otpservice               | -    | OTP generation, email delivery, and verification |
| customerbank             | -    | External customer bank details management |
| commentforkotsuggestion  | -    | KOT comment suggestions for menu items   |

## Tech Stack

- **Language:** Java 11
- **Framework:** Spring Boot 2.7.4, Spring Cloud 2021.0.4
- **Service Discovery:** Netflix Eureka
- **API Gateway:** Spring Cloud Gateway + Hystrix
- **Database:** MySQL 8.0 (Spring Data JPA)
- **Auth:** JWT (jjwt), BCrypt
- **Caching:** Google Guava (OTP storage)
- **Email:** Spring Mail
- **Build:** Maven

## API Endpoints

All requests go through the API Gateway.

### Authentication (Open)
- `POST /api/v1/authentication/register` - Register new customer
- `POST /api/v1/authentication/authenticate` - Login and receive JWT

### Customers (Secured)
- `GET /api/v1/customers` - List all customers
- `GET /api/v1/customers/{id}` - Get customer by ID
- `GET /api/v1/customers/balances/{id}` - Get balances
- `PUT /api/v1/customers/{id}` - Update customer
- `DELETE /api/v1/customers/{id}` - Delete customer

### Transactions (Secured)
- `GET /api/v1/payments/{custId}` - Get transaction history
- `POST /api/v1/payments` - Create transaction

### OTP (Secured)
- `POST /api/v1/otp/generateOtp` - Generate and email OTP
- `POST /api/v1/otp/verifyOtp` - Verify OTP

## Getting Started

### Prerequisites
- Java 11+
- Maven 3.6+
- MySQL 8.0

### Running the Services

Start services in this order:

```bash
# 1. Start Eureka Server
cd EurekaServer
./mvnw spring-boot:run

# 2. Start API Gateway
cd apiGatewayService
./mvnw spring-boot:run

# 3. Start business services (in any order)
cd authservice && ./mvnw spring-boot:run
cd customerservice && ./mvnw spring-boot:run
cd transactionservice && ./mvnw spring-boot:run
cd otpservice && ./mvnw spring-boot:run
cd customerbank && ./mvnw spring-boot:run
cd commentforkotsuggestion && ./mvnw spring-boot:run
```

### Database Setup

Each service requires its own MySQL database. Configure connection details in each service's `application.properties` or `application.yml`.

## Project Structure

```
waiter-api-microservices/
├── EurekaServer/              # Service Discovery
├── apiGatewayService/         # API Gateway + Auth Filter
├── authservice/               # Authentication & JWT
├── customerservice/           # Customer Management
├── transactionservice/        # Payment Processing
├── otpservice/                # OTP via Email
├── customerbank/              # Customer Bank Details
├── commentforkotsuggestion/   # KOT Comment Suggestions
├── COMPONENT_DESIGN.md        # Detailed component design document
└── README.md
```

## Documentation

See [COMPONENT_DESIGN.md](COMPONENT_DESIGN.md) for detailed architecture, data models, inter-service communication patterns, and security design.

## License

See [LICENSE](LICENSE) for details.
