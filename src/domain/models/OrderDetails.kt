package domain.models

data class OrderDetails(
    val orderType: String,
    val mainCourse: String?,
    val drink: String?,
    val dessert: String?,
    val extras: List<String>,
    val combos: List<String>
)