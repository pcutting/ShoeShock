package com.nevie.shoeshock

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.CartShoeItemBinding
import com.nevie.shoeshock.models.Cart
import com.nevie.shoeshock.models.CartAction
import com.nevie.shoeshock.models.ShoeItem




private const val TAG = "CartRecyclerViewAdapter"

class CartViewAdapter(
    private val shoeItems : MutableList<ShoeItem>,
    private val onClick: ( CartViewHolder, MutableList<ShoeItem>, CartAction, Int) -> Unit )
    : RecyclerView.Adapter<CartViewAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartShoeItemBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding)

//        return CartViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.cart_shoe_item, parent, false)
//        )
    }

    override fun getItemCount() = shoeItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

//        val cartItem = holder.itemView

        holder.bind(shoeItems[position])

        //TODO fix size spinner.
        //cart_item.findViewById<TextView>(R.id.price_in_cart).spinner_cart.selectedItem("8") = shoeItems[position].size

        //TODO fix discount price.
        //cart_item.findViewById<TextView>(R.id.discounted_price_in_cart).text = shoeItems[position].shoe.priceAfterDiscounts()


        holder.itemView.findViewById<Button>(R.id.plus_one_button).setOnClickListener {
            onClick(holder, shoeItems, CartAction.ADD_ONE, position)
        }

        //cartItem.minus_one_button.setOnClickListener {
        holder.itemView.findViewById<Button>(R.id.minus_one_button).setOnClickListener {
            onClick(holder,shoeItems, CartAction.SUBTRACT_ONE, position)
            if (shoeItems[position].quantity <= 0) {
                shoeItems.removeAt(position)
                notifyItemRemoved(position)
            }
        }

        val shoeSizesForMenu  = shoeItems[position].shoe.getSizes()
//        var spinnerAdapter = ArrayAdapter<Double>(cartItem.context, android.R.layout.simple_list_item_1, shoeSizesForMenu)
//        cartItem.spinner_cart.adapter = spinnerAdapter
//
//        shoeSizesForMenu.indexOf(shoeItems[position].size)
//        Log.d(TAG, "$shoeSizesForMenu : shoe sizes available for ${shoeItems[position].shoe.name}")
//        cartItem.spinner_cart.setSelection(shoeSizesForMenu.indexOf(shoeItems[position].size))

    }

    class CartViewHolder(private val binding: CartShoeItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(shoeItem: ShoeItem){

            binding.apply {

                shoeImageCart.setImageResource(shoeItem.shoe.images.first())
                priceInCart.text = shoeItem.shoe.price
                labelForCartShoe.text = shoeItem.shoe.name
                quantityInput.text = shoeItem.quantity.toString()
                cartItemSubtotal.text = shoeItem.getFormattedSubTotalAsString()

            }
        }
    }
}