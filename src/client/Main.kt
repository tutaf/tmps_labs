package client

import domain.models.OrderDetailsBuilder
import domain.factory.OrderFactory
import domain.inventory.Inventory
import domain.order.DiscountedOrder

fun main() {
    //Inventory.showInventory()

    val dineInOrderDetails = OrderDetailsBuilder("dinein")
        .setMainCourse("Pizza")
        .setDrink("Coke")
        .setDessert("Cheesecake")
        .addExtra("Fries")
        .build()

    val orderFactory = OrderFactory()
    var dineInOrder = orderFactory.createOrder(dineInOrderDetails)

    println("Apply discount? (Y/N)")
    when (readln().lowercase()) {
        "y" -> dineInOrder = DiscountedOrder(dineInOrder)
    }

    dineInOrder.processOrder()
    dineInOrder.printOrderTotal()


    //Inventory.showInventory()
}
