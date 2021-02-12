package com.nevie.shoeshock

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ActivityCartBinding
import com.nevie.shoeshock.models.Cart
import com.nevie.shoeshock.models.CartAction
import com.nevie.shoeshock.models.ShoeItem

class CartActivity : AppCompatActivity() {

    private val onCartItemClickListener: (Context, ShoeItem, CartAction, String) -> Unit = {context, shoeItem, cartAction, string ->
        when (cartAction) {
            CartAction.ADD_ONE -> {
                TODO()
            }
            CartAction.SUBTRACT_ONE -> {
                TODO()
            }
            CartAction.PURCHASE -> {
                TODO()
            }
            CartAction.CHANGE_SIZE -> {
                TODO()
            }


        }


    }

    private val cartRecyclerViewAdapter = CartRecyclerViewAdapter(onCartItemClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityCartBinding.inflate(layoutInflater)

        setContentView(binding.root)

        cartRecyclerViewAdapter.setList()
        binding.cartListRecyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        binding.cartListRecyclerview.adapter = cartRecyclerViewAdapter


    }


}