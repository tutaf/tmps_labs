package domain.order

import domain.models.OrderDetails

class DeliveryOrder(orderDetails: OrderDetails) : Order(orderDetails) {
    override fun processOrder() {
        println("\nProcessing Delivery Order:")
        processOrderItems()
    }
}