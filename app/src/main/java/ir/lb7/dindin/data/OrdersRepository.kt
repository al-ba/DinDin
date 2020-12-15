package ir.lb7.dindin.data

import io.reactivex.Observable
import ir.lb7.dindin.domain.Food
import ir.lb7.dindin.domain.Order

class OrdersRepository(private val dataSource: OrdersDataSource) {
    fun getOrders(): Observable<List<Order>> {
        return dataSource.getOrders()
    }

    fun addOrder(food: Food) {
        dataSource.addOrder(food)
    }

    fun deleteOrder(food: Food) {
        dataSource.deleteOrder(food)
    }
}