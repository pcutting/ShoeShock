package com.nevie.shoeshock

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.models.Cart
import com.nevie.shoeshock.models.CartAction
import com.nevie.shoeshock.models.ShoeItem
import kotlinx.android.synthetic.main.activity_shoe_detail.view.*
import kotlinx.android.synthetic.main.cart_shoe_item.view.*



private const val TAG = "CartRecyclerViewAdapter"

class CartRecyclerViewAdapter(private val cartItemClickListener : (CartViewHolder, MutableList<ShoeItem>, CartAction, Int, String) -> Unit )
    : RecyclerView.Adapter<CartRecyclerViewAdapter.CartViewHolder>() {

    private var shoeItems = mutableListOf<ShoeItem>()

    fun setList(){
        this.shoeItems = Cart.getCart()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cart_shoe_item, parent, false)
        )
    }

    override fun getItemCount() = Cart.cartItemsCount()

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        val cartItem = holder.itemView

        //TODO fix size spinner.
        //cart_item.findViewById<TextView>(R.id.price_in_cart).spinner_cart.selectedItem("8") = shoeItems[position].size

        //TODO fix discount price.
        //cart_item.findViewById<TextView>(R.id.discounted_price_in_cart).text = shoeItems[position].shoe.priceAfterDiscounts()

        cartItem.shoe_image_cart.setImageResource(shoeItems[position].shoe.images.first())
        cartItem.price_in_cart.text = shoeItems[position].shoe.price
        cartItem.label_for_cart_shoe.text = shoeItems[position].shoe.name

        cartItem.quantity_input.text = shoeItems[position].quantity.toString()

        cartItem.cart_item_subtotal.text = shoeItems[position].getFormattedSubTotalAsString()

        cartItem.plus_one_button.setOnClickListener {
            cartItemClickListener(holder, shoeItems, CartAction.ADD_ONE, position, "")
        }

        cartItem.minus_one_button.setOnClickListener {
            cartItemClickListener(holder,shoeItems, CartAction.SUBTRACT_ONE, position, "")
            if (shoeItems[position].quantity <= 0) {
                shoeItems.removeAt(position)
                notifyItemRemoved(position)
            }
        }

        val shoeSizesForMenu  = shoeItems[position].shoe.getSizes()
        var spinnerAdapter = ArrayAdapter<Double>(cartItem.context, android.R.layout.simple_list_item_1, shoeSizesForMenu)
        cartItem.spinner_cart.adapter = spinnerAdapter

        shoeSizesForMenu.indexOf(shoeItems[position].size)
        Log.d(TAG, "$shoeSizesForMenu : shoe sizes available for ${shoeItems[position].shoe.name}")
        cartItem.spinner_cart.setSelection(shoeSizesForMenu.indexOf(shoeItems[position].size))

    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}