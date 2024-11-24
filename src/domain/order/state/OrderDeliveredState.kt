package domain.order.state

import domain.order.Order

class OrderDeliveredState : OrderState {
    override fun nextState(order: Order) {
        println("Order is already delivered. No further action can be taken.")
    }

    override fun cancelOrder(order: Order) {
        println("Order cannot be canceled as it is already delivered.")
    }

    override fun getStateName(): String = "Order Delivered"
}
