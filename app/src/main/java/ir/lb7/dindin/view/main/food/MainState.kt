package ir.lb7.dindin.view.main.food

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import ir.lb7.dindin.domain.Food
import ir.lb7.dindin.domain.Order

data class MainState(
    val foods: Async<List<Food>> = Uninitialized,
    val orders: Async<List<Order>> = Uninitialized
) : MavericksState