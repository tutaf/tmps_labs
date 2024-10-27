package domain.order

import domain.models.OrderDetails

class TakeAwayOrder(orderDetails: OrderDetails) : Order(orderDetails) {
    override fun processOrder() {
        println("\nProcessing Takeaway Order:")
        processOrderItems()
    }
}
