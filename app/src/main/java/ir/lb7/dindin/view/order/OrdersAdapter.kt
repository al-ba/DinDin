package ir.lb7.dindin.view.order

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ir.lb7.dindin.R
import ir.lb7.dindin.databinding.OrderListItemBinding
import ir.lb7.dindin.domain.Food
import ir.lb7.dindin.domain.Order


class OrdersAdapter(
    private var orders: MutableList<Order>,
    val itemClickListener: OrderClickListener
) :
    RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = OrderListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: OrderViewHolder, position: Int) {
        viewHolder.bind(orders[position])
    }

    override fun getItemCount() = orders.size

    fun update(orders: List<Order>) {
        this.orders.clear()
        this.orders.addAll(orders)
        notifyDataSetChanged()
    }

    //region:view holder
    inner class OrderViewHolder(view: OrderListItemBinding) : RecyclerView.ViewHolder(view.root) {
        private val nameTextView: TextView = view.orderItemName
        private val priceTextView: TextView = view.orderItemPrice
        private val foodImageView: ImageView = view.orderItemImage
        private val removeImageView: ImageView = view.orderItemRemove

        init {
            removeImageView.setOnClickListener {
                itemClickListener.clickOnRemove(orders[adapterPosition].food)
            }
        }

        fun bind(order: Order) {
            nameTextView.text = if (order.count > 1) {
                "${order.count} ${order.food.title}"
            } else {
                order.food.title
            }
            priceTextView.text = priceTextView.context.getString(
                R.string.order_list_item_price,
                order.food.price * order.count
            )
            foodImageView.load(order.food.imageUrl){
                transformations(RoundedCornersTransformation(10.0f))
            }
        }
    }
    //endregion:view holder

    interface OrderClickListener {
        fun clickOnRemove(food: Food)
    }
}
