# 💰 Certified Time Deposit with TigerBeetle

This project implements a REST service for the lifecycle of a **Certified Time Deposit (CTD)** using [TigerBeetle](https://github.com/tigerbeetle/tigerbeetle), a high-performance financial ledger designed for distributed systems. It demonstrates how to model fixed-term deposit products in a double-entry accounting environment.

---

## 📘 What is a Time Deposit?

A **Time Deposit** (or **CTD – Certified Time Deposit**) is a financial product where a customer commits to locking funds for a predefined duration — the **maturity period** — in exchange for a **guaranteed, higher interest rate**. During the term:

- **Funds are non-withdrawable** until maturity.
- **Interest rates are fixed** and higher than those offered by regular savings accounts.
- **Deposits are only accepted** during a limited **deposit period**.

> ⚠️ **Important:** Only **positive interest rates** are supported for Time Deposit accounts. Negative rates are not allowed.

---

## 🧩 Supported Features

This implementation currently supports the following stages in the CDT lifecycle:

| Feature            | Description                                                                 |
|--------------------|-----------------------------------------------------------------------------|
| ✅ Account Opening  | Customer account is created with specified term and ledger ID              |
| ✅ Deposit Period   | Funds can be deposited during a configurable initial time window           |
| ✅ Term Period      | The holding phase during which funds remain locked                         |
| ✅ Maturity Date    | The product reaches maturity and becomes eligible for renewal or withdrawal |

---

## 🚧 Coming Soon

Planned features for upcoming releases:

| Feature             | Description                                                                 |
|---------------------|-----------------------------------------------------------------------------|
| ⏳ Cooling-Off Period | Allows early cancellation within a short window after account creation     |
| 🛡️ Grace Period       | Buffer period after renewal to modify or cancel the renewed deposit        |
| 🔁 Renewal            | Option to automatically or manually roll over the deposit at maturity      |
| ❌ Closure            | Final disbursement of principal and interest, and account termination       |

---

## 🛠️ Technology Stack

Here’s the **updated Tech Stack** section, now including **Spring Boot Modulith**, which aligns well with Clean Architecture and modular DDD practices:

---

## 🧰 Tech Stack

| Layer               | Technology / Tool                                                                           | Purpose                                                               |
| ------------------- | ------------------------------------------------------------------------------------------- | --------------------------------------------------------------------- |
| **Ledger Engine**   | [TigerBeetle](https://github.com/tigerbeetle/tigerbeetle)                                   | High-performance financial ledger with double-entry accounting        |
| **Language**        | Java 17                                                                                     | Core programming language                                             |
| **Build Tool**      | Maven                                                                                       | Dependency management and build automation                            |
| **Architecture**    | Clean Architecture + Hexagonal + Spring Modulith                                            | Enforces modular boundaries and decoupled service components          |
| **Framework**       | [Spring Boot Modulith](https://docs.spring.io/spring-modulith/docs/current/reference/html/) | Modular monolith framework for scalable, loosely coupled applications |
| **Domain Modeling** | Domain-Driven Design (DDD) principles                                                       | Business logic encapsulation, aggregates, and domain events           |
| **Interface Layer** | CLI (current) / Spring REST (planned)                                                       | User interaction and external integration                             |
| **Event Handling**  | Spring Application Events + Domain Events                                                   | Lifecycle triggers (e.g., maturity, renewal)                          |
| **Testing**         | JUnit 5, Spring Modulith `@ApplicationModuleTest`                                           | Per-module testing and integration validation                         |
| **Time Control**    | Java `Clock`, injectable                                                                    | Enables testing of time-based flows (e.g., deposit period, maturity)  |
| **ID Generation**   | Java `UUID`                                                                                 | Unique identifiers for accounts and transfers                         |
| **Serialization**   | ByteBuffer (TigerBeetle client)                                                             | Low-level memory interaction with the native ledger engine            |

---

## 🚀 Getting Started

1. **Run TigerBeetle locally**
   - Follow the instructions in the [official repository](https://github.com/tigerbeetle/tigerbeetle) to start a local instance on port `3000`.

2. **Build the project**
   ```bash
   mvn clean package
   ```

3. **Run the service**

   ```bash
   java -jar target/*.jar
   ```

4. **Use the embedded [HAL Explorer](https://github.com/toedter/hal-explorer)** 
[Click here](http://localhost:8080/explorer/index.html#uri=/) or run this command:
   ```
   open http://localhost:8080/explorer/index.html#uri=/
   ```
5. **Observability**
The project uses Spring Modulith's observability features to inspect the runtime interaction between the logical modules. To use and see this, run the application with the observability Maven profile enabled:
   ```
   $ mvn spring-boot:run -Pobservability
   ```
   That profile adds some dependencies, like Spring Cloud Sleuth as well as its integration with Zipkin. It also includes docker-compose support and will start Zipkin for you when the application starts. Interactions with the system will now expose the logical module invocation and their choreography.

---

## 🧱 Architecture Notes

This module follows Clean Architecture principles, aligned with **Spring Modulith** practices to ensure a modular and maintainable structure. The root path is:

```
src/main/java/com/bank/banking/deposit/ctdaccountservice
```

---

## 📁 Folder Structure

```plaintext
src/
├── domain/          ← Domain entities (e.g., `Account`, `Product`) and Output port interfaces (e.g., `AccountRepository`)
├── usecase/         ← Application services / Use cases (e.g., `CreateAccountUseCase`) and Commands (e.g., `CreateAccountCommand`)
├── infrastructure/  ← REST controllers, DTOs (e.g., `CreateAccountRequest`), JPA entities, Repository adapters, and Mappers
└── CtdAccountService.java ← Spring Boot main application
```

> Avoid creating nested folders within each package to keep the structure flat and easy to navigate.

---

## 🔄 Dependency Flow

```plaintext
domain ← usecase ← infrastructure
```

* `usecase` depends on `domain`
* `infrastructure` depends on `usecase`
* Controllers handle DTOs and commands only — not domain entities

---

## 🧩 Spring Modulith Guidelines

Apply **Spring Modulith** by treating each top-level package as a self-contained module.

* **@ApplicationModule**
  Declare module boundaries explicitly at the root of each logical unit (e.g., `usecase`, `domain`).

* **@NamedInterface**
  Use to expose only necessary classes (e.g., mappers, shared DTOs) between modules.

* **Event-Driven Design**
  Use domain events (e.g., `AccountCreatedEvent`) instead of direct calls between modules.

* **Testing**
  Apply `@ApplicationModuleTest` to validate module boundaries and encapsulation.

* **Visibility Rules**
  Keep domain classes package-private to avoid accidental usage from controllers or adapters.

---

## 🤝 Contributions

Contributions and feature suggestions are welcome! Please open an issue or submit a pull request.
