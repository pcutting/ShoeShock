package com.nevie.shoeshock

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.models.Cart
import com.nevie.shoeshock.models.CartAction
import com.nevie.shoeshock.models.ShoeItem
import kotlinx.android.synthetic.main.cart_shoe_item.view.*





class CartRecyclerViewAdapter(private val cartItemClickListener : (Context, ShoeItem, CartAction, String) -> Unit )
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

        val cart_item = holder.itemView

        //TODO fix size spinner.
        //cart_item.findViewById<TextView>(R.id.price_in_cart).spinner_cart.selectedItem("8") = shoeItems[position].size

        //TODO fix discount price.
        //cart_item.findViewById<TextView>(R.id.discounted_price_in_cart).text = shoeItems[position].shoe.priceAfterDiscounts()

        cart_item.findViewById<ImageView>(R.id.shoe_image_cart).setImageResource(shoeItems[position].shoe.images.first())
        cart_item.findViewById<TextView>(R.id.price_in_cart).text = shoeItems[position].shoe.price
        cart_item.findViewById<TextView>(R.id.label_for_cart_shoe).text = shoeItems[position].shoe.name

        cart_item.findViewById<TextView>(R.id.quantity_input).text = shoeItems[position].quantity.toString()


    }


    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}