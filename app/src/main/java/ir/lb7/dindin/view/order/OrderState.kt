package ir.lb7.dindin.view.order

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import ir.lb7.dindin.domain.Order

data class OrderState(val orders: Async<List<Order>> = Uninitialized) : MavericksState