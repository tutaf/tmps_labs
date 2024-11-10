package domain.inventory


object Inventory {
    private val items = mutableMapOf<String, ItemComponent>()

    init {
        addItem(InventoryItem("Pizza", quantity = 10, price = 8.99))
        addItem(InventoryItem("Burger", quantity = 15, price = 5.49))
        addItem(InventoryItem("Fries", quantity = 42, price = 2.49))
        addItem(InventoryItem("Coke", quantity = 30, price = 1.99))
        addItem(InventoryItem("Salad", quantity = 3, price = 4.99))
        addItem(InventoryItem("Water", quantity = 50, price = 0.99))
        addItem(InventoryItem("Ice Cream", quantity = 11, price = 2.49))

        val burgerCombo = ComboItem("Burger Combo")
        burgerCombo.addItem(getItem("Burger")!!)
        burgerCombo.addItem(getItem("Fries")!!)
        burgerCombo.addItem(getItem("Coke")!!)
        addItem(burgerCombo)
    }

    fun isItemAvailable(itemName: String): Boolean {
        val item = items[itemName]
        return when (item) {
            is InventoryItem -> item.quantity > 0
            is ComboItem -> item.getItems().all { it is InventoryItem && it.quantity > 0 }
            else -> false
        }
    }

    fun addItem(item: ItemComponent) {
        items[item.name] = item
    }

    fun getItem(name: String): ItemComponent? = items[name]

    /**
     * Extracts an item from the inventory by reducing its quantity by one.
     * Returns the price of the item if available, or `null` if the item is out of stock.
     * Also prints out an error if item is out of stock
     *
     * @param item The name of the item to extract from the inventory.
     * @return The price of the item as a [Double] if available, or `null` if out of stock.
     */
    fun extractItem(itemName: String): Double? {
        val item = items[itemName]
        return when (item) {
            is InventoryItem -> {
                if (item.quantity > 0) {
                    item.quantity -= 1
                    item.getPrice()
                } else {
                    println("Item \"$itemName\" is out of stock!")
                    null
                }
            }
            is ComboItem -> {
                if (item.getItems().all { it is InventoryItem && it.quantity > 0 }) {
                    // Reduce quantity of each item in the combo
                    item.getItems().forEach {
                        if (it is InventoryItem) it.quantity -= 1
                    }
                    item.getPrice()
                } else {
                    println("Combo \"$itemName\" is out of stock!")
                    null
                }
            }
            else -> {
                println("Item \"$itemName\" does not exist!")
                null
            }
        }
    }

    fun showInventory() {
        println("\nCurrent Inventory:")
        items.forEach { (name, item) ->
            when (item) {
                is InventoryItem -> println("$name: Quantity = ${item.quantity}, Price = \$${item.getPrice()}")
                is ComboItem -> {
                    println("$name (Combo): Price = \$${String.format("%.2f", item.getPrice())}")
                    item.getItems().forEach { subItem ->
                        println("  - ${subItem.name}")
                    }
                }
            }
        }
    }
}
