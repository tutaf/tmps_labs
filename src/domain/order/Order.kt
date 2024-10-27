package domain.order

import domain.models.OrderDetails
import domain.inventory.Inventory

abstract class Order(private val orderDetails: OrderDetails) {
    abstract fun processOrder()

    protected fun processOrderItems() {
        println("Order Details:")
        orderDetails.mainCourse?.let {
            if (Inventory.isItemAvailable(it)) {
                Inventory.reduceItemQuantity(it)
                println("Main Course: $it")
            } else {
                println("Main Course: $it - not available")
            }
        }
        orderDetails.drink?.let {
            if (Inventory.isItemAvailable(it)) {
                Inventory.reduceItemQuantity(it)
                println("Drink: $it")
            } else {
                println("Drink: $it - not available")
            }
        }
        orderDetails.dessert?.let {
            if (Inventory.isItemAvailable(it)) {
                Inventory.reduceItemQuantity(it)
                println("Dessert: $it")
            } else {
                println("Dessert: $it - not available")
            }
        }
        if (orderDetails.extras.isNotEmpty()) {
            println("Extras:")
            orderDetails.extras.forEach { extra ->
                if (Inventory.isItemAvailable(extra)) {
                    Inventory.reduceItemQuantity(extra)
                    println(" - $extra")
                } else {
                    println(" - $extra - not available")
                }
            }
        }
    }
}