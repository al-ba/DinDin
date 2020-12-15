package ir.lb7.dindin.view.main.food

import android.graphics.LightingColorFilter
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import ir.lb7.dindin.R
import ir.lb7.dindin.databinding.FoodListItemBinding
import ir.lb7.dindin.domain.Food


class FoodAdapter(
    val itemClickListener: FoodClickListener
) : ListAdapter<Food, FoodAdapter.FoodViewHolder>(FOOD_DIFF) {
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = FoodListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: FoodViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    //region:view holder
    inner class FoodViewHolder(view: FoodListItemBinding) : RecyclerView.ViewHolder(view.root) {
        private val titleTextView: TextView = view.foodItemTitleTextView
        private val descriptionTextView: TextView = view.foodItemDescriptionTextView
        private val foodImageView: ImageView = view.foodItemImageView
        private val footerTextView: TextView = view.foodItemFooterTextView
        private val priceButton: Button = view.foodItemPriceButton.also { button ->
            button.setOnClickListener {
                button.text = button.context.getString(R.string.added_food)
                button.background.colorFilter = LightingColorFilter(0x00AA00, 0x00AA00)
                itemClickListener.clickOnPrice(getItem(adapterPosition))
                handler.postDelayed({
                    button.text = getPriceText(getItem(adapterPosition))
                    button.background.colorFilter = null
                }, 1000)
            }
        }

        fun bind(food: Food) {
            titleTextView.text = food.title
            descriptionTextView.text = food.description
            foodImageView.load(food.imageUrl) {
                transformations(RoundedCornersTransformation(topLeft = 40.0f, topRight = 40.0f))
            }
            footerTextView.text = food.footer
            priceButton.text = getPriceText(food)
        }

        private fun getPriceText(food: Food) =
            priceButton.context.getString(R.string.price_format, food.price)
    }
    //endregion:view holder

    companion object {
        val FOOD_DIFF = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Food, newItem: Food) = oldItem == newItem
        }
    }

    interface FoodClickListener {
        fun clickOnPrice(food: Food)
    }
}
