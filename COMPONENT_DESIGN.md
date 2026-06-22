# Component Design - Waiterr API Microservices

## 1. System Overview

Waiterr API is a microservices-based banking and monetary application built with Spring Boot and Spring Cloud. The system provides customer management, authentication, payment transactions, OTP verification, and restaurant KOT (Kitchen Order Ticket) comment suggestions.

```
┌─────────────────────────────────────────────────────────────────────┐
│                          Client Applications                         │
└─────────────────────────────┬───────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────┐
│                    API Gateway Service (Spring Cloud Gateway)         │
│                    - JWT Authentication Filter                        │
│                    - Route Management                                 │
│                    - Circuit Breaker (Hystrix)                        │
└──────────┬──────────┬──────────┬──────────┬─────────────────────────┘
           │          │          │          │
           ▼          ▼          ▼          ▼
┌────────────┐ ┌──────────┐ ┌──────────┐ ┌──────────────┐
│   Auth     │ │ Customer │ │   OTP    │ │ Transaction  │
│  Service   │ │ Service  │ │ Service  │ │   Service    │
└─────┬──────┘ └────┬─────┘ └──────────┘ └──────┬───────┘
      │              │                           │
      └──────────────┼───────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────────────────┐
│                       Eureka Server (Service Discovery)               │
└─────────────────────────────────────────────────────────────────────┘
```

## 2. Component Details

### 2.1 Eureka Server (Service Discovery)

| Attribute       | Value                              |
|-----------------|------------------------------------|
| Artifact ID     | `EurekaServer`                     |
| Port            | Configured externally              |
| Role            | Service registry and discovery     |
| Dependencies    | `spring-cloud-starter-netflix-eureka-server` |

Provides service registration and discovery for all microservices. Each service registers itself with Eureka, enabling client-side load balancing via `@LoadBalanced` RestTemplate.

---

### 2.2 API Gateway Service

| Attribute       | Value                              |
|-----------------|------------------------------------|
| Artifact ID     | `apiGatewayService`                |
| Role            | Unified entry point, authentication, routing |
| Framework       | Spring Cloud Gateway               |
| Circuit Breaker | Netflix Hystrix                    |

**Responsibilities:**
- Route incoming requests to appropriate downstream services
- Validate JWT tokens on secured endpoints
- Provide fallback responses when services are unavailable
- Enforce open/secured endpoint policies

**Route Configuration:**

| Route ID                        | Path Pattern              | Target Service                  |
|---------------------------------|---------------------------|---------------------------------|
| WaiterrApiCustomerService       | `/api/v1/customers/**`    | lb://WaiterrApiCustomerService  |
| WaiterrApiOtpService            | `/api/v1/otp/**`          | lb://WaiterrApiOtpService       |
| WaiterrApiAuthenticationService | `/api/v1/authentication/**` | lb://WaiterrApiAuthenticationService |
| WaiterrApiTransactionService    | `/api/v1/payments/**`     | lb://WaiterrApiTransactionService |

**Open Endpoints (no auth required):**
- `/api/v1/authentication/authenticate`
- `/api/v1/authentication/register`

**Key Classes:**
- `GatewayConfig` - Route definitions
- `AuthenticationFilter` - JWT validation gateway filter
- `JwtUtil` - Token parsing and validation
- `RouterValidator` - Open/secured route classification
- `FallbackController` - Service unavailability fallback

---

### 2.3 Authentication Service

| Attribute       | Value                              |
|-----------------|------------------------------------|
| Artifact ID     | `authservice`                      |
| Role            | User registration and login        |
| Database        | MySQL                              |
| Eureka Client   | Enabled                            |

**Responsibilities:**
- Register new customers (delegates to Customer Service)
- Authenticate customers and issue JWT tokens
- Password hashing with BCrypt

**API Endpoints:**

| Method | Path                                 | Description           |
|--------|--------------------------------------|-----------------------|
| POST   | `/api/v1/waiterr/authentication/register` | Register a new customer |
| POST   | `/api/v1/waiterr/authentication/authenticate` | Login and get JWT token |

**Inter-Service Communication:**
- Calls `CustomerService` via RestTemplate for registration (`POST /api/v1/customers/register`)
- Calls `CustomerService` via RestTemplate for login validation (`POST /api/v1/customers/authenticate`)

**JWT Configuration:**
- Algorithm: HS256
- Token expiry: 1 hour
- Claims: Subject (email)

---

### 2.4 Customer Service

| Attribute       | Value                              |
|-----------------|------------------------------------|
| Artifact ID     | `customerservice`                  |
| Role            | Customer CRUD and account management |
| Database        | MySQL                              |
| Eureka Client   | Enabled                            |

**Responsibilities:**
- Full CRUD operations on customer profiles
- Auto-generation of account numbers
- Password-based authentication (BCrypt)
- Account verification by account number and IFSC

**API Endpoints:**

| Method | Path                                    | Description                    |
|--------|-----------------------------------------|--------------------------------|
| GET    | `/api/v1/waiterr/customers`             | List all customers             |
| GET    | `/api/v1/waiterr/customers/{custId}`    | Get customer by ID             |
| GET    | `/api/v1/waiterr/customers/balances/{custId}` | Get customer balances    |
| GET    | `/api/v1/waiterr/customers/byEmailId/{emailId}` | Get customer by email  |
| POST   | `/api/v1/waiterr/customers/register`    | Register new customer          |
| POST   | `/api/v1/waiterr/customers/authenticate`| Authenticate customer          |
| POST   | `/api/v1/waiterr/customers/verify`      | Verify by account details      |
| PUT    | `/api/v1/waiterr/customers/{custId}`    | Update customer                |
| DELETE | `/api/v1/waiterr/customers/{custId}`    | Delete customer                |

**Data Model - Customer:**
- `id` (UUID, auto-generated)
- `emailId`, `mobileNo`, `firstName`, `lastName`
- `password` (BCrypt hashed)
- `dob`, `address`, `city`, `state`, `country`, `zipcode`
- `accNo` (auto-generated), `ifscCode` (default: "RBS000890")
- `profileUrl`, `currency` (default: "$")
- `checkinBalance` (default: 5000.00), `savingsBalance`
- `jwtToken`

---

### 2.5 Transaction Service

| Attribute       | Value                              |
|-----------------|------------------------------------|
| Artifact ID     | `transactionservice`               |
| Role            | Payment processing and transaction history |
| Database        | MySQL                              |
| Eureka Client   | Enabled                            |

**Responsibilities:**
- Process money transfers between customers
- Update sender and receiver balances
- Implement round-off savings (spare change to savings)
- Maintain transaction history

**API Endpoints:**

| Method | Path                              | Description                  |
|--------|-----------------------------------|------------------------------|
| GET    | `/api/v1/waiterr/payments/{custId}` | Get transaction history    |
| POST   | `/api/v1/waiterr/payments`        | Create new transaction       |

**Inter-Service Communication:**
- Calls `CustomerService` to fetch sender/receiver details (`GET /api/v1/customers/{id}`)
- Calls `CustomerService` to update balances (`PUT /api/v1/customers/{id}`)

**Data Model - Transaction:**
- `id` (UUID, auto-generated)
- `amount`, `roundedOffAmount`
- `timeStamp` (LocalDateTime)
- `receiverAccountNumber`, `customerAccountNumber`
- `remarks`, `ifsc`
- `currentBalance`, `receiverCurrentBalance`
- `recipient` (UUID), `customerId` (UUID)

---

### 2.6 OTP Service

| Attribute       | Value                              |
|-----------------|------------------------------------|
| Artifact ID     | `otpservice`                       |
| Role            | OTP generation, delivery, and verification |
| Eureka Client   | Enabled                            |

**Responsibilities:**
- Generate 6-digit OTPs
- Send OTPs via email (Spring Mail)
- Cache OTPs with 5-minute expiry (Guava Cache)
- Verify OTPs against cached values

**API Endpoints:**

| Method | Path                           | Description          |
|--------|--------------------------------|----------------------|
| POST   | `/api/v1/waiterr/otp/generateOtp` | Generate and send OTP |
| POST   | `/api/v1/waiterr/otp/verifyOtp`   | Verify submitted OTP |

**OTP Storage:**
- In-memory cache using Google Guava `LoadingCache`
- Auto-expires after 5 minutes
- Key: customer ID, Value: OTP integer

---

### 2.7 Customer Bank Service

| Attribute       | Value                              |
|-----------------|------------------------------------|
| Artifact ID     | `customerbank`                     |
| Role            | External customer bank details     |
| Database        | MySQL                              |
| Eureka Client   | Disabled (commented out)           |

**Responsibilities:**
- Store and manage external customer bank details
- Lookup by UUID or mobile number

**API Endpoints:**

| Method | Path                                | Description               |
|--------|-------------------------------------|---------------------------|
| GET    | `/api/v1/waiterr/customerbank`      | Get customer details      |
| POST   | `/api/v1/waiterr/customerbank`      | Add customer details      |
| PUT    | `/api/v1/waiterr/customerbank`      | Update customer details   |
| DELETE | `/api/v1/waiterr/customerbank`      | Delete customer details   |

**Data Model - CustomerDetails:**
- `id` (UUID, auto-generated)
- `name`
- `mobileNumber`

---

### 2.8 Comment For KOT Suggestion Service

| Attribute       | Value                              |
|-----------------|------------------------------------|
| Artifact ID     | `commentforkotsuggestion`          |
| Role            | Kitchen Order Ticket comment management |
| Database        | MySQL                              |
| Eureka Client   | Disabled (commented out)           |

**Responsibilities:**
- Manage predefined comments/suggestions for Kitchen Order Tickets
- Associate comments with menu items

**API Endpoints:**

| Method | Path                                           | Description                |
|--------|------------------------------------------------|----------------------------|
| GET    | `/api/v1/waiterr/commentforkotsuggestion`      | Get comments by ID or menu item |
| POST   | `/api/v1/waiterr/commentforkotsuggestion`      | Add new comment suggestion |
| DELETE | `/api/v1/waiterr/commentforkotsuggestion`      | Delete comment suggestion  |

**Data Model - CommentForKOTSuggestion:**
- `id` (UUID, auto-generated)
- `commentForKot` (String)
- `menuItemId` (UUID)

---

## 3. Inter-Service Communication

```
┌──────────────┐         REST/HTTP          ┌──────────────────┐
│  Auth        │ ───────────────────────────▶│  Customer        │
│  Service     │  register, authenticate     │  Service         │
└──────────────┘                             └──────────────────┘
                                                      ▲
┌──────────────┐         REST/HTTP                    │
│ Transaction  │ ─────────────────────────────────────┘
│  Service     │  get/update customer balances
└──────────────┘
```

**Communication Pattern:** Synchronous REST via `RestTemplate` with `@LoadBalanced` annotation for client-side load balancing through Eureka service discovery.

| Source Service     | Target Service   | Operations                                  |
|-------------------|------------------|---------------------------------------------|
| Auth Service       | Customer Service | Register customer, Authenticate customer    |
| Transaction Service| Customer Service | Get customer details, Update balances       |

---

## 4. Technology Stack

| Layer              | Technology                                    |
|-------------------|-----------------------------------------------|
| Language           | Java 11                                       |
| Framework          | Spring Boot 2.7.4                             |
| Cloud Framework    | Spring Cloud 2021.0.4                         |
| Service Discovery  | Netflix Eureka                                |
| API Gateway        | Spring Cloud Gateway                          |
| Circuit Breaker    | Netflix Hystrix                               |
| Database           | MySQL 8.0                                     |
| ORM                | Spring Data JPA / Hibernate                   |
| Authentication     | JWT (jjwt)                                    |
| Password Hashing   | BCrypt (Spring Security)                      |
| Caching            | Google Guava (in-memory)                      |
| Email              | Spring Mail (JavaMailSender)                  |
| Build Tool         | Maven                                         |
| Utilities          | Lombok, Jackson                               |

---

## 5. Security Architecture

### Authentication Flow

```
Client                Gateway              Auth Service         Customer Service
  │                     │                       │                      │
  │  POST /register     │                       │                      │
  ├────────────────────▶│──────────────────────▶│─────────────────────▶│
  │                     │                       │  POST /customers/    │
  │                     │                       │  register            │
  │◀────────────────────│◀──────────────────────│◀─────────────────────│
  │   Customer + JWT    │                       │                      │
  │                     │                       │                      │
  │  GET /customers/x   │                       │                      │
  │  Authorization:     │                       │                      │
  │  Bearer <token>     │                       │                      │
  ├────────────────────▶│ Validate JWT          │                      │
  │                     │──────────────────────────────────────────────▶│
  │◀────────────────────│◀─────────────────────────────────────────────│
  │   Customer data     │                       │                      │
```

### Security Mechanisms
- **JWT Token Validation** at the API Gateway layer
- **BCrypt Password Hashing** for stored credentials
- **Open/Secured Route Classification** via `RouterValidator`
- **OTP Verification** for sensitive operations

---

## 6. Data Storage

Each service owns its data and connects to MySQL independently. There is no shared database pattern.

| Service            | Database Tables              |
|-------------------|------------------------------|
| Customer Service   | `customer`, `global_key_values` |
| Transaction Service| `transactions`               |
| Customer Bank      | `CustomerDetails`            |
| KOT Suggestion     | `CommentForKOTSuggestion`    |
| Auth Service       | `customer` (shared schema)   |

---

## 7. Deployment Considerations

- Each service is independently deployable as a Spring Boot JAR
- Services register with Eureka for dynamic discovery
- API Gateway provides a single entry point for all client traffic
- Services use logical names (e.g., `WaiterrApiCustomerService`) for inter-service calls, resolved via Eureka
- Hystrix provides circuit breaking for resilience at the gateway level
