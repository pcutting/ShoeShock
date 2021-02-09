package com.nevie.shoeshock.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.*

data class Shoe(
    val brand: String,
    var labelOfShoe: String,
    var modelName:String,
    var description : String,
    var price : String,
    var sizesAvailableList : MutableList<SizeAvailability> = mutableListOf(),
    var currency: String = "USD",
    var sex: Sex,
    var discounts: MutableList<Discount> = mutableListOf(),
    var images : MutableList<Int> = mutableListOf()
)

enum class Sex(val label:String){
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
    var discountClass : DiscountType = DiscountType.AUTOMATICALLY_APPLIES_TO_ALL
){
    fun isAutomaticallyAppliedToAllItemsInCart() = (discountClass== DiscountType.AUTOMATICALLY_APPLIES_TO_ALL)

    fun willVoidAllOtherPurchase() = (discountClass == DiscountType.STAND_ALONE_AND_WILL_VOIDS_ALL_OTHERS)


    @RequiresApi(Build.VERSION_CODES.O)
    fun verifiedCodeIfNeeded(attemptedCodes: List<String>) =
        (discountClass == DiscountType.APPLIED_BY_CODE &&
                attemptedCodes.contains(code) &&
                (startDate == null ||
                    endDate == null ||
                    (startDate..endDate).contains(LocalDate.now())
                )
        )

}

enum class DiscountType {
    APPLIED_BY_CODE,
    AUTOMATICALLY_APPLIES_TO_ALL,
    STAND_ALONE_AND_WILL_VOIDS_ALL_OTHERS
}

data class SizeAvailability(
    var size: Double,
    var quantity: Int = 0
)

