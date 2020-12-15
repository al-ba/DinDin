package ir.lb7.dindin.view.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import dagger.hilt.android.AndroidEntryPoint
import ir.lb7.dindin.R
import ir.lb7.dindin.databinding.FragmentOrderBinding
import ir.lb7.dindin.domain.Food
import ir.lb7.dindin.framework.OrderInteractors
import javax.inject.Inject

@AndroidEntryPoint
class OrderFragment : Fragment(), MavericksView, OrdersAdapter.OrderClickListener {

    @Inject
    lateinit var ordersInteractors: OrderInteractors

    private lateinit var binding: FragmentOrderBinding
    private val viewModel: OrderViewModel by fragmentViewModel()
    private lateinit var ordersAdapter: OrdersAdapter

    companion object {
        fun newInstance() = OrderFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(layoutInflater, container, false)
        ordersAdapter = OrdersAdapter(mutableListOf(), this)
        binding.orderRecyclerView.adapter = ordersAdapter
        viewModel.init(ordersInteractors)
        return binding.root
    }

    override fun invalidate(): Unit = withState(viewModel) {
        if (it.orders is Success) {
            val orders = it.orders.invoke()
            ordersAdapter.update(orders)
            binding.orderTotalValueTv.text = context?.getString(
                R.string.order_total_value,
                orders.sumByDouble { order -> order.count * order.food.price })
        }
    }

    override fun clickOnRemove(food: Food) {
        viewModel.removeOrder(food)
    }

}