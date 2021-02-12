package com.nevie.shoeshock

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ActivityShoeDetailBinding
import com.nevie.shoeshock.models.Shoe
import kotlinx.android.synthetic.main.cart_shoe_item.view.*
import kotlinx.android.synthetic.main.shoe_image_item.view.*
import com.nevie.shoeshock.repositories.ShoeRepository as ShoeRepository1

private const val TAG = "ShoeDetailActivity"

class ShoeDetailActivity: AppCompatActivity() {

    private val onShoeImageItemClickListener: (Int) -> Unit = {imageResourceInt ->
        ActivityShoeDetailBinding.inflate(layoutInflater)
            .largeShoeImageViewDetail.setImageResource(imageResourceInt)
        Log.d(TAG,"image click? id: ${imageResourceInt}")


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

        shoeImagesClickableAdapter.setShoe(shoe)

        var spinnerAdapter = ArrayAdapter<Double>(this, R.layout.simple_list_item_1, shoe_sizes_for_menu)
        binding.shoeImagesRecyclerViewDetail.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        binding.shoeImagesRecyclerViewDetail.adapter = shoeImagesClickableAdapter

//        binding.shoeImagesRecyclerViewDetail.shoe_image_in_recycler_item.setOnClickListener {
//
//        }

        binding.spinner.adapter = spinnerAdapter

    }
}