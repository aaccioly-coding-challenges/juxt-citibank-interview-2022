package pro.juxt.interview.secondround

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
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
    fun `can compute optimal change for a canonical coin system`() {
        val changeToGive = Amount(500)

        val vendingMachine = VendingMachine(canonicalCoinSystem)
        val change = vendingMachine.dispenseChange(changeToGive)

        val expectedCoins = mapOf(
            Coin(200) to Amount(2),
            Coin(100) to Amount(1)
        )

        with(change) {
            coins shouldBe expectedCoins
            remainingChange.value shouldBe 0
            completeChange.shouldBeTrue()
        }
    }

    @Test
    fun `allows partial change`() {
        val changeToGive = Amount(8)

        val vendingMachine = VendingMachine(setOf( Coin(5)))
        val change = vendingMachine.dispenseChange(changeToGive)

        val expectedCoins = mapOf(
            Coin(5) to Amount(1)
        )

        with(change) {
            coins shouldBe expectedCoins
            remainingChange.value shouldBe 3
            completeChange.shouldBeFalse()
        }
    }

}
