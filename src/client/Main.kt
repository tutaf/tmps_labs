package client

import domain.facade.OrderFacade
import domain.models.OrderDetailsBuilder
import domain.order.DiscountedOrder

fun main() {

    val dineInOrderDetails = OrderDetailsBuilder("dinein")
        .setMainCourse("Pizza")
        .setDrink("Coke")
        .setDessert("Cheesecake")
        .addExtra("Fries")
        .addCombo("Burger Combo")
        .build()

    val orderFacade = OrderFacade()
    var order = orderFacade.placeOrder(dineInOrderDetails) ?: return

    println("\nDo you have a discount card? (Y/N)")
    when (readln().lowercase()) {
        "y" -> order = DiscountedOrder(order)
    }

    order.printOrderTotal()


}
