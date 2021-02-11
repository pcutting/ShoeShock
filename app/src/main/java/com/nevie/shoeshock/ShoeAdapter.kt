package com.nevie.shoeshock

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.models.Shoe

class ShoeAdapter: RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder>() {
    private val shoeItems = mutableListOf<Shoe>()
    private var onShoeItemClickListener : ShoeViewHolder.OnShoeItemClickListener? = null

    //TODO("test if this is called.  it shouldn't be, clickableRecyclerViewAdapter replaces it")

    fun setShoeItems(shoeItems: List<Shoe>) {
        this.shoeItems.clear()
        this.shoeItems.addAll(shoeItems)
        notifyDataSetChanged()
    }

    fun setOnShoeItemClickListener(onShoeItemClickListener: ShoeViewHolder.OnShoeItemClickListener?) {
      this.onShoeItemClickListener = onShoeItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ShoeViewHolder {
        return ShoeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.shoe_item,parent, false))
    }

    override fun getItemCount()= shoeItems.size

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onShoeItemClickListener?.onShoeItemClick(shoeItems[position])
        }
    }


    class ShoeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        interface OnShoeItemClickListener {
            fun onShoeItemClick(item: Shoe)
        }

    }
}