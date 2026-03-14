## Parking Lot LLD / Machine Coding Problem

This project contains an implementation of the classic **Parking Lot** Low-Level Design / Machine Coding problem, often used in interviews.

The goal is to design and build a modular, extensible parking lot system that supports multiple floors, multiple parking spots per floor, and different vehicle types (e.g., bike, car, truck).

### Problem Statement (Summary)

- **Parking lot structure**
  - Multiple floors.
  - Each floor has multiple parking spots.
  - Spots can be of different types (e.g. `BIKE`, `CAR`, `TRUCK`).
  - Each spot can hold at most one vehicle at a time.

- **Vehicles**
  - Each vehicle has at least:
    - Registration number
    - Color
  - Vehicle types: Bike, Car, Truck (can be extended).

- **Core operations**
  - Create a parking lot with a configurable number of floors and spots per floor.
  - Park a vehicle:
    - Find the nearest available compatible spot (lowest floor, then lowest spot index).
    - If successful, return a ticket that uniquely identifies the spot.
  - Unpark a vehicle using a ticket:
    - Free the spot.
    - Return basic vehicle details.
  - Queries / reporting:
    - Free slot count by vehicle type.
    - List free slots by vehicle type.
    - List occupied slots by vehicle type.
    - Optionally:
      - Get registration numbers of vehicles by color.
      - Get slots for vehicles by color.
      - Get slot by registration number.

### Design Overview

The implementation is written in **Java** with a clean object-oriented design. Typical core entities:

- `ParkingLot`: Root aggregate that manages floors and parking operations.
- `Floor`: Represents a physical floor with a collection of parking spots.
- `ParkingSpot`: Represents a single parking spot, including its type and occupancy.
- `Vehicle` (and subclasses `Car`, `Bike`, `Truck`): Encapsulate vehicle details.
- `Ticket`: Represents an issued parking ticket with mapping to a specific spot.
- `ParkingStrategy` (optional): Encapsulates how the “nearest spot” is chosen.

The design aims to be:

- **Encapsulated**: Entities manage their own state and invariants.
- **Extensible**: Easy to add new vehicle/spot types or change allocation strategy.
- **Testable**: Business logic is separated from I/O.

### Project Structure

- `src/`
  - `model/`
    - Domain entities such as `ParkingLot`, `Floor`, `ParkingSpot`, `Vehicle`, `Ticket`, etc.
  - `service/`
    - Parking, allocation, and query services (if you choose to separate them).
  - `main/` or an entry-point class
    - CLI or simple runner to simulate operations.

*(The exact structure may evolve as you build out the solution.)*

### How to Run (Java)

1. **Prerequisites**
   - Java 8+ installed (`java -version` to verify).
   - A build tool such as Maven or Gradle (optional, if you decide to use one).

2. **Compile**

   From the project root:

   ```bash
   javac -d out $(find src -name "*.java")
   ```

3. **Run**

   Assuming your main class is `Main` in the default package or `main` package:

   ```bash
   java -cp out Main
   ```

   or, if it is inside a package:

   ```bash
   java -cp out your.package.name.Main
   ```

Adjust the command according to your actual package and main class.

### Extending the Solution

Possible extensions to try:

- Add pricing/billing based on parking duration and vehicle type.
- Support reservations or different parking strategies.
- Persist state (e.g., to a database or file).
- Expose a REST API instead of a CLI.

### Concurrency & Consistency (Interview Notes)

Some typical follow-up questions and how this implementation approaches them:

- **How do you avoid two vehicles getting the same spot at the same time?**  
  - `ParkingSpot` uses per-spot locking via `synchronized` methods (`tryOccupy` / `vacate`).  
  - The `tryOccupy` method makes the "check-free + occupy" operation atomic per spot.  
  - If two threads race for the same spot, one succeeds, the other sees the spot as no longer available and fails (returns `Optional.empty()` from `park`).

- **How do you make ticket management thread-safe?**  
  - `ticketToSpot` is a `ConcurrentHashMap`, so concurrent `put`/`remove`/`get` operations from multiple threads are safe.  
  - Combined with per-spot locking, this prevents both structural corruption of the map and double allocation of spots.

- **What about multiple pods / processes?**  
  - The current design assumes a single JVM (single process).  
  - `synchronized` and `ConcurrentHashMap` only protect concurrency *within* that process.  
  - In a real distributed deployment, you would move the critical sections to a shared, strongly consistent layer (e.g. database row locks or a distributed lock keyed by spot id).

### Failure Scenarios (Interview Notes)

- **What if `unpark` is called with a valid ticket but the spot is already free?**  
  - That indicates inconsistent state (e.g. previous bug or manual tampering).  
  - A robust implementation would detect this (ticket exists but `spot.isAvailable()` is already `true`), treat it as an error or "already unparked", and log it for investigation instead of silently succeeding.

- **How do you handle partial failures (spot vacated but ticket not removed, or vice versa)?**  
  - In this in-memory version, `unpark` updates spot and ticket map together in one method.  
  - In a real system, both updates would be part of a single database transaction (update spot row + ticket row), so either both succeed or both roll back.  
  - If any inconsistency is detected at runtime, the system would surface an error and log it rather than proceeding with a half-applied change.

### Notes

This repository is intended primarily for **practice and interview preparation**. Focus on:

- Clean, readable code.
- Solid class design and separation of concerns.
- Handling edge cases (invalid tickets, full lot, etc.).

