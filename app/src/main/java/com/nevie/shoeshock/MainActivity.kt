package com.nevie.shoeshock

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ActivityMainBinding
import com.nevie.shoeshock.models.Cart
import com.nevie.shoeshock.models.Shoe
import com.nevie.shoeshock.models.ShoeItem
import com.nevie.shoeshock.repositories.ShoeRepository

const val SHOE_ID = "Shoe_ID"

class MainActivity : AppCompatActivity() {

    private val onShoeItemClickListener: (Shoe, Boolean) -> Unit = {shoe, clickedHeartBoolean ->
        Toast.makeText(this, "Clicked on heart:${clickedHeartBoolean}. \nItem Clicked: ${shoe.name}. ", Toast.LENGTH_SHORT).show()
        if(clickedHeartBoolean) {
            val shoeItem = ShoeItem(shoe,7.0,1)
            Cart.addToCard(shoeItem)
            openCartActivity()
            //TODO finish adding sizeing option before sinding off to cart.
        } else {
            openShoeDetailsActivity(shoe)
        }
    }

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


    private var shoesClickableAdapter = ClickableRecyclerViewAdapter(onShoeItemClickListener)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        var binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        shoesClickableAdapter.setList(ShoeRepository().getShoes())
        binding.shoesListRecyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL  , false)
        binding.shoesListRecyclerview.adapter = shoesClickableAdapter

    }



    private fun openCartActivity(){

        val intent = Intent(this, CartActivity::class.java)
        //intent.putExtra("shoeItem_id", shoeItem)
        startActivity(intent)

    }

    private fun openShoeDetailsActivity(shoe : Shoe){
        val intent = Intent(this,  ShoeDetailActivity::class.java)
        intent.putExtra(SHOE_ID, shoe)
        startActivity(intent)

    }


}