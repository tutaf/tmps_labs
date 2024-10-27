package domain.order

import domain.models.OrderDetails

class DineInOrder(orderDetails: OrderDetails) : Order(orderDetails) {
    override fun processOrder() {
        println("\nProcessing Dine-In Order:")
        processOrderItems()
    }
}
