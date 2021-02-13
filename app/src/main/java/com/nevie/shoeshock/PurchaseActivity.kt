package com.nevie.shoeshock

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nevie.shoeshock.databinding.ActivityPurchaseBinding
import com.nevie.shoeshock.models.Cart
import java.math.RoundingMode

class PurchaseActivity: AppCompatActivity() {

    private var summary: String = ""
    private var totalCost : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.purchaseSummaryTextview.text = Cart.getCartSummaryText()
        binding.purchaseTotalPrice.text = Cart.getCartValue().toBigDecimal()
            .setScale(2,RoundingMode.DOWN).toDouble().toString()


        var bar = supportActionBar
        bar?.title = "back"
        bar?.setDisplayHomeAsUpEnabled(true)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)

        val inflater : MenuInflater = menuInflater
        inflater.inflate(com.nevie.shoeshock.R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //return super.onOptionsItemSelected(item)
        return when (item.itemId) {
            com.nevie.shoeshock.R.id.cart_menu -> {
                openCartActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }


    private fun openCartActivity( ){

        val intent = Intent(this, CartActivity::class.java)

        startActivity(intent)

    }


    override fun onSupportNavigateUp(): Boolean {
//        return super.onSupportNavigateUp()
        onBackPressed()
        return true
    }



}