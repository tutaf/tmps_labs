package domain.inventory

abstract class ItemComponent {
    abstract val name: String
    abstract fun getPrice(): Double
    open fun addItem(item: ItemComponent) {
        throw UnsupportedOperationException("Cannot add item to this component")
    }
    open fun removeItem(item: ItemComponent) {
        throw UnsupportedOperationException("Cannot remove item from this component")
    }
    open fun getItems(): List<ItemComponent> {
        throw UnsupportedOperationException("This component does not contain items")
    }
}
