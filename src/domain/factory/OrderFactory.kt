package domain.factory

import domain.models.OrderDetails
import domain.order.DeliveryOrder
import domain.order.DineInOrder
import domain.order.Order
import domain.order.TakeAwayOrder

class OrderFactory {
    fun createOrder(orderDetails: OrderDetails): Order {
        return when (orderDetails.orderType.lowercase()) {
            "dinein" -> DineInOrder(orderDetails)
            "takeaway" -> TakeAwayOrder(orderDetails)
            "delivery" -> DeliveryOrder(orderDetails)
            else -> throw IllegalArgumentException("Unknown order type.")
        }
    }
}