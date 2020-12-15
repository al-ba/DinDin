package ir.lb7.dindin.view.order

import ir.lb7.dindin.domain.Food
import ir.lb7.dindin.framework.OrderInteractors
import ir.lb7.dindin.helper.MvRxViewModel

class OrderViewModel(initialState: OrderState) : MvRxViewModel<OrderState>(initialState) {
    private lateinit var ordersInteractors: OrderInteractors

    fun init(ordersInteractors: OrderInteractors) {
        this.ordersInteractors = ordersInteractors
        fetch()
    }

    private fun fetch() {
        ordersInteractors.getOrders().execute {
            copy(orders = it)
        }
    }

    fun removeOrder(food: Food) {
        ordersInteractors.deleteOrders(food)
        fetch()
    }
}