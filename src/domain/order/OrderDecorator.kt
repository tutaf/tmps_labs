// OrderDecorator.kt
package domain.order

abstract class OrderDecorator(protected val decoratedOrder: Order) : Order(decoratedOrder.orderDetails) {

    override fun processOrder() {
        decoratedOrder.processOrder()
    }

    override fun printOrderTotal() {
        decoratedOrder.printOrderTotal()
    }

    protected fun getOriginalOrderTotal(): Double {
        return decoratedOrder.getOrderTotal()
    }
}
