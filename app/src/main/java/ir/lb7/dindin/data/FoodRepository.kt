package ir.lb7.dindin.data

class FoodRepository(private val dataSource: FoodDataSource) {
    fun getFoods() = dataSource.getFoods()
}