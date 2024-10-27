# TMPS lab 1 - Creational Design Patterns 
**Author:** Andrei Cernisov, FAF-222

---

## Introduction

This project demonstrates the use of **Creational Design Patterns** to manage object creation. Creational patterns are used to simplify object creation, improve flexibility and enhance code organization, especially in scenarios with complex object structures. Here, I've implemented the follwing 3 patterns:

- **Singleton**
- **Builder**
- **Factory**

As the theme for the project I picked an order management system for a restaurant.

## Project structure

The project is organized into packages based on functionality and responsibility:

- **client**: Contains the main execution file, `Main.kt`, which demonstrates the order creation and processing workflow.
- **domain**: Holds the main business logic of the application, organized into the following sub-packages:
    - **factory**: Contains the `OrderFactory`, responsible for creating specific order types based on input.
    - **inventory**: Manages the stock and availability of items, implemented as a Singleton.
    - **models**: Contains `OrderDetails` and `OrderDetailsBuilder`, which represent order specifications and facilitate flexible order creation.
    - **order**: Defines different order types (`DineInOrder`, `TakeAwayOrder`, `DeliveryOrder`) and the base `Order` class, which outlines the steps for processing orders.

## Implemented design patterns

### Singleton pattern

The **Singleton Pattern** ensures a class has only one instance while providing a global access point to that instance. In this project, `Inventory` is implemented as a Singleton using Kotlin’s `object` declaration. This design is suitable here because `Inventory` needs a single, consistent point of access to manage item availability across all orders.

- **Code**:
  ```kotlin
  package domain.inventory

  object Inventory {
      private val items = mutableMapOf(
          "Pizza" to 10,
          "Burger" to 15,
          "Salad" to 20,
          "Coke" to 30,
          "Water" to 50,
          "Ice Cream" to 25,
          "Fries" to 40
      )
      
      // ...
  }
  ```
- **Benefits**:
    - Ensures consistency in inventory levels across different parts of the program.
    - Reduces the need for multiple instances of `Inventory`, avoiding data duplication or inconsistency.

### Builder pattern

The **Builder Pattern** is used to construct a complex object with optional parameters in a readable and manageable way. In this project, `OrderDetailsBuilder` builds an instance of `OrderDetails` with various optional fields, such as main course, drink, dessert, and extras.

- **Code**:
  ```kotlin
  package domain.models

  class OrderDetailsBuilder(private val orderType: String) {
      private var mainCourse: String? = null
      private var drink: String? = null
      private var dessert: String? = null
      private val extras = mutableListOf<String>()

      fun setMainCourse(mainCourse: String) = apply { this.mainCourse = mainCourse }
      fun setDrink(drink: String) = apply { this.drink = drink }
      fun setDessert(dessert: String) = apply { this.dessert = dessert }
      fun addExtra(extra: String) = apply { this.extras.add(extra) }

      fun build(): OrderDetails {
          return OrderDetails(orderType, mainCourse, drink, dessert, extras)
      }
  }
  ```
- **Benefits**:
    - Simplifies object construction for `OrderDetails` by allowing the addition of only necessary attributes.
    - Reduces the need for multiple constructors in `OrderDetails`.

### Factory pattern

The Factory Pattern provides a way to delegate the instantiation of objects to subclasses. In this project, `OrderFactory` is responsible for creating instances of different order types (`DineInOrder`, `TakeAwayOrder`, `DeliveryOrder`) based on the `orderType` attribute in `OrderDetails`.

- **Code**:
  ```kotlin
  package domain.factory

  import domain.models.OrderDetails
  import domain.order.DeliveryOrder
  import domain.order.DineInOrder
  import domain.order.Order
  import domain.order.TakeAwayOrder

  class OrderFactory {
      fun createOrder(orderDetails: OrderDetails): Order {
          return when (orderDetails.orderType.lowercase()) {
              "dinein" -> DineInOrder(orderDetails)
              "takeaway" -> TakeAwayOrder(orderDetails)
              "delivery" -> DeliveryOrder(orderDetails)
              else -> throw IllegalArgumentException("Unknown order type.")
          }
      }
  }
  ```
- **Benefits**:
    - Decouples the order creation logic from the `Order` class hierarchy.
    - Makes it easy to add new types of orders in the future by extending `Order` and updating `OrderFactory`.

## Usage Example

Here, in `Main.kt`, the client side of these patterns is demonstrated:
```kotlin
fun main() {
    Inventory.showInventory()

    // builing the details for an order using OrderDetailsBuilder
    val dineInOrderDetails = OrderDetailsBuilder("dinein")
        .setMainCourse("Pizza")
        .setDrink("Coke")
        .setDessert("Cheesecake")
        .addExtra("Fries")
        .build()

    //  creating an order from order details using OrderFactory
    val orderFactory = OrderFactory()
    val dineInOrder = orderFactory.createOrder(dineInOrderDetails)

    dineInOrder.processOrder()

    Inventory.showInventory()
}
```

1. **Singleton Pattern (Inventory Management)**
  - The `Inventory` object, implemented as a singleton, manages item availability across the system. By calling `Inventory.showInventory()` at the start and end, we can view and update a single, consistent set of inventory values that all orders interact with.
  - This singleton approach ensures that all parts of the system reference the same `Inventory` instance, helping prevent discrepancies and enabling centralized management of stock levels.

2. **Builder Pattern (Order Details Construction)**
  - The `OrderDetailsBuilder` is used to build the order specifications (`OrderDetails`) in a clear and readable way, allowing optional fields (main course, drink, dessert, extras) to be set based on the order type.
  - In this example, `OrderDetailsBuilder("dinein")` is used to define a Dine-In order with `Pizza`, `Coke`, `Cheesecake`, and `Fries`. This pattern simplifies the construction of `OrderDetails` by allowing only relevant fields to be added, avoiding the need for multiple constructors in the `OrderDetails` class.

3. **Factory Method Pattern (Order Creation)**
  - The `OrderFactory` uses the Factory Method pattern to determine the appropriate type of order (`DineInOrder`, `TakeAwayOrder`, or `DeliveryOrder`) based on the `orderType` in `OrderDetails`.
  - By calling `orderFactory.createOrder(dineInOrderDetails)`, the system dynamically creates a `DineInOrder` object based on the provided order details, hiding the instantiation logic and making it easy to add new order types in the future without altering existing code.

## Conclusion

This project successfully demonstrates three creational design patterns:

- **Singleton** for managing inventory as a single instance.
- **Builder** for flexible construction of `OrderDetails`.
- **Factory Method** for creating specific order types based on order attributes.

By implementing these patterns, the project maintains a well-organized and flexible code structure, making it easy to extend functionality or modify object creation logic as needed. These patterns help control object instantiation complexity, which aligns with the core principles of creational design patterns.