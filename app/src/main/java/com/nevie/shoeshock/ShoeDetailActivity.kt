package com.nevie.shoeshock

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ActivityShoeDetailBinding
import com.nevie.shoeshock.models.Cart
import com.nevie.shoeshock.models.Shoe
import com.nevie.shoeshock.models.ShoeItem
import kotlinx.android.synthetic.main.activity_shoe_detail.*


private const val TAG = "ShoeDetailActivity"

class ShoeDetailActivity: AppCompatActivity() {

    //private lateinit var largeImageViewReference : ImageView

    private val onShoeImageItemClickListener: ( Int) -> Unit = { imageResourceInt ->
        large_shoe_image_view_detail.setImageResource(imageResourceInt)
    }

    private val shoeImagesClickableAdapter = ShoeImageRecyclerViewAdapter(onShoeImageItemClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_shoe_detail)

        var shoe : Shoe = intent.getParcelableExtra<Shoe>("Shoe_ID") as Shoe

        val shoe_sizes_for_menu  = shoe.getSizes()


        Log.d(TAG, "$shoe")

        var binding = ActivityShoeDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        Log.d(TAG, "Shoe.brand = ${shoe.brand}")

        binding.brandLabelDetail.text = shoe.brand
        binding.priceLabelDetail.text = shoe.price
        binding.modelNameLabelDetail.text = shoe.modelName
        binding.descriptionLabelDetail.text = shoe.description
        binding.modelNameLabelDetail.text = shoe.modelName
        binding.largeShoeImageViewDetail.setImageResource(shoe.images.first())

        binding.addToCartButtonDetail.setOnClickListener {

                val shoeItem = ShoeItem(shoe,binding.sizeLabelForSpinnerCart.text.toString().toDouble(),1)
                Log.d(TAG, "shoeDetailActivity: onLoad: button click: $shoeItem")
                Cart.addToCard(shoeItem)
                openCartActivity(shoeItem)
        }

        shoeImagesClickableAdapter.setShoe(shoe)

        var spinnerAdapter = ArrayAdapter<Double>(this, R.layout.simple_list_item_1, shoe_sizes_for_menu)
        binding.shoeImagesRecyclerViewDetail.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        binding.shoeImagesRecyclerViewDetail.adapter = shoeImagesClickableAdapter

        //largeImageViewReference = binding.largeShoeImageViewDetail

//        binding.shoeImagesRecyclerViewDetail.shoe_image_in_recycler_item.setOnClickListener {
//
//        }

        binding.spinner.adapter = spinnerAdapter

    }




    private fun openCartActivity( shoeItem: ShoeItem){
        Log.d(TAG, "openCartActivity")
        val intent = Intent(this, CartActivity::class.java)
        intent.putExtra("shoeItem_id", shoeItem)
        startActivity(intent)

    }


}