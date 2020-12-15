package ir.lb7.dindin.usecase

import ir.lb7.dindin.data.FoodRepository

class GetFoods(private val repository: FoodRepository) {
    operator fun invoke() = repository.getFoods()
}