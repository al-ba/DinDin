package ir.lb7.dindin.data

import io.reactivex.Observable
import ir.lb7.dindin.domain.Food
import ir.lb7.dindin.domain.Order

interface OrdersDataSource {
    fun getOrders(): Observable<List<Order>>
    fun addOrder(food: Food)
    fun deleteOrder(food: Food)
}