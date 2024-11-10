package domain.inventory

data class InventoryItem(
    var quantity: Int,
    val price: Double
)

object Inventory {
    private val items = mutableMapOf(
        "Pizza" to InventoryItem(quantity = 10, price = 8.99),
        "Burger" to InventoryItem(quantity = 15, price = 5.49),
        "Salad" to InventoryItem(quantity = 20, price = 4.99),
        "Coke" to InventoryItem(quantity = 30, price = 1.99),
        "Water" to InventoryItem(quantity = 50, price = 0.99),
        "Ice Cream" to InventoryItem(quantity = 25, price = 2.99),
        "Fries" to InventoryItem(quantity = 40, price = 2.49)
    )

    fun isItemAvailable(item: String): Boolean {
        return (items[item]?.quantity ?: 0) > 0
    }

    fun reduceItemQuantity(item: String) {
        val inventoryItem = items[item]
        if (inventoryItem != null && inventoryItem.quantity > 0) {
            inventoryItem.quantity -= 1
        } else {
            println("Item \"$item\" is out of stock!")
        }
    }

    fun showInventory() {
        println("\nCurrent Inventory:")
        items.forEach { (item, inventoryItem) ->
            println("$item: Quantity = ${inventoryItem.quantity}, Price = \$${inventoryItem.price}")
        }
    }
}
