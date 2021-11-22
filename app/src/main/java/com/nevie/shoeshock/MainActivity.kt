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
import com.nevie.shoeshock.models.Shoe
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

        binding.shoesListRecyclerview.adapter =  ShoesViewAdapter(ShoeRepository.getShoes()) {
                shoe: Shoe,clickedHeartBoolean: Boolean  ->
                openShoeDetailsActivity(shoe)
        }

        binding.shoesListRecyclerview.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL  , false)

        val bar = supportActionBar
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