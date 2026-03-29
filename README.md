# 🏯 313 Business Points — Master Backend Testing Guide
> **Made with 🦾 by [amigo.d.cyber](https://github.com/amigoDcyber)**  🛡️🚀

This is the official testing and setup guide for the **313 Business Points** Remittance Platform. It is designed for GitHub collaborators and team members to set up, authenticate, and test the core backend engine.

---

## 🏗 1. Local Environment Setup
Before you start, ensure your local machine has the following services running. **Redis is required for authentication!**

| Service | Host | Port | Purpose |
| :--- | :--- | :--- | :--- |
| **PostgreSQL** | `localhost` | `5432` | Main Database |
| **Redis** | `localhost` | `6379` | Sessions, OTP, & Security Blacklisting |

---

### 🍎 how to Install Redis (By Platform)

#### **Windows**
*   **Recommended**: Install **WSL2** (Linux Subsystem), then run `sudo apt install redis-server`.
*   **Alternative**: Use **Docker** to run Redis: `docker run -p 6379:6379 -d redis`.
*   **Manual**: Download the latest MSI from [Redis for Windows](https://github.com/microsoftarchive/redis/releases).

#### **macOS**
*   Open your terminal and use **Homebrew**:
    ```bash
    brew install redis
    brew services start redis
    ```

#### **Linux (Ubuntu/Debian)**
*   Run the following commands:
    ```bash
    sudo apt update
    sudo apt install redis-server
    sudo systemctl start redis
    ```

---

### ☕ Step 0: How to Run the Backend (By Platform)
Before testing, make sure you have **Java 17+** and **Maven** installed.

#### **Windows (Command Prompt / Powershell)**
```cmd
mvnw.cmd spring-boot:run
```
*(If you don't have Maven installed globally, use the included wrapper).*

#### **macOS / Linux**
```bash
./mvnw spring-boot:run
```

---

### 💾 Database Access:
*   **Database Name**: `remit313`
*   **Username**: `apple`
*   **Password**: `backend`

---

## 🔑 2. Testing Credentials
All test accounts currently use the **same password** for simplicity during testing.

| Role | Email | Password |
| :--- | :--- | :--- |
| **Customer** | `user@313.com` | `password123` |
| **Admin/Compliance** | `admin@313.com` | `password123` |

---

## 🛠 3. Total Testing Walkthrough (JSON Bodies)

### 1. Login (The User)
*   **Endpoint**: `POST {{base_url}}/auth/login`
*   **Body**:
```json
{
  "email": "user@313.com",
  "password": "password123",
  "deviceId": "MY_KONSOLE"
}
```

---

### 2. Trust the Device
*   **Endpoint**: `PATCH {{base_url}}/devices/{{device_uuid}}/trust`
*   **Auth**: Bearer Token
*   **Body**: (None)

---

### 3. Submit KYC (Customer)
*   **Endpoint**: `POST {{base_url}}/kyc/user/85f6aba9-e641-4202-9991-3833470b220d`
*   **Auth**: Bearer Token
*   **Body**:
```json
{
  "idType": "NATIONAL_ID",
  "idNumber": "11998800112233",
  "idDocumentUrl": "http://cloud.com/my-id.jpg",
  "selfieUrl": "http://cloud.com/my-selfie.jpg",
  "proofOfAddressUrl": "http://cloud.com/my-bill.jpg",
  "nationality": "RWANDAN",
  "occupation": "Software Engineer"
}
```

---

### 4. Admin Login
*   **Endpoint**: `POST {{base_url}}/auth/login`
*   **Body**:
```json
{
  "email": "admin@313.com",
  "password": "password123",
  "deviceId": "ADMIN_STATION"
}
```

---

### 5. Approve KYC (Admin)
*   **Endpoint**: `PATCH {{base_url}}/kyc/{{kyc_uuid}}/review?status=VERIFIED`
*   **Auth**: Admin Bearer Token
*   **Body**: (None - uses Query Param `status=VERIFIED`)

---

### 6. Create Quote
*   **Endpoint**: `POST {{base_url}}/quotes`
*   **Auth**: Bearer Token
*   **Body**:
```json
{
  "corridorId": "375d74d8-b972-483c-914a-1934714df02b",
  "sendCurrency": "RWF",
  "receiveCurrency": "KES",
  "amountSent": 10000.0
}
```

---

### 7. Add Recipient
*   **Endpoint**: `POST {{base_url}}/recipients`
*   **Auth**: Bearer Token
*   **Body**:
```json
{
  "fullName": "John Kenya",
  "phone": "+254700000000",
  "country": "KENYA",
  "deliveryMethod": "MOBILE_MONEY",
  "networkOperator": "Safaricom"
}
```

---

### 8. Initiate Transfer
*   **Endpoint**: `POST {{base_url}}/transfers`
*   **Auth**: Bearer Token
*   **Body**:
```json
{
  "quoteId": "QUOTE_ID_FROM_STEP_6",
  "recipientId": "RECIPIENT_ID_FROM_STEP_7",
  "transferReason": "Family Support"
}
```

---

## 🎨 4. Insomnia / Postman Tips
*   **Environment Variables**: Create an environment in Insomnia with a variable named `base_url` set to `http://localhost:8080/api/v1`.
*   **Bearer Auth**: Almost every endpoint (except login/signup) requires `Bearer Token`. Paste your token in the **Auth** tab.
*   **Redis Check**: If you get a `403 Forbidden` for everything, make sure **Redis** is running!

---

## 🧹 5. Resetting for a Fresh Start (Master Reset)
If your database gets messy or you want to start the tests from scratch, follow these three steps:

### Step 1: Wipe Database & Redis
Stop the Spring Boot app and run these:
```bash
# Delete and recreate the database
sudo -u postgres dropdb remit313 && sudo -u postgres createdb remit313 -O apple

# Clear the Redis security cache
redis-cli flushall
```

### Step 2: Rebuild Schema
Start the backend to let Flyway recreate the tables:
```bash
mvn spring-boot:run
```

### Step 3: Seed the Test Accounts
Run this SQL block to restore the standard test users and corridors:
```bash
PGPASSWORD=backend psql -U apple -h localhost -d remit313 -c "
INSERT INTO users (id, first_name, last_name, email, password_hash, status) VALUES 
('85f6aba9-e641-4202-9991-3833470b220d', 'Test', 'User', 'user@313.com', '\$2a\$12\$8u9Ygg1jBqcgBmJGPevfJe0nrkAlVBJvw23D1dyuJ6SDI4Njmd2ui', 'ROLE_USER'),
('b416f47a-8b6f-45c2-9205-4279a110518b', 'System', 'Admin', 'admin@313.com', '\$2a\$12\$8u9Ygg1jBqcgBmJGPevfJe0nrkAlVBJvw23D1dyuJ6SDI4Njmd2ui', 'ROLE_ADMIN');

INSERT INTO corridors (id, from_country, to_country, from_currency, to_currency, delivery_method, min_amount, max_amount, is_active) VALUES 
('375d74d8-b972-483c-914a-1934714df02b', 'RWANDA', 'KENYA', 'RWF', 'KES', 'MOBILE_MONEY', 1000.00, 1000000.00, true),
('3277cf39-84f5-4fd4-af92-20f951604ae4', 'RWANDA', 'UGANDA', 'RWF', 'UGX', 'BANK_TRANSFER', 1000.00, 1000000.00, true);
"
```

---
**Happy Testing, Team!** 🚀💻🛡️
