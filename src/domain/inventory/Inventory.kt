package domain.inventory

object Inventory {
    private val items = mutableMapOf(
        "Pizza" to 10,
        "Burger" to 15,
        "Salad" to 20,
        "Coke" to 30,
        "Water" to 50,
        "Ice Cream" to 25,
        "Fries" to 40
    )

    fun isItemAvailable(item: String): Boolean {
        return items.getOrDefault(item, 0) > 0
    }

    fun reduceItemQuantity(item: String) {
        val quantity = items.getOrDefault(item, 0)
        if (quantity > 0) {
            items[item] = quantity - 1
        } else {
            println("Item \"$item\" is out of stock!")
        }
    }

    fun showInventory() {
        println("\nCurrent Inventory:")
        items.forEach { (item, quantity) ->
            println("$item: $quantity")
        }
    }
}
