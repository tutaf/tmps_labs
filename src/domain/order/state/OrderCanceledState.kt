package domain.order.state

import domain.order.Order

class OrderCanceledState : OrderState {
    override fun nextState(order: Order) {
        println("Order is canceled. No further action can be taken.")
    }

    override fun cancelOrder(order: Order) {
        println("Order is already canceled.")
    }

    override fun getStateName(): String = "Order Canceled"
}
