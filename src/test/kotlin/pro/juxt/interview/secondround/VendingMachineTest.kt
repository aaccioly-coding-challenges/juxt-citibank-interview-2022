package pro.juxt.interview.secondround

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class VendingMachineTest {

     companion object{
         private val canonicalCoinSystem = setOf(
             Coin(1),
             Coin(2),
             Coin(5),
             Coin(10),
             Coin(20),
             Coin(50),
             Coin(100),
             Coin(200)
         )
     }


    @Test
    fun `can be initialized with coins`() {

        val vendingMachine = VendingMachine(canonicalCoinSystem)

        val expectedDenominations = listOf(
            Coin(200),
            Coin(100),
            Coin(50),
            Coin(20),
            Coin(10),
            Coin(5),
            Coin(2),
            Coin(1)
        )

        vendingMachine.availableCoins shouldBe expectedDenominations
    }

    @Test
    fun `can compute optimal change`() {
        val changeToGive = Amount(500)

        val vendingMachine = VendingMachine(canonicalCoinSystem)
        val change = vendingMachine.dispenseChange(changeToGive)

        val expectedChange = mapOf(
            Coin(200) to Amount(2),
            Coin(100) to Amount(1)
        )

        change shouldBe expectedChange
    }

}
