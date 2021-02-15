package com.nevie.shoeshock

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ActivityCartBinding
import com.nevie.shoeshock.models.Cart
import com.nevie.shoeshock.models.CartAction
import com.nevie.shoeshock.models.ShoeItem


private const val TAG = "CartActivity"

class CartActivity : AppCompatActivity() {

    //@Alan TODO
    // I wanted to call these, but was having problems with the findViewById getting ints, not TextViews... posted more details on discord.

    private val commandsForCartItemActions
            : (CartViewAdapter.CartViewHolder, MutableList<ShoeItem>, CartAction, Int) -> Unit =
        { holder, shoeItems, cartAction, position->
//        when (cartAction) {
//            CartAction.ADD_ONE -> {
//                shoeItems[position].plus(1)
                    ////Type mismatch: inferred type is TextView! but Int was expected.  why??
//                holder.itemView.findViewById<TextView>(quantity_input).text  = shoeItems[position].quantity.toString()
//                holder.itemView.findViewById<TextView>(cart_item_subtotal).text = shoeItems[position].getFormattedSubTotalAsString()
//
//                //Verify this one.
//                holder.itemView.rootView.findViewById<TextView>(cart_value_label).text =  Cart.getCartValue().toString()
//
//
//            }
//            CartAction.SUBTRACT_ONE -> {
//                shoeItems[position].minus(1)
//                holder.itemView.findViewById<TextView>(quantity_input).text  = shoeItems[position].quantity.toString()
//                holder.itemView.findViewById<TextView>(cart_item_subtotal).text = shoeItems[position].getFormattedSubTotalAsString()
//
//                //Verify this one.
//                holder.itemView.rootView.findViewById<TextView>(cart_value_label).text =  Cart.getCartValue().toString()
//
//            }
//
//            CartAction.CHANGE_SIZE -> {
//                TODO()
//            }
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityCartBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //cartRecyclerViewAdapter.setList()

        binding.cartValueLabel.text = Cart.getCartValue().toString()
        binding.purchaseButton.setOnClickListener {
            val intent = Intent(this, PurchaseActivity::class.java)
            startActivity(intent)
        }

        binding.cartListRecyclerview.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.cartListRecyclerview.adapter = CartViewAdapter(Cart.getCart()){
                holder: CartViewAdapter.CartViewHolder,
                shoeItems: MutableList<ShoeItem>, action: CartAction, position: Int ->
                 //   commandsForCartItemActions(holder, shoeItems, action, position)

            when (action) {
                CartAction.ADD_ONE -> {
                    shoeItems[position].plus(1, this)
                    //Type mismatch: inferred type is TextView! but Int was expected.  why??
//                    holder.itemView.findViewById<TextView>(quantity_input)).text  = shoeItems[position].quantity.toString()
//                    holder.itemView.findViewById<TextView>(cart_item_subtotal).text = shoeItems[position].getFormattedSubTotalAsString()
                    holder.bind(shoeItems[position])

                    //Verify this one. Code smell?
                    binding.cartValueLabel.text = Cart.getCartValue().toString()
                }
                CartAction.SUBTRACT_ONE -> {
                    shoeItems[position].minus(1)
                    holder.bind(shoeItems[position])
                    binding.cartValueLabel.text =  Cart.getCartValue().toString()
                }
//
//                CartAction.CHANGE_SIZE -> {
//                    TODO()
//                }
//
                else -> {
                    TODO()
                }
            }
        }

        var bar = supportActionBar
        bar?.title = "Cart"
        bar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}