package com.nevie.shoeshock

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.models.Shoe

class ShoeImageRecyclerViewAdapter(private val shoeImageItemClickListener: (Int) -> Unit)
    : RecyclerView.Adapter<ShoeImageRecyclerViewAdapter.ShoeImageRecyclerViewHolder>() {

    private lateinit var shoe : Shoe

    fun setShoe(shoe: Shoe){
        this.shoe = shoe
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeImageRecyclerViewHolder {
        return ShoeImageRecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.shoe_image_item,parent, false)
        )
    }

    override fun getItemCount() = this.shoe.images.size

    override fun onBindViewHolder(holder: ShoeImageRecyclerViewHolder, position: Int) {
        holder.itemView.findViewById<ImageView>(R.id.shoe_image_in_recycler_item).setImageResource(shoe.images[position])

        holder.itemView.findViewById<ImageView>(R.id.shoe_image_in_recycler_item).setOnClickListener {
            shoeImageItemClickListener( shoe.images[position])
            Log.d("TAG", "ImageRsID ${shoe.images[position]}")
        }
    }

    class ShoeImageRecyclerViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView)
}