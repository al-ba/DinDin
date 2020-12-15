package ir.lb7.dindin.view.main.food

import com.airbnb.mvrx.Loading
import ir.lb7.dindin.domain.Food
import ir.lb7.dindin.framework.FoodInteractors
import ir.lb7.dindin.framework.OrderInteractors
import ir.lb7.dindin.helper.MvRxViewModel

class FoodViewModel(initialState: MainState) :
    MvRxViewModel<MainState>(initialState) {

    private lateinit var foodInteractors: FoodInteractors
    private lateinit var ordersInteractors: OrderInteractors

    private fun fetch() {
        setState {
            copy(foods = Loading())
        }
        foodInteractors.getFoods().execute {
            copy(foods = it)
        }

    }

    fun fetchOrders() {
        setState {
            copy(orders = Loading())
        }
        ordersInteractors.getOrders().execute {
            copy(orders = it)
        }
    }

    fun init(foodInteractors: FoodInteractors, ordersInteractors: OrderInteractors) {
        this.foodInteractors = foodInteractors
        this.ordersInteractors = ordersInteractors
        fetch()
    }

    fun orderFood(food: Food) {
        ordersInteractors.addOrder(food)
        fetchOrders()
    }
}