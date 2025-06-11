# Certified Time Deposit (CTD) Account

## 🧱 Domain Model

### Entity: `CtdAccount`
Represents a certified time deposit account.

| Field       | Type      | Description                                 |
|-------------|-----------|---------------------------------------------|
| `id`        | UUID      | Unique identifier for the CTD account        |
| `alias`     | String    | Human-readable identifier (e.g., "My Savings") |
| `status`    | Enum      | Current lifecycle status of the account     |
| `createdAt` | DateTime  | Timestamp when the account was created      |
| `updatedAt` | DateTime  | Last modification timestamp                 |

#### `CtdAccountStatus` Enum
Defines the possible lifecycle states of a CTD account:
- `PENDING_CREATION`
- `ACTIVE`
- `LOCKED` (deposit period ended)
- `WITHDRAWN`
- `CLOSED`
- `CANCELLED`

---

## 🧠 Use Cases (Application Layer)

### ✅ Create CTD Accoun
### 💰 Deposit into CTD Account
### 💸 Withdraw from CTD Account
### ❌ Close CTD Account
### ✏️ Update Alias or Metadata
### 🔍 Find by ID
### 🔍 Find by Alias
### 📃 List All Accounts

---

## 🔐 Domain Rules and Invariants

* Accounts can only be deposited into during the **deposit window**.
* Once **locked**, withdrawals are subject to maturity conditions.
* An account cannot be closed until all funds are withdrawn.
* Alias must be unique per customer.

---

## ✅ Example: Lifecycle Flow

```plaintext
CREATE --> ACTIVE --> LOCKED --> WITHDRAWN --> CLOSED
           |                             |
           └--> CANCELLED                └--> CLOSED
```
