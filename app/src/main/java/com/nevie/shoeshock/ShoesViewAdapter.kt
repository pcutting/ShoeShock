package com.nevie.shoeshock

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ShoeItemBinding
import com.nevie.shoeshock.models.Cart
import com.nevie.shoeshock.models.Shoe
import com.nevie.shoeshock.models.ShoeItem


private const val TAG = "ClickableRecyclerViewAd"

class ShoesViewAdapter(private val shoes: MutableList<Shoe>,
                       private val onClicked: (Shoe, Boolean) -> Unit
): RecyclerView.Adapter<ShoesViewAdapter.ShoesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewItem: Int): ShoesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ShoeItemBinding.inflate(layoutInflater, parent, false)
        return ShoesViewHolder(binding)
    }

    override fun getItemCount() = shoes.size

    override fun onBindViewHolder(holder: ShoesViewHolder, position: Int) {


        holder.bind(shoes[position])

        holder.itemView.setOnClickListener {
            onClicked(shoes[position], false)
        }
    }

    class ShoesViewHolder(private val binding: ShoeItemBinding
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener,
    PopupMenu.OnMenuItemClickListener{

        override fun onClick(view: View) {
            showPopupMenu(view)
        }

        private fun showPopupMenu(view: View) {
            val popupMenu = PopupMenu(view.context,view)
            popupMenu.inflate(R.menu.pop_up_menu_sizes)
            if(myShoe != null) {
                myShoe.getSizes().forEach {
                    popupMenu.menu.add(it.toString())
                }
            }
            popupMenu.setOnMenuItemClickListener(this)
            popupMenu.show()
        }

        override fun onMenuItemClick(item: MenuItem) : Boolean {
            Log.d(TAG, "onMenuItemClick in ShoesViewHolder: $item")
            //super.onMenuItemClick(item)

            val shoeItem = ShoeItem(myShoe,item.toString().toDoubleOrNull() ?: myShoe.getSizes().first() ,1)

            Cart.addToCard(shoeItem)

            val intent = Intent(itemView.context, CartActivity::class.java)
            itemView.context.startActivity(intent)

            return true
        }

        private lateinit var myShoe : Shoe

        fun bind(shoe:Shoe) {
            myShoe = shoe
            val priceWithSymbol = "$${(shoe.price)}"

            binding.apply {
                modelNameLabel.text = shoe.name
                shoeImage.setImageResource(shoe.images.first())
                priceLabel.text = priceWithSymbol
                brandLabel.text = shoe.brand

                heartImage.setOnClickListener(this@ShoesViewHolder)
            }
        }
    }
}