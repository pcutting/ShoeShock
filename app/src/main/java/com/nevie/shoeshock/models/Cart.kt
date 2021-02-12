package com.nevie.shoeshock.models

import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import kotlinx.android.parcel.Parcelize

private const val TAG = "Cart.kt"

@Parcelize
data class ShoeItem(
    var shoe: Shoe,
    var size: Double,
    var quantity: Int
) : Parcelable


enum class CartAction(val namedValue: String) {
    ADD_ONE("add"),
    SUBTRACT_ONE("subtract"),
    PURCHASE("purchase"),
    CHANGE_SIZE("change_size")
}
@Parcelize
object Cart : Parcelable{

    private var shoeItems : MutableList<ShoeItem> = mutableListOf()

    fun getCart() = shoeItems



    fun pairsOfShoes() : Int {
        var sum = 0
        shoeItems.forEach {
            sum += it.quantity
        }
        return sum
    }

    fun cartItemsCount() = shoeItems.size



    fun addToCard(shoeItem: ShoeItem):Boolean{
        val availableAtSize = shoeItem.shoe.sizesAvailableMap[shoeItem.size] ?: 0
        return when {
            availableAtSize <= 0 -> {
                Log.d(TAG, "addToCart(): There are zero in inventory to add. Wanted ${shoeItem.quantity}")
                //Toast.makeText(this,"We couldn't add ${shoeItem.quantity} to the card, we only have ${availableAtSize} available.  We will add that many to your cart.",Toast.LENGTH_LONG).show()
                true
            }

            shoeItem.quantity > availableAtSize -> {
                Log.d(TAG, "addToCart(): Not enough shoes in inventory to add ${shoeItem.quantity}, had to add a reduced amount ($availableAtSize)")
                shoeItem.quantity = availableAtSize
                shoeItems.add(shoeItem)
                //Toast.makeText(this,"We couldn't add ${shoeItem.quantity} to the card, we only have ${availableAtSize} available.  We will add that many to your cart.", Toast.LENGTH_SHORT).show()
                true
            }

            shoeItem.quantity <= availableAtSize -> {
                shoeItems.add(shoeItem)
                Log.d(TAG, "addToCart(): added ${shoeItem.quantity} @ ${shoeItem.shoe}: size: ${shoeItem.size}")

                true
            }
            else ->  {
                Log.d(TAG, "There was some error in adding items to your cart")
                //Toast.makeText(this,"There was an error in adding items to your cart", Toast.LENGTH_SHORT).show()
                false
                }
        }
    }
}

