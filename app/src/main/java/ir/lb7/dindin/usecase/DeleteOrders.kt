package ir.lb7.dindin.usecase

import ir.lb7.dindin.data.OrdersRepository
import ir.lb7.dindin.domain.Food

class DeleteOrder(private val repository: OrdersRepository) {
    operator fun invoke(food: Food) = repository.deleteOrder(food)
}