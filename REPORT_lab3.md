# TMPS Lab 3 - Behavioral Design Patterns
## Author: Andrei Cernisov, FAF-222



---

## Objectives:

* Study and understand the Behavioral Design Patterns
*  As a continuation of the previous laboratory work, think about what communication between software entities might be involed in my system
* Implement some additional functionalities using behavioral design patterns

## Introduction

Behavioral Design Patterns are concerned with the interaction between objects and the responsibilities they share. They help in mangaing communication between classes and objects and help ensure reusability and flexibility

In this lab, I ipmlemented: Memento, Observer, and State patterns. Each pattern addresses a specific behavioral aspect:
- Memento allows to save and restore state of an object
- Observer is useful for communication between objects, specifically for reacting to changes in state
- State manages an object's behaviour based on its internal state

## Implementation & Explanation

The food ordering system from previous labs was improved by the following behavioural patterns:

### 1. Memento Pattern

#### Main Idea:
The Memento pattern captures and externalizes an object's internal state so that it can be restored. It also helps avoid breaking encapsulation, as you keep private fields private

#### Why Use It:
The project needed ability to undo changes to order details. For example if a user adds an item by mistake, they should be able to revert back to previous state without starting the entire order from beginning

#### Implementation:
The OrderDetailsBuilder class generates and restores mementos that contain the current state of the order. The Caretaker class stores these mementos in a stack and allows us to pop the latest change if user wants to undo something

#### Code Snippet (Caretaker.kt):

```kotlin
package domain.memento

import java.util.Stack

class Caretaker {
    private val mementos = Stack<OrderDetailsMemento>()

    fun saveState(memento: OrderDetailsMemento) {
        mementos.push(memento)
    }

    fun undo(): OrderDetailsMemento? {
        return if (mementos.size > 1) {
            mementos.pop()
            mementos.peek()
        } else {
            null
        }
    }
}
```

#### Location:
The code is in domain/memento/Caretaker.kt and is utilized in the client file to allow users to undo their actions when they are building their order

---

### 2. Observer Pattern

#### Main Idea:
The Observer pattern defines a one-to-many dependency where one object (the subject) notifies multiple dependent objects (observers) about state changes

#### Why Use It:
It could be beneficial for the project if it's extended to work with several clients, as each of them could be notified about changes to inventory and therefore it could update it's menu accordingly as soon as the inventory changes. Another usecase for inventory observer is that Inventory could be monitored to notify the administrator of the order management system about stock running low

#### Implementation:
The Inventory class acts as subject and the InventoryDisplay class is its observer. Whenever the inventory is updated, all the attached observers are notified

#### Code Snippet (InventoryDisplay.kt):

```kotlin
package domain.inventory

import domain.observer.Observer
import domain.observer.Subject

class InventoryDisplay : Observer {
    override fun update(subject: Subject) {
        if (subject is Inventory) {
            println("Inventory has been updated:")
            println("----------")
            subject.showInventory()
            println("----------")
        }
    }
}
```

#### Location:
The InventoryDisplay observer is in  domain/inventory/InventoryDisplay.kt.  It's registered with the Inventory subject in client

---

### 3. State Pattern

#### Main Idea:
State pattern allows an object to alter its behavior when its internal state changes. This is achieved by delegating behavior to state specific objects

#### Why Use It:
The ordering system required multiple states to represent lifecycle of an order: placed, prepared, delivered, canceled, etc. Each state has specific rules for moving on to the next state

#### Implementation:
The `Order` class maintains a reference to its current `OrderState`. The state determines what happens when the order transitions to the next stage or is canceled.

#### Code Snippet (OrderPlacedState.kt):

```kotlin
package domain.order.state

import domain.order.Order

class OrderPlacedState : OrderState {
    override fun nextState(order: Order) {
        println("Order is now being prepared")
        order.state = OrderPreparedState() // each concrete state defines transition to the next state
    }

    override fun cancelOrder(order: Order) {
        println("Order has been canceled.")
        order.state = OrderCanceledState() 
    }

    override fun getStateName(): String = "Order Placed"
}
```

#### Location:
State impelmentations are located in domain/order/state folder. The Order class from domain/order/Order.kt manages current state and then delegates behavior to the state objects

---

## Results

With addition of behavioral patterns:
- Users can undo their actions when building order (Memento)
- Inventory system gained ability to dynamicaly user interface when changes occur (Observer patern)
- Order preparation process is now being maanaged by State pattern, ensuring valid transitions between states and defining a clear logic

