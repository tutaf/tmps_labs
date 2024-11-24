package domain.order.state

import domain.order.Order

interface OrderState {
    fun nextState(order: Order)
    fun cancelOrder(order: Order)
    fun getStateName(): String
}
