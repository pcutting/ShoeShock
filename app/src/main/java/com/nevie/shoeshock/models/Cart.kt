package com.nevie.shoeshock.models



data class ShoeItem(
    var shoe: Shoe,
    var size: Double,
    var quantity: Int
)

data class AppliedDiscount(
    var discount : Discount
    var
)



data class Cart (
    var shoeItems : MutableList<ShoeItem> = mutableListOf(),
    var discounts : MutableList<Discount> = mutableListOf(),



)

