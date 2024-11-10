package client

import domain.models.OrderDetailsBuilder
import domain.factory.OrderFactory
import domain.inventory.Inventory

fun main() {
    //Inventory.showInventory()

    val dineInOrderDetails = OrderDetailsBuilder("dinein")
        .setMainCourse("Pizza")
        .setDrink("Coke")
        .setDessert("Cheesecake")
        .addExtra("Fries")
        .build()

    val orderFactory = OrderFactory()
    val dineInOrder = orderFactory.createOrder(dineInOrderDetails)
    dineInOrder.processOrder()
    dineInOrder.printOrderTotal()


    //Inventory.showInventory()
}
