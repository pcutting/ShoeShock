package com.nevie.shoeshock

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.LinearLayout.HORIZONTAL
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ActivityMainBinding
import com.nevie.shoeshock.models.Shoe
import com.nevie.shoeshock.repositories.ShoeRepository

class MainActivity : AppCompatActivity() {

    private val onShoeItemClickListener: (Shoe, Boolean) -> Unit = {shoe, clickedHeartBoolean ->
        Toast.makeText(this, "Clicked on heart:${clickedHeartBoolean}. \nItem Clicked: ${shoe.labelOfShoe}. ", Toast.LENGTH_SHORT).show()
    }

    private val clickableAdapter = ClickableRecyclerViewAdapter(onShoeItemClickListener)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        var binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        clickableAdapter.setList(ShoeRepository().getShoes())
        binding.shoesListRecyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL  , false)
        binding.shoesListRecyclerview.adapter = clickableAdapter

    }
}