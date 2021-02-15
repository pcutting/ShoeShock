package com.nevie.shoeshock.models

import android.content.Context
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.RoundingMode

private const val TAG = "Cart.kt"

@Parcelize
data class ShoeItem(
    var shoe: Shoe,
    var size: Double,
    var quantity: Int
) : Parcelable {
    fun getSubTotal()= shoe.price.toDouble() * quantity

    fun getFormattedSubTotalAsString():String = "SubTotal: $${getSubTotal().toBigDecimal().setScale(2,RoundingMode.HALF_UP)}"

    fun getDetails():String {
        return "${shoe.brand} ${shoe.name} pairs: $quantity for $${shoe.price} SubTotal: $${getSubTotal()}"
    }

    fun plus(addNumberToOrder : Int, context:Context? = null){
        var attemptedTotal = this.quantity + addNumberToOrder
        var numberToAddToCart = addNumberToOrder
        var toastMsg:String = ""
        val availableAtSize = shoe.sizesAvailableMap[size] ?: 0
        val canAdd: Boolean = when {
            availableAtSize <= 0 -> {
                //Log.d(TAG, "addToCart(): There are zero in inventory to add. Wanted ${shoeItem.quantity}")
                toastMsg = "We are out of that size, please choose another size or shoe."
                false
            }

            attemptedTotal > availableAtSize -> {
                //Log.d(TAG, "addToCart(): Not enough shoes in inventory to add ${shoeItem.quantity}, had to add a reduced amount ($availableAtSize)")
                toastMsg =  "Not enough shoes in inventory to add ${quantity}, had to add a reduced amount ($availableAtSize)"
                numberToAddToCart = availableAtSize - quantity
                true
            }

            attemptedTotal <= availableAtSize -> {

                //Log.d(TAG, "addToCart(): added ${shoeItem.quantity} @ ${shoeItem.shoe}: size: ${shoeItem.size}")
                true
            }
            else ->  {
                //Log.d(TAG, "There was some error in adding items to your cart")
                //Toast.makeText(this,"There was an error in adding items to your cart", Toast.LENGTH_SHORT).show()
                toastMsg = "Unknown error.  Please try again."
                false
            }
        }

        if (context != null) {
//            try {
//                Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
//            } catch (e: Exception) {
//                Log.d(TAG, "someone tried to call this function without property defining a context")
//            }
        }

        if (canAdd) {
            this.quantity += numberToAddToCart
        }
    }

    fun minus(subtractNumberFromOrder : Int){
        this.quantity -= subtractNumberFromOrder
        if (quantity < 0) {
            quantity = 0
        }
    }
}

enum class CartAction(val namedValue: String) {
    ADD_ONE("add"),
    SUBTRACT_ONE("subtract"),
    CHANGE_SIZE("change_size")
}

@Parcelize
object Cart : Parcelable{

    private var shoeItems : MutableList<ShoeItem> = mutableListOf()

    fun clearCart(){
        shoeItems = mutableListOf()
    }

    fun getCart() = shoeItems

    fun getCartValue() =
        shoeItems.sumByDouble { it.getSubTotal() }.toBigDecimal()
        .setScale(2, RoundingMode.HALF_UP).toDouble()

    fun getCartSummaryText() = shoeItems.joinToString("\n") { it.getDetails() }

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
                //Log.d(TAG, "addToCart(): There are zero in inventory to add. Wanted ${shoeItem.quantity}")
                //Toast.makeText(this,"We couldn't add ${shoeItem.quantity} to the card, we only have ${availableAtSize} available.  We will add that many to your cart.",Toast.LENGTH_LONG).show()
                true
            }

            shoeItem.quantity > availableAtSize -> {
                //Log.d(TAG, "addToCart(): Not enough shoes in inventory to add ${shoeItem.quantity}, had to add a reduced amount ($availableAtSize)")
                shoeItem.quantity = availableAtSize
                shoeItems.add(shoeItem)
                //Toast.makeText(this,"We couldn't add ${shoeItem.quantity} to the card, we only have ${availableAtSize} available.  We will add that many to your cart.", Toast.LENGTH_SHORT).show()
                true
            }

            shoeItem.quantity <= availableAtSize -> {
                shoeItems.add(shoeItem)
                //Log.d(TAG, "addToCart(): added ${shoeItem.quantity} @ ${shoeItem.shoe}: size: ${shoeItem.size}")
                true
            }
            else ->  {
                //Log.d(TAG, "There was some error in adding items to your cart")
                //Toast.makeText(this,"There was an error in adding items to your cart", Toast.LENGTH_SHORT).show()
                false
                }
        }
    }
}

