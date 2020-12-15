package ir.lb7.dindin.view.main.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import dagger.hilt.android.AndroidEntryPoint
import ir.lb7.dindin.R
import ir.lb7.dindin.databinding.FragmentFoodBinding
import ir.lb7.dindin.domain.Food
import ir.lb7.dindin.domain.FoodType
import ir.lb7.dindin.framework.FoodInteractors
import ir.lb7.dindin.framework.OrderInteractors
import javax.inject.Inject

@AndroidEntryPoint
class FoodFragment(private val foodType: FoodType) : Fragment(), MavericksView,
    FoodAdapter.FoodClickListener {

    @Inject
    lateinit var foodInteractors: FoodInteractors

    @Inject
    lateinit var ordersInteractors: OrderInteractors

    private lateinit var binding: FragmentFoodBinding
    private val viewModel: FoodViewModel by fragmentViewModel()
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodBinding.inflate(layoutInflater, container, false)
        foodAdapter = FoodAdapter(this)
        binding.foodRecyclerview.adapter = foodAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabShop.setOnClickListener {
            findNavController().navigate(R.id.action_slidingFragment_to_orderFragment)
        }
    }

    override fun invalidate(): Unit = withState(viewModel) { state ->
        if (state.foods is Success) {
            state.foods.invoke().let { foods ->
                foodAdapter.submitList(
                    foods.filter { food -> food.category == foodType }
                )
            }
        }
        if (state.orders is Success) {
            val sumOrders = state.orders.invoke().sumBy { it.count }
            binding.fabBadgeCount.text = sumOrders.toString()
            if (sumOrders > 0) {
                binding.fabBadgeCount.visibility = View.VISIBLE
            } else {
                binding.fabBadgeCount.visibility = View.GONE
            }
        }
    }

    override fun clickOnPrice(food: Food) {
        viewModel.orderFood(food)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init(foodInteractors, ordersInteractors)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchOrders()
    }
}