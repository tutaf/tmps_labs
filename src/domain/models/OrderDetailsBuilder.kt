package domain.models

class OrderDetailsBuilder(private val orderType: String) {
    private var mainCourse: String? = null
    private var drink: String? = null
    private var dessert: String? = null
    private val extras = mutableListOf<String>()
    private val combos = mutableListOf<String>()


    fun setMainCourse(mainCourse: String) = apply { this.mainCourse = mainCourse }
    fun setDrink(drink: String) = apply { this.drink = drink }
    fun setDessert(dessert: String) = apply { this.dessert = dessert }
    fun addExtra(extra: String) = apply { this.extras.add(extra) }
    fun addCombo(combo: String) = apply { this.combos.add(combo) }

    fun build(): OrderDetails {
        return OrderDetails(orderType, mainCourse, drink, dessert, extras, combos)
    }
}