package domain.order

import domain.models.OrderDetails
import domain.inventory.Inventory

abstract class Order(internal val orderDetails: OrderDetails) {
    protected var orderTotal: Double = 0.0

    abstract fun processOrder()

    protected fun processOrderItems() {
        orderTotal = 0.0
        println("Order Details:")
        val itemsToProcess = mutableListOf<String>()
        val itemNames = mutableListOf<String>()

        orderDetails.mainCourse?.let {
            itemsToProcess.add(it)
            itemNames.add("Main Course")
        }
        orderDetails.drink?.let {
            itemsToProcess.add(it)
            itemNames.add("Drink")
        }
        orderDetails.dessert?.let {
            itemsToProcess.add(it)
            itemNames.add("Dessert")
        }
        itemsToProcess.addAll(orderDetails.extras)
        itemNames.addAll(List(orderDetails.extras.size) { "Extra" })
        itemsToProcess.addAll(orderDetails.combos)
        itemNames.addAll(List(orderDetails.combos.size) { "Combo" })


        itemsToProcess.forEachIndexed { index, itemName ->
            if (Inventory.isItemAvailable(itemName)) {
                Inventory.extractItem(itemName)?.let { itemPrice ->
                    orderTotal += itemPrice
                }
                println("${itemNames[index]}: $itemName")
            } else {
                println("${itemNames[index]}: $itemName - not available")
            }
        }
    }

    internal fun getOrderTotal(): Double = orderTotal

    abstract fun printOrderTotal()
}
