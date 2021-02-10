package com.nevie.shoeshock.repositories

import com.nevie.shoeshock.R
import com.nevie.shoeshock.models.Sex
import com.nevie.shoeshock.models.Shoe
import com.nevie.shoeshock.models.SizeAvailability

class ShoeRepository {


}



fun buildCatelog(): MutableList<Shoe>{
    var shoes = mutableListOf<Shoe>()

    shoes.add(Shoe(
        "Puma",
        "Men's Tazon 6 Ii Cross-Trainer Sneaker",
        "MTazon6Ii",
        "50.51",
        """
            A sleek, streamlined silhouette sets the Tazon 6 apart from the rest. Lace up the sixth edition of the PUMA running-inspired sneaker and enjoy a comfortable and stable feel.
            Color: Puma Black-ultra Gray
            100% Synthetic
            Imported
            Rubber sole
            Run-Train Performance Sneaker
            Product details
            Package Dimensions : 12.05 x 7.52 x 4.37 inches; 1.57 Pounds
            Item model number : 19413701
            Department : Mens
            Date First Available : January 14, 2020
            Manufacturer : PUMA
            ASIN : B083TR2QQC
        """.trimIndent(),
        mutableListOf<SizeAvailability>(
            SizeAvailability(7.0,210),
            SizeAvailability(7.5,210),
            SizeAvailability(8.0,210),
            SizeAvailability(8.5,210),
            SizeAvailability(9.0,210),
            SizeAvailability(10.0,210)),
        "USD",
        Sex.MALE,
        listOf<Int>(
            R.drawable.puma1_black_sneaker_0,
            R.drawable.puma1_black_sneaker_1,
            R.drawable.puma1_black_sneaker_2,
            R.drawable.puma1_black_sneaker_3)





        )



    )

    )

}