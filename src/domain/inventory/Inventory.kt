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

    /**
     * Extracts an item from the inventory by reducing its quantity by one.
     * Returns the price of the item if available, or `null` if the item is out of stock.
     * Also prints out an error if item is out of stock
     *
     * @param item The name of the item to extract from the inventory.
     * @return The price of the item as a [Double] if available, or `null` if out of stock.
     */
    fun extractItem(item: String): Double? {
        val inventoryItem = items[item]
        if (inventoryItem != null && inventoryItem.quantity > 0) {
            inventoryItem.quantity -= 1
            return inventoryItem.price
        } else {
            println("Item \"$item\" is out of stock!")
            return null
        }
    }

    fun showInventory() {
        println("\nCurrent Inventory:")
        items.forEach { (item, inventoryItem) ->
            println("$item: Quantity = ${inventoryItem.quantity}, Price = \$${inventoryItem.price}")
        }
    }
}
