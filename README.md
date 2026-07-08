# 🏦 Banking System REST API

A fully functional Banking System REST API built with **Spring Boot**, **MySQL**, and **JWT Authentication**.

---

## 👨‍💻 Built By
- **Prathmesh Patil** (Prathmesh07778)
- **Prathmesh** (Prathmesh0777)

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 17+ | Programming Language |
| Spring Boot 3.3 | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Token-based Security |
| Spring Data JPA | Database Access |
| MySQL | Database |
| Lombok | Reduce Boilerplate Code |
| Maven | Build Tool |

---

## ✅ Features

- User Registration & Login with JWT
- Create, View, Update, Delete Bank Accounts
- Deposit, Withdraw, Transfer Money
- Transaction History
- Input Validation
- Secure Endpoints (JWT Protected)

---

## 🚀 Getting Started

### Prerequisites
- Java 17 or above
- MySQL installed and running
- Maven

### 1. Clone the Repository
```bash
git clone https://github.com/Prathmesh07778/banking_api.git
cd banking_api
```

### 2. Create MySQL Database
```sql
CREATE DATABASE banking_db;
```

### 3. Configure application.properties
Open `src/main/resources/application.properties` and update:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.security.user.name=admin
spring.security.user.password=admin123
```

### 4. Run the Application
```bash
./mvnw spring-boot:run
```

App will start at: `http://localhost:8080`

---

## 📌 API Endpoints

### 🔐 Authentication (No token required)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login and get JWT token |

**Register Request:**
```json
{
    "name": "Prathmesh Patil",
    "email": "prathmesh@gmail.com",
    "password": "pass1234",
    "phone": "9876543210"
}
```

**Login Request:**
```json
{
    "email": "prathmesh@gmail.com",
    "password": "pass1234"
}
```

**Login Response:**
```json
{
    "success": true,
    "message": "Login successful",
    "data": "eyJhbGciOiJIUzI1NiJ9..."
}
```

> Copy the token from `data` field and use it in all other requests as Bearer Token.

---

### 🏦 Account Endpoints (JWT Required)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/accounts` | Get all accounts |
| GET | `/accounts/{id}` | Get account by ID |
| POST | `/accounts` | Create new account |
| PUT | `/accounts/{id}` | Update account |
| DELETE | `/accounts/{id}` | Delete account |

**Create Account Request:**
```json
{
    "accountNumber": 6507202310,
    "holderName": "Prathmesh Patil",
    "balance": 10000.0,
    "accountType": "Savings"
}
```

---

### 💸 Transaction Endpoints (JWT Required)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/transactions/deposit/{accountId}?amount=5000` | Deposit money |
| POST | `/transactions/withdraw/{accountId}?amount=3000` | Withdraw money |
| POST | `/transactions/transfer?fromId=1&toId=2&amount=2000` | Transfer money |
| GET | `/transactions/history/{accountId}` | Get transaction history |

---

## 🔑 How to Use JWT Token in Postman

1. Login using `/api/auth/login`
2. Copy the token from the response
3. In Postman → **Authorization** tab → **Bearer Token**
4. Paste the token
5. Send your request

---

## 📁 Project Structure

```
src/main/java/com/banking/banking_api/
├── controller/
│   ├── AccountController.java
│   ├── AuthController.java
│   └── TransactionController.java
├── dto/
│   ├── request/
│   │   ├── LoginRequest.java
│   │   └── RegisterRequest.java
│   └── response/
│       └── ApiResponse.java
├── entity/
│   ├── Account.java
│   ├── Transaction.java
│   ├── User.java
│   └── Role.java
├── repository/
│   ├── AccountRepository.java
│   ├── TransactionRepository.java
│   └── UserRepository.java
├── security/
│   ├── JwtAuthFilter.java
│   └── SecurityConfig.java
├── service/
│   ├── AccountService.java
│   ├── AuthService.java
│   └── TransactionService.java
└── util/
    └── JwtUtil.java
```

---

## 🔒 Security

- All endpoints except `/api/auth/**` require a valid JWT token
- Tokens expire after **24 hours**
- Passwords are encrypted using **BCrypt**
- CSRF is disabled (stateless API)

---

## 📊 Database Tables

| Table | Description |
|---|---|
| `users` | Stores registered users |
| `accounts` | Stores bank accounts |
| `transactions` | Stores all transactions |

---

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a Pull Request

---

## 📝 License

This project is open source and available under the [MIT License](LICENSE).
