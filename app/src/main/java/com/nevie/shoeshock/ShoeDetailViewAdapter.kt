package com.nevie.shoeshock

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ShoeImageItemBinding
import com.nevie.shoeshock.models.Shoe

class ShoeDetailViewAdapter(private val shoe: Shoe,
                            private val onClick: (Int) -> Unit)
    : RecyclerView.Adapter<ShoeDetailViewAdapter.ShoeDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ShoeImageItemBinding.inflate(layoutInflater, parent, false)
        return ShoeDetailViewHolder(binding)
    }

    override fun getItemCount() = this.shoe.images.size

    override fun onBindViewHolder(holder: ShoeDetailViewHolder, position: Int) {
        //holder.itemView.findViewById<ImageView>(R.id.shoe_image_in_recycler_item).setImageResource(shoe.images[position])
        holder.bind(shoe,position)
        holder.itemView.findViewById<ImageView>(R.id.shoe_image_in_recycler_item).setOnClickListener {
            onClick(position)    //(shoe.images[position])
            //Log.d("TAG", "ImageRsID ${shoe.images[position]}")
        }
    }

    class ShoeDetailViewHolder(private val binding: ShoeImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shoe: Shoe, position: Int) {
            binding.apply{
                shoeImageInRecyclerItem.setImageResource(shoe.images[position])
                //root.setOnClickListener(binding, false)
            }
        }
    }
}