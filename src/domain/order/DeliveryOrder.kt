package domain.order

import domain.models.OrderDetails

private const val DELIVERY_PRICE = 5.0f

class DeliveryOrder(orderDetails: OrderDetails) : Order(orderDetails) {
    override fun processOrder() {
        println("\nProcessing Delivery Order:")
        processOrderItems()
    }

    override fun printOrderTotal() {
        println("---\nOrder total: $$orderTotal + $$DELIVERY_PRICE (delivery) = $${orderTotal+ DELIVERY_PRICE}\n---")
    }
}