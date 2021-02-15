package com.nevie.shoeshock

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ActivityShoeDetailBinding
import com.nevie.shoeshock.models.Cart
import com.nevie.shoeshock.models.Shoe
import com.nevie.shoeshock.models.ShoeItem

private const val TAG = "ShoeDetailActivity"

class ShoeDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_shoe_detail)

        var shoe = intent.getParcelableExtra<Shoe>("Shoe_ID") as Shoe
        var binding = ActivityShoeDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //Log.d(TAG, "Shoe.brand = ${shoe.brand}")
        binding.brandLabelDetail.text = shoe.brand
        binding.priceLabelDetail.text = "$${shoe.price}"
        binding.modelNameLabelDetail.text = shoe.modelName
        binding.descriptionLabelDetail.text = shoe.description
        binding.modelNameLabelDetail.text = shoe.modelName
        binding.largeShoeImageViewDetail.setImageResource(shoe.images.first())

        binding.shoeImagesRecyclerViewDetail.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        binding.shoeImagesRecyclerViewDetail.adapter = ShoeDetailViewAdapter(
            shoe
            ) {position: Int ->
                binding.largeShoeImageViewDetail.setImageResource(shoe.images[position])
            }

        val shoeSizeForMenu  = shoe.getSizes()
        var spinnerAdapter = ArrayAdapter<Double>(this, R.layout.simple_list_item_1, shoeSizeForMenu)
        binding.spinner.adapter = spinnerAdapter

        var bar = supportActionBar
        bar?.title = "${shoe.brand}"
        bar?.setDisplayHomeAsUpEnabled(true)

        binding.addToCartButtonDetail.setOnClickListener {
            val shoeItem = ShoeItem(shoe,binding.spinner.selectedItem.toString().toDouble(),1)
            //Log.d(TAG, "shoeDetailActivity: onLoad: button click: $shoeItem")
            Cart.addToCard(shoeItem)
            openCartActivity()
        }
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

    override fun onSupportNavigateUp(): Boolean {
//        return super.onSupportNavigateUp()
        onBackPressed()
        return true
    }

    private fun openCartActivity( ){
        //Log.d(TAG, "openCartActivity")
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)

    }


}