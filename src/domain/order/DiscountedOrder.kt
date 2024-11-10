package domain.order

class DiscountedOrder(
    decoratedOrder: Order
) : OrderDecorator(decoratedOrder) {

    override fun printOrderTotal() {
        super.printOrderTotal()

        val originalTotal = getOriginalOrderTotal()
        val discountAmount = originalTotal * 0.1
        val discountedTotal = originalTotal - discountAmount

        println("Price with discount applied: $${String.format("%.2f", discountedTotal)}\n---")
    }
}
