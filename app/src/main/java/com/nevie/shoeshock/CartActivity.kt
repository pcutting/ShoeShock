package com.nevie.shoeshock

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ActivityCartBinding
import com.nevie.shoeshock.models.Cart
import com.nevie.shoeshock.models.CartAction
import com.nevie.shoeshock.models.ShoeItem
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.cart_shoe_item.*


class CartActivity : AppCompatActivity() {

    private val onCartItemClickListener
            : ( MutableList<ShoeItem>, CartAction, Int, String) -> Unit =
        {shoeItems, cartAction, position, string ->
        when (cartAction) {
            CartAction.ADD_ONE -> {
                shoeItems[position].plus(1)
                quantity_input.text = shoeItems[position].quantity.toString()
                cart_value_label.text = Cart.getCartValue().toString()
                cart_item_subtotal.text = shoeItems[position].getFormatedSubTotalAsString()
            }
            CartAction.SUBTRACT_ONE -> {
                shoeItems[position].minus(1)
                quantity_input.text = shoeItems[position].quantity.toString()
                cart_value_label.text = Cart.getCartValue().toString()
                cart_item_subtotal.text = shoeItems[position].getFormatedSubTotalAsString()
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
        //var binding = ActivityCartBinding.inflate(layoutInflater)

        setContentView(binding.root)

        cartRecyclerViewAdapter.setList()
        binding.cartListRecyclerview.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.cartListRecyclerview.adapter = cartRecyclerViewAdapter
        binding.cartValueLabel.text = Cart.getCartValue().toString()
        binding.purchaseButton.setOnClickListener {
            val intent = Intent(this, PurchaseActivity::class.java)
            startActivity(intent)
        }

        var bar = supportActionBar
        bar?.title = "Cart"
        bar?.setDisplayHomeAsUpEnabled(true)


//        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha)
//
//        toolbar.setNavigationOnClickListener(object : OnClickListener() {
//            fun onClick(v: View?) {
//                startActivity(Intent(applicationContext, MainActivity::class.java))
//            }
//        })

    }

    override fun onSupportNavigateUp(): Boolean {
        //
        onBackPressed()
        return true
    }

}