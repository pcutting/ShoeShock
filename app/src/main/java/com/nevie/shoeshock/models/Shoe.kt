package com.nevie.shoeshock.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.math.RoundingMode
import java.time.LocalDate

data class Shoe(
    val brand: String,
    var labelOfShoe: String,
    var modelName:String,
    var price: String,
    var description: String,
    var sizesAvailableList: MutableList<SizeAvailability> = mutableListOf(),
    var currency: String = "USD",
    var sex: Sex,
    var images: MutableList<Int> = mutableListOf()

    ) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun priceAfterDiscounts(discounts: List<Discount>): String {
        var updatedPrice = price.toDoubleOrNull() ?: 0.0
        var maxDiscountFound = 0.0

        discounts.forEach { disc ->
            val discount = disc.rateOfCurrentDiscountForToday()

            if (discount > maxDiscountFound) {
                maxDiscountFound = discount
            }
        }

        updatedPrice  *= (1-maxDiscountFound)

        return updatedPrice.toBigDecimal().setScale(2,RoundingMode.DOWN).toString()
    }
}

enum class Sex(val label: String){
    MALE("Male"),
    FEMALE("Female"),
    UNISEX("Unisex"),
    CHILD("Child")
}

data class Discount(
    var startDate: LocalDate? = null,
    var endDate: LocalDate? = null,
    var discountPercentAsDouble: Double,
    var code: String = "",
    var isACouponWithCode:Boolean = false,
    var isAStandAloneDiscountAndWillVoidAllOtherDiscounts: Boolean = false,
    var priority_10_is_highest: Int = 0,
    var discountClass : DiscountType = DiscountType.AUTOMATICALLY_APPLIES_TO_ONLY_ONE_ITEM,
    var includesOnlyShoes: MutableList<Shoe>? = null,
    var excludesShoes: MutableList<Shoe>? = null,
    var brandDiscounted: String = ""
){
    @RequiresApi(Build.VERSION_CODES.O)
    fun rateOfCurrentDiscountForToday(attemptedCodes: List<String>? = null) : Double {
        var discountRate = 0.0

        if (attemptedCodes != null) {
            TODO("build out this fuctionality for coupon verification")
        }

        if (((startDate?.isBefore(LocalDate.now()) == true &&
                    endDate?.isAfter(LocalDate.now()) == true))) {
            if(discountClass == DiscountType.AUTOMATICALLY_APPLIES_TO_ALL) {
                discountRate = discountPercentAsDouble
            }
        }

        return discountRate
    }
}

enum class DiscountType {
    APPLIED_BY_CODE,
    AUTOMATICALLY_APPLIES_TO_ALL,
    AUTOMATICALLY_APPLIES_TO_ONLY_ONE_ITEM,
    STAND_ALONE_AND_WILL_VOIDS_ALL_OTHERS,
    NEVER_DISCOUNTED
}

data class SizeAvailability(
    var size: Double,
    var quantity: Int = 0,
    var unitOfMeasure: String = "US"
)

