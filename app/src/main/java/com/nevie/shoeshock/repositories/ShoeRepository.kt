package com.nevie.shoeshock.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.nevie.shoeshock.R
import com.nevie.shoeshock.models.*
import java.time.LocalDate

data class ShoeRepository(
    private var shoes : MutableList<Shoe>? = null,
    private var discounts : MutableList<Discount>? = null
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getShoes() = shoes ?: buildShoesCatalog()


    @RequiresApi(Build.VERSION_CODES.O)
    fun getDiscounts() = discounts ?: buildPromotions()


    fun getSizes(shoe: Shoe):List<Double>{
        return shoe.sizesAvailableMap.filter { (key,value) -> value > 0 }.keys.toList()
    }


}


@RequiresApi(Build.VERSION_CODES.O)
private fun buildShoesCatalog(): MutableList<Shoe>{
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
        mutableMapOf(
            7.0 to 210,
            7.5 to 210,
            8.0 to 210,
            8.5 to 210,
            9.0 to 210,
            10.0 to 210),
        "USD",
        Sex.MALE,
        mutableListOf<Int>(
            R.drawable.puma1_black_sneaker_0,
            R.drawable.puma1_black_sneaker_1,
            R.drawable.puma1_black_sneaker_2,
            R.drawable.puma1_black_sneaker_3,
            R.drawable.puma1_black_sneaker_4,
            R.drawable.puma1_black_sneaker_5
        )

    ))
    //Nike Women's Revolution 5 Running Shoe
    shoes.add(Shoe(
        "Nike",
        "Nike Women's Revolution 5 Running Shoe",
        "WRev5Runner",
        "87.00",
        """
            Many color combinations available.
            Imported
            Synthetic sole
            Shaft measures approximately low-top from arch
            REVOLUTIONARY COMFORT: These Nike women's running shoes have been designed with lightweight material and a soft foam midsole, built to keep you running in comfort.
            BREATHABLE SUPPORT: These Nike women's shoes are built with a lightweight knit textile that wraps your foot in breathable comfort. A reinforced heel and no-sew overlays lend support and durability.
            LIGHTWEIGHT CUSHIONING: The soft foam midsole delivers a smooth, stable ride. The textured outer wall of these women's running shoes help reduce weight and hide creases.
            DURABLE, FLEXIBLE TRACTION: The rubber outsole of these Nike women's shoes offer durable traction on a variety of surfaces. Spaces in the tread let your foot flex naturally.
            NIKE WOMEN'S RUNNING SHOE: Imported, fabric: lightweight knit, plush padding, soft foam midsole, and rubber outsole.

        """.trimIndent(),
        mutableMapOf(
            7.0 to 10,
            7.5 to 2,
            8.0 to 2,
            8.5 to 10,
            9.0 to 20,
            10.0 to 6),
        "USD",
        Sex.MALE,
        mutableListOf(
            R.drawable.nice_women_revolution_5_run0,
            R.drawable.nice_women_revolution_5_run1,
            R.drawable.nice_women_revolution_5_run2,
            R.drawable.nice_women_revolution_5_run3,
            R.drawable.nice_women_revolution_5_run4,
            R.drawable.nice_women_revolution_5_run5,
            R.drawable.nice_women_revolution_5_run6
        )
    ))

    //
    shoes.add(Shoe(
        "Nike",
        "Mens Flex Experience Run 8",
        "MenFlexExpert8",
        "74.10",
        """
           The Nike Flex Experience RN 8 running shoe delivers lightweight comfort that conforms to your every step. Soft knit material hugs your foot, while flex grooves in the outsole encourage an adaptive ride that's ready for wherever your route takes you.
           Textile
           Imported
           Synthetic sole
           Shaft measures approximately low-top from arch
           RUNNING SHOES FOR MEN: The Nike Flex Experience RN 8 running shoe delivers lightweight comfort with a knit fabric that conforms to your every step.
           COMFORTABLE FIT: Men's Nike shoes feature flex grooves in the outsole for natural flexibility and a soft mesh in the heel for an adaptive fit while running.
           DURABLE DESIGN: A no-sew overlay at the laces and toe tip adds durability to our men's running shoes while textured outsole provides additional traction and durability.
           RESILIENT RIDE: Injected unit sole midsole provides a resilient ride and enough durability to double as an outsole. This allows for a reduction in rubber and overall weight on your running shoes.
           RUN FREELY: Hexagonal flex grooves offer 6 different flex points for running freely in your Nike running shoes and the rounded heel rolls with the ground, promoting a natural range of motion.
        """.trimIndent(),
        mutableMapOf(
            7.0 to 30,
            7.5 to 30,
            8.0 to 30,
            8.5 to 30,
            9.0 to 30,
            10.0 to 30),
        "USD",
        Sex.MALE,
        mutableListOf(
            R.drawable.nike_mens_flex_run_8_0,
            R.drawable.nike_mens_flex_run_8_1,
            R.drawable.nike_mens_flex_run_8_2,
            R.drawable.nike_mens_flex_run_8_3,
            R.drawable.nike_mens_flex_run_8_4
        )
    ))
// Goobon Air Men tennis gym
    shoes.add(Shoe(
        "Goobon",
        "Air Shoes for Men Tennis",
        "AirMenTennisGym",
        "33.99",
        """
         Tennis
         Imported
         Rubber sole
         Platform measures approximately .50"
         【Air cushion sole】 : The air cushion absorb vibration, provid better support and gives a bounce to your step which makes these sneakers perfect for running.
         【Non slip】 : Rubber soles are non slip and wear-resistant. Even if you run on the smooth marble floor, you don't have to worry about it. They will firmly grasp the ground.
         【A pair of breathing shoes】 : Skin friendly fabrics offer a snug, sock-like fit, it will not cause friction and blistering and allows the feet to breathe freely during exercise.
         【Applicable Scene】 : Perfect for sports, athletic, workout, gym, walking, jogging, traveling,running, and work for every season.
         【Service】 : Shipped from United states, and we offer a 100% no reason to return refun.
        """.trimIndent(),
        mutableMapOf(
            7.0 to 30,
            7.5 to 30,
            8.0 to 30,
            8.5 to 30,
            9.0 to 30,
            10.0 to 30),
        "USD",
        Sex.MALE,
        mutableListOf(
            R.drawable.goobon_air_shoes_men_tenis0,
            R.drawable.goobon_air_shoes_men_tenis1,
            R.drawable.goobon_air_shoes_men_tenis2,
            R.drawable.goobon_air_shoes_men_tenis3,
            R.drawable.goobon_air_shoes_men_tenis4,
            R.drawable.goobon_air_shoes_men_tenis5
        )
    ))

    //
    shoes.add(Shoe(
        "Orthofeet",
        "Shoe with Proven Heel and Foot Pain Relief",
        "613M070",
        "129.95",
        """
         GUARANTEED COMFORT! Test our shoes for up to 60 DAYS and see for yourself why Orthofeet is regarded by millions as the WORLD'S MOST COMFORTABLE and stylish orthopedic shoes. Try RISK FREE: Walk COMFORTABLY and PAIN FREE or Your MONEY BACK. 60-day wear test. FREE shipping and returns.
         PLANTAR FASCIITIS? HEEL PAIN? FOOT PAIN? NEUROPATHY? Orthofeet orthotic shoes offer the BEST ORTHOPEDIC SHOE solution. Lightweight SOLE with ERGONOMIC design and SUPERIOR CUSHIONING along with premium ORTHOTIC INSOLES that feature ANATOMICAL ARCH SUPPORT and multiple CUSHIONING layers provide soft, PILLOW-LIKE SUPPORT that works wonders to enhance COMFORT and EASE PAIN on the FOOT and HEEL, all the way up your KNEES, HIPS and LOWER BACK caused by flat feet, overpronation and arthritis.
         EXTENDED WIDTHS - These SUPER COMFORTABLE men’s casual shoes with soft, non-binding uppers are available in Medium, Wide and Extra Wide widths, offering a PERFECT FIT. The UNIQUE THERAPEUTIC DESIGN helps alleviate stress on the joints, enhances stability and substantially improves the ease of walking.
         SENSITIVE FEET? DIABETES? ARTHRITIS? NEUROPATHY? Soft uppers with smooth interior lining and extra foam padding provide SUPERIOR COMFORT and protection for sensitive feet and an ideal footwear solution as men’s diabetic shoes, men’s arthritis shoes and men’s shoes for neuropathy.
         BUNIONS? HAMMERTOES? A wide & roomy TOE BOX offers a comfortable, non-binding fit for the front part of the foot and toes, and eases pressure on bunions, hammertoes Morton’s Neuroma & swollen feet. ORTHOTIC FRIENDLY: Extra depth design with removable insoles (¼” thick in the forefoot area) provides ample space for custom made orthotics.
        """.trimIndent(),
        mutableMapOf(
            7.0 to 30,
            7.5 to 30,
            8.0 to 30,
            8.5 to 30,
            9.0 to 30,
            10.0 to 30),
        "USD",
        Sex.MALE,
        mutableListOf(
            R.drawable.orthofeet_613m070_0,
            R.drawable.orthofeet_613m070_1,
            R.drawable.orthofeet_613m070_2,
            R.drawable.orthofeet_613m070_3,
            R.drawable.orthofeet_613m070_4
        )
    ))


    //
    shoes.add(Shoe(
        "Orthofeet",
        "Casual wear with Proven Heel and Foot Pain Relief",
        "B07CMYXMS7",
        "99.95",
        """
         100% Suede
         Foam sole
         GUARANTEED COMFORT! Test our shoes for up to 60 DAYS and see for yourself why Orthofeet is regarded by millions as the WORLD'S MOST COMFORTABLE and stylish orthopedic shoes. Try RISK FREE: Walk COMFORTABLY and PAIN FREE or Your MONEY BACK. 60-day wear test. FREE shipping and returns.
         PLANTAR FASCIITIS? HEEL PAIN? FOOT PAIN? NEUROPATHY? Orthofeet orthotic shoes offer the BEST ORTHOPEDIC SHOE solution. Lightweight SOLE with ERGONOMIC design and SUPERIOR CUSHIONING along with premium ORTHOTIC INSOLES that feature ANATOMICAL ARCH SUPPORT and multiple CUSHIONING layers provide soft, PILLOW-LIKE SUPPORT that works wonders to enhance COMFORT and EASE PAIN on the FOOT and HEEL, all the way up your KNEES, HIPS and LOWER BACK caused by flat feet, overpronation and arthritis.
         EXTENDED WIDTHS - These SUPER COMFORTABLE men’s casual shoes with soft, non-binding uppers are available in Medium, Wide and Extra Wide widths, offering a PERFECT FIT. The UNIQUE THERAPEUTIC DESIGN helps alleviate stress on the joints, enhances stability and substantially improves the ease of walking.
         SENSITIVE FEET? DIABETES? ARTHRITIS? NEUROPATHY? Soft uppers with smooth interior lining and extra foam padding provide SUPERIOR COMFORT and protection for sensitive feet and an ideal footwear solution as men’s diabetic shoes, men’s arthritis shoes and men’s shoes for neuropathy.
         BUNIONS? HAMMERTOES? A wide & roomy TOE BOX offers a comfortable, non-binding fit for the front part of the foot and toes, and eases pressure on bunions, hammertoes Morton’s Neuroma & swollen feet. ORTHOTIC FRIENDLY: Extra depth design with removable insoles (¼” thick in the forefoot area) provides ample space for custom made orthotics.
        """.trimIndent(),
        mutableMapOf(
            7.0 to 30,
            7.5 to 30,
            8.0 to 30,
            8.5 to 30,
            9.0 to 30,
            10.0 to 30),
        "USD",
        Sex.MALE,
        mutableListOf(
            R.drawable.orthofeet_b07_0,
            R.drawable.orthofeet_b07_1,
            R.drawable.orthofeet_b07_2,
            R.drawable.orthofeet_b07_3,
            R.drawable.orthofeet_b07_4
        )
    ))
    return shoes
}


@RequiresApi(Build.VERSION_CODES.O)
fun buildPromotions(): MutableList<Discount>{
    var promos = mutableListOf<Discount>()

    promos.add(
        Discount(
            LocalDate.now().minusDays(20),
            LocalDate.now().plusDays(10),
            0.25,
            "",
            false,
            false,
            0,
            DiscountType.AUTOMATICALLY_APPLIES_TO_ALL
        )
    )
    promos.add(
        Discount(
            LocalDate.now().plusDays(20),
            LocalDate.now().plusDays(50),
            0.5,
            "",
            false,
            false,
            0,
            DiscountType.AUTOMATICALLY_APPLIES_TO_ALL
        )
    )


    return promos
}