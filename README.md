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

This project is being designed using **Clean Architecture** principles, enriched with **modular boundaries**, **use-case-driven design**, and **event-driven communication**.

### Core Architectural Layers

* **Domain Layer**
  Defines the core business model and rules (e.g., `Account`, `InterestPolicy`, `MaturitySchedule`). This layer is **pure Java**, without dependencies on frameworks or external libraries. It includes:

  * Value Objects and Entities
  * Domain Services
  * Domain Events (e.g., `AccountMatured`, `DepositCreated`)

* **Application Layer**
  Orchestrates use cases like `OpenAccount`, `MakeDeposit`, `ApplyMaturity`. It coordinates between the domain and infrastructure layers and is responsible for:

  * Input validation
  * Transaction boundaries
  * Publishing domain events
  * Use case orchestration without containing business logic itself

* **Infrastructure Layer**
  Provides technical capabilities to support the domain and application layers. It includes:

  * Adapter for **TigerBeetle Java client**
  * Persistence (if needed for internal states or metadata)
  * Time services, UUID generators, and serialization
  * Event publishing or logging infrastructure

* **Interface Layer** *(Planned)*
  The outermost layer intended for interacting with the application via:

  * CLI interface (initial)
  * REST API (future)
  * Input DTOs and output views for external consumers

* **Modular Boundaries**
  The system is structured around **business capabilities**, separating concerns cleanly into vertical modules (e.g., `accounts`, `deposits`, `maturity`, `renewals`) which each contain their own domain, application logic, and adapters.

* **Domain Events & Event-Driven Flow**
  Domain events like `DepositPeriodClosed` or `AccountRenewed` are used to trigger follow-up processes decoupled from the original command, promoting extensibility and separation of concerns.

* **Aggregate Roots and Invariants**
  Aggregates enforce consistency boundaries, e.g., `Account` as the root of a CDT lifecycle. Invariants like "no deposits after deposit period ends" are enforced within the aggregate.

* **Typed Use Cases**
  Use cases are modeled explicitly, with clear input/output models, avoiding "anemic services." Each use case is isolated and testable in complete separation from infrastructure.

* **Port and Adapter Pattern**
  All dependencies point inward. Interfaces (Ports) are defined in the domain or application layers, while implementations (Adapters) live in the infrastructure layer.

* **Time as a First-Class Concern**
  The concept of time (e.g., for deposit periods, maturity, grace windows) is handled explicitly via injected clock providers, enabling testability and simulation.

---

## 🤝 Contributions

Contributions and feature suggestions are welcome! Please open an issue or submit a pull request.
