package com.nevie.shoeshock.models



data class ShoeItem(
    var shoe: Shoe,
    var size: Double,
    var quantity: Int
)



data class Cart (
    var shoeItems : MutableList<ShoeItem> = mutableListOf()

)

