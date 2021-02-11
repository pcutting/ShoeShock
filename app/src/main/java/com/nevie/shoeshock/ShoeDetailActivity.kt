package com.nevie.shoeshock

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.nevie.shoeshock.databinding.ActivityShoeDetailBinding
import com.nevie.shoeshock.models.Shoe
import com.nevie.shoeshock.repositories.ShoeRepository

private const val TAG = "ShoeDetailActivity"

class ShoeDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_shoe_detail)

        var shoe : Shoe = intent.getParcelableExtra<Shoe>("Shoe_ID") as Shoe

        val shoe_sizes_for_menu = shoe.sizesAvailableList.map { it.size }.toList()


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

        var spinnerAdapter = ArrayAdapter<Double>(this, android.R.layout.simple_list_item_1, shoe_sizes_for_menu)

        binding.spinner.adapter = spinnerAdapter

    }
}