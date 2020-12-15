package ir.lb7.dindin.framework

import io.reactivex.Observable
import ir.lb7.dindin.data.OrdersDataSource
import ir.lb7.dindin.domain.Food
import ir.lb7.dindin.domain.Order

class OrdersDataSourceImpl : OrdersDataSource {
    private val orders = mutableListOf<Order>()
    override fun getOrders(): Observable<List<Order>> {
        return Observable.fromCallable {
            orders.map {
                it.copy(food = it.food, count = it.count)
            }
        }
    }

    override fun addOrder(food: Food) {
        val currentItem = getOrDefault(food)
        if (currentItem == null) {
            orders.add(Order(food, 1))
        } else {
            currentItem.count = currentItem.count + 1
        }
    }

    override fun deleteOrder(food: Food) {
        val currentItem = getOrDefault(food)
        if (currentItem != null) {
            orders.remove(currentItem)
        }
    }

    private fun getOrDefault(food: Food): Order? {
        return orders.firstOrNull {
            food.id == it.food.id
        }
    }
}