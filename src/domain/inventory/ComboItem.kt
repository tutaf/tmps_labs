package domain.inventory

class ComboItem(
    override val name: String
) : ItemComponent() {
    private val items = mutableListOf<ItemComponent>()

    override fun addItem(item: ItemComponent) {
        items.add(item)
    }

    override fun removeItem(item: ItemComponent) {
        items.remove(item)
    }

    override fun getItems(): List<ItemComponent> = items

    override fun getPrice(): Double {
        return items.sumOf { it.getPrice() } * 1.0
    }
}
