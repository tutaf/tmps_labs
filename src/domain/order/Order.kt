package domain.order

import domain.models.OrderDetails
import domain.inventory.Inventory

abstract class Order(internal val orderDetails: OrderDetails) {
    protected var orderTotal: Double = 0.0

    abstract fun processOrder()

    protected fun processOrderItems() {
        orderTotal = 0.0
        println("Order Details:")
        orderDetails.mainCourse?.let {
            if (Inventory.isItemAvailable(it)) {
                Inventory.extractItem(it)?.let { itemPrice ->
                    orderTotal += itemPrice
                }
                println("Main Course: $it")
            } else {
                println("Main Course: $it - not available")
            }
        }
        orderDetails.drink?.let {
            if (Inventory.isItemAvailable(it)) {
                Inventory.extractItem(it)?.let { itemPrice ->
                    orderTotal += itemPrice
                }
                println("Drink: $it")
            } else {
                println("Drink: $it - not available")
            }
        }
        orderDetails.dessert?.let {
            if (Inventory.isItemAvailable(it)) {
                Inventory.extractItem(it)?.let { itemPrice ->
                    orderTotal += itemPrice
                }
                println("Dessert: $it")
            } else {
                println("Dessert: $it - not available")
            }
        }
        if (orderDetails.extras.isNotEmpty()) {
            println("Extras:")
            orderDetails.extras.forEach { extra ->
                if (Inventory.isItemAvailable(extra)) {
                    Inventory.extractItem(extra)?.let { itemPrice ->
                        orderTotal += itemPrice
                    }
                    println(" - $extra")
                } else {
                    println(" - $extra - not available")
                }
            }
        }
    }

    internal fun getOrderTotal(): Double = orderTotal

    abstract fun printOrderTotal()
}