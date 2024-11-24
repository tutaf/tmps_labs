package domain.order.state

import domain.order.Order

class OrderPlacedState : OrderState {
    override fun nextState(order: Order) {
        println("Order is now being prepared.")
        order.state = OrderPreparedState()
    }

    override fun cancelOrder(order: Order) {
        println("Order has been canceled.")
        order.state = OrderCanceledState()
    }

    override fun getStateName(): String = "Order Placed"
}
