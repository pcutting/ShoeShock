package com.nevie.shoeshock.models

import com.nevie.shoeshock.repositories.ShoeRepository
import org.junit.Assert
import org.junit.Test

class CartUnitTest {

    @Test
    fun `Direct 10 units of tazon 6 cross trainer cost 505 point 10 at 50 point 51 each`() {
        Cart.clearCart()
        val shoes = ShoeRepository.getShoes()
        Cart.addToCard(ShoeItem(shoes[0],8.0,10))
//        Cart.getCart()[0].plus(9)
        Assert.assertEquals("SubTotal: $505.10", Cart.getCart()[0].getFormattedSubTotalAsString())
    }


    @Test
    fun `adding 9, to get 10 units of tazon 6 cross trainer cost 505 point 10 at 50 point 51 each`() {
        Cart.clearCart()
        val shoes = ShoeRepository.getShoes()
        Cart.addToCard(ShoeItem(shoes[0],8.0,1))
        Cart.getCart()[0].plus(9)
        Assert.assertEquals("SubTotal: $505.10", Cart.getCart()[0].getFormattedSubTotalAsString())
    }


    @Test
    fun `adding 1 9 times to get 10 units of tazon 6 cross trainer cost 505 point 10 at 50 point 51 each`() {
        //Simulates the add button
        Cart.clearCart()
        val shoes = ShoeRepository.getShoes()
        Cart.addToCard(ShoeItem(shoes[0],8.0,1))
        Cart.getCart()[0].plus(1)
        Cart.getCart()[0].plus(1)
        Cart.getCart()[0].plus(1)
        Cart.getCart()[0].plus(1)
        Cart.getCart()[0].plus(1)
        Cart.getCart()[0].plus(1)
        Cart.getCart()[0].plus(1)
        Cart.getCart()[0].plus(1)
        Cart.getCart()[0].plus(1)
        Assert.assertEquals("SubTotal: $505.10", Cart.getCart()[0].getFormattedSubTotalAsString())
    }

    @Test
    fun `adding 2 units first catalog shoe and adding 2 of second catalog shoe and removing one of first`() {
        //Simulates the add button
        Cart.clearCart()
        val shoes = ShoeRepository.getShoes()
        Cart.addToCard(ShoeItem(shoes[0],8.0,1))
        Cart.getCart()[0].plus(1)

        Cart.addToCard(ShoeItem(shoes[1],7.0,1))
        Cart.getCart()[1].plus(1)

        Cart.getCart()[0].minus(1)
        Assert.assertEquals("224.51", Cart.getCartValue().toString())
    }

}
