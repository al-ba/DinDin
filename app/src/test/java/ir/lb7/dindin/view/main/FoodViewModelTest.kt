package ir.lb7.dindin.view.main

import com.airbnb.mvrx.Success
import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import ir.lb7.dindin.data.FoodRepository
import ir.lb7.dindin.data.OrdersRepository
import ir.lb7.dindin.domain.Food
import ir.lb7.dindin.framework.FakeFoodDataSource
import ir.lb7.dindin.framework.FoodInteractors
import ir.lb7.dindin.framework.OrderInteractors
import ir.lb7.dindin.framework.OrdersDataSourceImpl
import ir.lb7.dindin.usecase.AddOrder
import ir.lb7.dindin.usecase.DeleteOrder
import ir.lb7.dindin.usecase.GetFoods
import ir.lb7.dindin.usecase.GetOrders
import ir.lb7.dindin.view.main.food.FoodViewModel
import ir.lb7.dindin.view.main.food.MainState
import org.junit.Rule
import org.junit.Test

class FoodViewModelTest {

    //region Rules
    @get:Rule
    val mvrxTestRule = MvRxTestRule()
    //endregion

    //region Fields
    val foodInteractors = FoodInteractors(GetFoods(FoodRepository(FakeFoodDataSource())))

    val orderRepo = OrdersRepository(OrdersDataSourceImpl())
    val ordersInteractors = OrderInteractors(
        AddOrder(orderRepo),
        GetOrders(orderRepo),
        DeleteOrder(orderRepo)
    )
    //endregion

    //region Tests
    @Test
    fun initViewModel() {
        val viewModel = FoodViewModel(MainState())
        viewModel.init(foodInteractors, ordersInteractors)
        withState(viewModel) { state ->
            assert(state.orders is Success<*>)
            val orders = state.orders.invoke()
            assert(orders != null)
            assert(orders!!.isEmpty())
        }
    }

    @Test
    fun testOrderFood() {
        val viewModel = FoodViewModel(MainState())
        viewModel.init(foodInteractors, ordersInteractors)
        viewModel.orderFood(
            Food(
                1,
                "fake food",
                "fake food desc",
                "fake food footer",
                "fake image",
                10.0
            )
        )
        withState(viewModel) { state ->
            assert(state.orders is Success<*>)
            val orders = state.orders.invoke()
            assert(orders != null)
            assert(orders!!.size == 1)
        }
    }
    //endregion

}