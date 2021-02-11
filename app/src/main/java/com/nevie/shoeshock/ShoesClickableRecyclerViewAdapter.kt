package com.nevie.shoeshock

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.models.Shoe

private const val TAG = "ClickableRecyclerViewAd"

class ClickableRecyclerViewAdapter(private val shoeItemClickListener: (Shoe, Boolean) -> Unit)
    : RecyclerView.Adapter<ClickableRecyclerViewAdapter.ClickableShoeViewHolder>() {


    private val shoes = mutableListOf<Shoe>()



    fun setList(shoes: List<Shoe>) {
        this.shoes.clear()
        this.shoes.addAll(shoes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewItem: Int): ClickableShoeViewHolder {
        return ClickableShoeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.shoe_item, parent, false)
        )
    }

    override fun getItemCount() = shoes.size

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }


    override fun onBindViewHolder(holderClickableShoe: ClickableShoeViewHolder, position: Int) {
        //        var binding = ShoeItemBinding.inflate(layoutInflater)
        // tried to figure out binding, couldn't do it.
        val priceWithSymbol = "$${(shoes[position].price)}"

        holderClickableShoe.itemView.findViewById<ImageView>(R.id.shoe_image)
            .setImageResource(shoes[position].images.first())

        holderClickableShoe.itemView.findViewById<TextView>(R.id.price_label).text = priceWithSymbol
        holderClickableShoe.itemView.findViewById<TextView>(R.id.brand_label).text = shoes[position].brand
        holderClickableShoe.itemView.findViewById<TextView>(R.id.model_name_label).text = shoes[position].name
        //holder.itemView.findViewById<TextView>(R.id.discounted_price_label).text = shoes[position].priceAfterDiscounts()
        holderClickableShoe.itemView.findViewById<ImageView>(R.id.heart_image)
            .setOnClickListener {
            shoeItemClickListener(shoes[position], true)
            Log.d(TAG, "clicked on heart")
            }


        holderClickableShoe.itemView.setOnClickListener {
            Log.d(TAG, "Clicked outside of heart")
            shoeItemClickListener(shoes[position], false)
        }

    }

    class ClickableShoeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)



}