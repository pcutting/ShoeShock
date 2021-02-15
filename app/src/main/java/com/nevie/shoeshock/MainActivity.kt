package com.nevie.shoeshock

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
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

//    private val onShoeItemClickListener: (Shoe, Boolean) -> Unit = {shoe, clickedHeartBoolean ->
//        Toast.makeText(this, "Clicked on heart:${clickedHeartBoolean}. \nItem Clicked: ${shoe.name}. ", Toast.LENGTH_SHORT).show()
//        if(clickedHeartBoolean) {
//            //var size = spinner_in_menu_link.selectedItem.toString().toDoubleOrNull() ?: 0.0
//            //val shoeItem = ShoeItem(shoe,size,1)
//            //Cart.addToCard(shoeItem)
//            openCartActivity()
//            //TODO finish adding sizing option before sending off to cart.
//        } else {
//            openShoeDetailsActivity(shoe)
//        }
//    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //return super.onCreateOptionsMenu(menu)
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)

        val menuItemSpinner = menu?.findItem(R.id.menu_spinner_for_sizes_in_options_menu)
        //spinner_in_menu_link = menuItemSpinner?.actionView as Spinner
        val items = arrayOf("7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11", "11.5")
        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, items)
       // spinner_in_menu_link.adapter = adapter

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //return super.onOptionsItemSelected(item)
        return when (item.itemId) {
            R.id.cart_menu -> {
                openCartActivity()
                true
            }
//            R.id.menu_spinner_for_sizes_in_options_menu -> {
//                this.shoesShoesClickableAdapter.notifyDataSetChanged()
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //private lateinit var shoesShoesClickableAdapter : ShoesViewAdapter
//    private lateinit var binding : ActivityMainBinding


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        //val referMainActivity = this

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


//        binding.shoesListRecyclerview.apply {
//            adapter = ClickableRecyclerViewAdapter(
//                ShoeRepository.getShoes()
//            ) {  shoe: Shoe, clickedHeartBoolean: Boolean ->
//                if (clickedHeartBoolean) {
//                    openCartActivity()  // fails at this point..
//                }
//            }
//        }


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


        binding.shoesListRecyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL  , false)
        //binding.shoesListRecyclerview.adapter = shoesClickableAdapter

        var bar = supportActionBar
        bar?.title = "Shoe Shock - Catalog"

    }

    private fun openCartActivity(){
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }

    private fun openShoeDetailsActivity(shoe : Shoe){
        val intent = Intent(this,  ShoeDetailActivity::class.java)
        intent.putExtra(SHOE_ID, shoe)
        startActivity(intent)
    }
}