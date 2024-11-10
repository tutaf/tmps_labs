package domain.order

import domain.models.OrderDetails

private const val PACKAGING_PRICE = 2.0f


class TakeAwayOrder(orderDetails: OrderDetails) : Order(orderDetails) {
    override fun processOrder() {
        println("\nProcessing Takeaway Order:")
        processOrderItems()
    }

    override fun printOrderTotal() {
        println("---\nOrder total: $$orderTotal + $$PACKAGING_PRICE (packaging) = $${orderTotal+ PACKAGING_PRICE}\n---")
    }
}
