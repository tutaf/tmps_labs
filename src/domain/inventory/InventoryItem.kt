package domain.inventory

data class InventoryItem(
    override val name: String,
    var quantity: Int,
    private val price: Double
) : ItemComponent() {

    override fun getPrice(): Double = price

}