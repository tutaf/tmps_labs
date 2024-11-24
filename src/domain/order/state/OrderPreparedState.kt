package domain.order.state

import domain.order.Order

class OrderPreparedState : OrderState {
    override fun nextState(order: Order) {
        println("Order is now ready for delivery or pickup.")
        order.state = OrderDeliveredState()
    }

    override fun cancelOrder(order: Order) {
        println("Order cannot be canceled as it is already being prepared.")
    }

    override fun getStateName(): String = "Order Prepared"
}
