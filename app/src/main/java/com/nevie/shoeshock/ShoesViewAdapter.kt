package com.nevie.shoeshock

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nevie.shoeshock.databinding.ShoeItemBinding
import com.nevie.shoeshock.models.Shoe

private const val TAG = "ClickableRecyclerViewAd"

class ShoesViewAdapter(private val shoes: MutableList<Shoe>,
                       private val onClick: (Shoe, Boolean) -> Unit)
    : RecyclerView.Adapter<ShoesViewAdapter.ShoesViewHolder>() {

    //private val shoes = mutableListOf<Shoe>()

//    fun setList(shoes: List<Shoe>) {
//        this.shoes.clear()
//        this.shoes.addAll(shoes)
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewItem: Int): ShoesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ShoeItemBinding.inflate(layoutInflater, parent, false)
        return ShoesViewHolder(binding)
    }

    override fun getItemCount() = shoes.size

    override fun onBindViewHolder(holder: ShoesViewHolder, position: Int) {
        holder.bind(shoes[position])

        // @Alan TODO verify .heart_image use is appropriate.  is there better way?
        holder.itemView.findViewById<ImageView>(R.id.heart_image).setOnClickListener {
            onClick(shoes[position], true)
        }

        holder.itemView.setOnClickListener {
            onClick(shoes[position], false)
        }



        //holder.itemView.findViewById<TextView>(R.id.discounted_price_label).text = shoes[position].priceAfterDiscounts()
//        holderClickableShoe.itemView.heart_image
//            .setOnClickListener {
//            shoeItemClickListener(shoes[position], true)
//            //Log.d(TAG, "Clicked on heart")
//            }
//
//        holderClickableShoe.itemView.setOnClickListener {
//            //Log.d(TAG, "Clicked outside of heart")
//            shoeItemClickListener(shoes[position], false)
//        }

        //val spinner = holderClickableShoe.itemView.findViewById<Spinner>(R.id.menu_spinner_for_sizes_in_options_menu)
        //val selectedSize = spinner?.selectedItem ?: 0.0
        val shoeSizesForMenu  = shoes[position].getSizes()
//        if (!shoeSizesForMenu.contains(selectedSize)){
//            holderClickableShoe.itemView.heart_image.setImageResource(R.drawable.ic_baseline_broken_image_24)
//        }

        //holderClickableShoe

        //var spinnerAdapter = ArrayAdapter<Double>(holderClickableShoe.itemView.context, android.R.layout.simple_list_item_1, shoeSizesForMenu)
        //cartItem.spinner_cart.adapter = spinnerAdapter

        //shoeSizesForMenu.indexOf(shoes[position].size)
        //Log.d(TAG, "$shoeSizesForMenu : shoe sizes available for ${shoeItems[position].shoe.name}")
        //cartItem.spinner_cart.setSelection(shoeSizesForMenu.indexOf(shoeItems[position].size))
    }

    class ShoesViewHolder(private val binding: ShoeItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(shoe:Shoe) {

            val priceWithSymbol = "$${(shoe.price)}"

            binding.apply {
                modelNameLabel.text = shoe.name
                shoeImage.setImageResource(shoe.images.first())
                priceLabel.text = priceWithSymbol
                brandLabel.text = shoe.brand


                //discounted_price_label).text = shoes[position].priceAfterDiscounts()

                heartImage.setOnClickListener {
                    //TODO
                    //shoeItemClickListener(shoe, true)

                }

                //outside of heart clicked
                root.setOnClickListener {
                    //TODO
                    //shoeItemClickListener(shoe, false)
                }

            }
        }

    }
}