package com.nevie.shoeshock

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ActivityMainBinding
import com.nevie.shoeshock.models.Cart
import com.nevie.shoeshock.models.Shoe
import com.nevie.shoeshock.models.ShoeItem
import com.nevie.shoeshock.repositories.ShoeRepository

const val SHOE_ID = "Shoe_ID"

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //return super.onOptionsItemSelected(item)
        return when (item.itemId) {
            R.id.cart_menu -> {
                openCartActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.shoesListRecyclerview.adapter =  ShoesViewAdapter(
                ShoeRepository.getShoes()
                ) { shoe: Shoe,clickedHeartBoolean: Boolean  ->
                    if(clickedHeartBoolean) {
                        // TODO spinner popup menu for size:
                        //var size = spinner_in_menu_link.selectedItem.toString().toDoubleOrNull() ?: 0.0
                        var size = 8.0

                        //TODO Fix the size below with spinner popup menu.

                        val shoeItem = ShoeItem(shoe,size,1)
                        Cart.addToCard(shoeItem)
                        openCartActivity()

                        //TODO finish adding sizing option before sending off to cart.
                    } else {
                        openShoeDetailsActivity(shoe)
                    }
            }

        binding.shoesListRecyclerview.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL  , false)

        var bar = supportActionBar
        bar?.title = "Shoe Shock - Catalog"
    }

    private fun openCartActivity(){
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }

    private fun openShoeDetailsActivity(shoe : Shoe){
        val intent = Intent(this, ShoeDetailActivity::class.java)
        intent.putExtra(SHOE_ID, shoe)
        startActivity(intent)
    }
}