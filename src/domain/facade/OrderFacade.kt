package domain.facade

import domain.factory.OrderFactory
import domain.inventory.Inventory
import domain.models.OrderDetails
import domain.order.Order

class OrderFacade {
    private val orderFactory = OrderFactory()

    fun placeOrder(orderDetails: OrderDetails): Order? {
        if (!Inventory.isItemAvailable(orderDetails.mainCourse ?: "")) {
            println("You haven't selected a main course or it's not available. Canceling the order")
            return null
        }
        val order = orderFactory.createOrder(orderDetails)
        order.processOrder()
        return order
    }
}
