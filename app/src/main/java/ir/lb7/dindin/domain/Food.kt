package ir.lb7.dindin.domain

data class Food(
    val id: Int,
    val title: String,
    val description: String,
    val footer: String,
    val imageUrl: String,
    val price: Double,
    val category: FoodType
)

enum class FoodType(val text: String) {
    PIZZA("Pizza"),
    SUSHI("Sushi"),
    DRINK("Drink")
}