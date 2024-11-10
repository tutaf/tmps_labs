# TMPS lab 2 - Structural Design Patterns

## Author: Andrei Cernisov, FAF-222

---

## Objectives:

* Understand and study Structural Design Patterns
* Extend the project from lab1 using structural patterns
* Use 3 structural pattenrs in the project

## Introduction

Structural Design Patterns are used to organize relationships between objects and classes to form more complex structures, making code easier to manitain. Instead of focusing on creating objects, structural patterns emphasize ways of composing them into useful structures that work efficiently together.

In this project, I implemented: **Facade**, **Decorator**, and **Composite**. Each pattern here serves a specific purpose:
- **Facade** simplifies how the main system interacts with complex components
- **Decorator** extends functionality dynamically, like adding a discount to an order.
- **Composite** helps organize individual items and groups of items (like meal combos) in a way that treats them uniformly

## Implementation & Explanation

I've expanded the food ordering system from previous lab, adding the follwing 3 patterns:

### 1. Facade Pattern

#### Main Idea:
The Facade pattern provides a single, unified interface to a set of interfaces in a subsystem. It simplifies the interaction with the system by hiding the complexity of different components and allows the client to interact with the system in a straightforward way

#### Why Use It:
In my project placing an odrer involves several steps: checking the inventory, creating specific order types (Dine-In, Takeaway, Delivery). Instead of making the client code handle all these steps directly, I create an OrderFacade that handles them

#### Implementation:
The OrderFacade class integrates the OrderFactory and Inventory components to streamline order creation and validation. The client can now just call `placeOrder` on the OrderFacade, which will handle inventory checks and order processing in the background

#### Code Snippet (OrderFacade.kt):

```kotlin
package domain.facade

import domain.factory.OrderFactory
import domain.inventory.Inventory
import domain.models.OrderDetails
import domain.order.Order

class OrderFacade {
    private val orderFactory = OrderFactory()

    fun placeOrder(orderDetails: OrderDetails): Order? {
        if (!Inventory.isItemAvailable(orderDetails.mainCourse ?: "")) {
            println("Main course is not available.")
            return null
        }
        val order = orderFactory.createOrder(orderDetails)
        order.processOrder()
        return order
    }
}
```

#### Location:
The code is in domain/facade/OrderFacade.kt. This pattern centralizes the order creation and simplifies the client code from client/Main.kt

### 2. Decorator Pattern

#### Main Idea:
 Decorator pattern allows behavior to be added to individual objects, either statically or dynamically, without affecting other objects of the same class. It’s useful for adding optional features to objects, like, in this case, applying a discount to a specific order

#### Why Use It:
It allows client to apply a discount without modifying the base Order classes. I implemented DiscountedOrder as a decorator - this approach means we can apply a discount by wrapping an order in a DiscountedOrder decorator instead of modifying each Order subclass (DineInOrder, TakeAwayOrder, etc.). Also, other decorators can be added to the system in the future and applied (together or individually), without disrupting the work fo the system

#### Implementation:
DiscountedOrder class extends OrderDecorator which is itself a subclass of Odrer. The decorator intercepts the `printOrderTotal` method and applies a 10 procent discount to the order's total

#### Code Snippet (DiscountedOrder.kt):

```kotlin
package domain.order

class DiscountedOrder(decoratedOrder: Order) : OrderDecorator(decoratedOrder) {

    override fun printOrderTotal() {
        super.printOrderTotal()

        val originalTotal = getOriginalOrderTotal()
        val discountAmount = originalTotal * 0.1
        val discountedTotal = originalTotal - discountAmount

        println("Price with discount applied: $${String.format("%.2f", discountedTotal)}\n---")
    }
}
```

#### Location:
The code is in domain/order/DiscountedOrder.kt and domain/order/OrderDecorator.kt. This decorator pattern can be seen in the client/Main.kt file, where discnout is applied based on user input

### 3. Composite Pattern

#### Main Idea:
The Composite pattern is used to treat individual objects and groups of objects uniformly. It allows us to create a tree structure where each node can be an individual item or a group of items

#### Why Use It:
In the ordering system, combo meals consist of several items grouped together, like a burger combo that includes a burger, fries, and a drink. With the Composite pattern, I can treat this combo as a single ComboItem object, while still maintaining the ability to get prices and adjust quantities of the individual items

#### Implementation:
The ComboItem class is a composite of individual InventoryItem objects. This pattern allows us to handle both individual items and combos uniformly within Inventory system, making it simpler to calculate total prices and manage stock

#### Code Snippet (ComboItem.kt):

```kotlin
package domain.inventory

class ComboItem(override val name: String) : ItemComponent() {
    private val items = mutableListOf<ItemComponent>()

    override fun addItem(item: ItemComponent) {
        items.add(item)
    }

    override fun getPrice(): Double {
        return items.sumOf { it.getPrice() } * 1.0
    }
}
```

#### Location:
The code is in domain/inventory/ComboItem.kt. The `Inventory` class uses this pattern to handle combo items and their individual components, making it possible to handle stock and pricing for combos as a unit

## Results 

The project now has combo meals and order discounts, and order creation details are hidden from client to make order creation more simple