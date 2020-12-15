package ir.lb7.dindin.usecase

import io.reactivex.Observable
import ir.lb7.dindin.data.OrdersRepository
import ir.lb7.dindin.domain.Order

class GetOrders(private val repository: OrdersRepository) {
    operator fun invoke(): Observable<List<Order>> = repository.getOrders()
}