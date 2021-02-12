package com.nevie.shoeshock

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ActivityMainBinding
import com.nevie.shoeshock.models.Shoe
import com.nevie.shoeshock.repositories.ShoeRepository

const val SHOE_ID = "Shoe_ID"

class MainActivity : AppCompatActivity() {



    private val onShoeItemClickListener: (Shoe, Boolean) -> Unit = {shoe, clickedHeartBoolean ->
        Toast.makeText(this, "Clicked on heart:${clickedHeartBoolean}. \nItem Clicked: ${shoe.name}. ", Toast.LENGTH_SHORT).show()
        openShoeDetailsActivity(shoe)
    }

    private fun openShoeDetailsActivity(shoe : Shoe){
        val intent = Intent(this,  ShoeDetailActivity::class.java)
        intent.putExtra(SHOE_ID, shoe)
        startActivity(intent)

    }

    private val shoesClickableAdapter = ClickableRecyclerViewAdapter(onShoeItemClickListener)

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
}