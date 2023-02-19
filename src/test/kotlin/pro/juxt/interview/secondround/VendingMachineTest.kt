package pro.juxt.interview.secondround

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import pro.juxt.interview.secondround.CoinSystemType.*

class VendingMachineTest {

    companion object {
        private val canonicalCoinSystem = CoinSystem(
            setOf(
                Coin(1),
                Coin(2),
                Coin(5),
                Coin(10),
                Coin(20),
                Coin(50),
                Coin(100),
                Coin(200)
            )
        )

        private val nonCanonicalCoinSystem = CoinSystem(
            setOf(
                Coin(5),
                Coin(10),
                Coin(20),
                Coin(25)
            )
        )
    }

    @Test
    fun `can be initialized with coins`() {
        val vendingMachine = VendingMachine(canonicalCoinSystem)

        val expectedDenominations = listOf(
            Coin(1),
            Coin(2),
            Coin(5),
            Coin(10),
            Coin(20),
            Coin(50),
            Coin(100),
            Coin(200)
        )

        vendingMachine.coinSystem.coins shouldBe expectedDenominations
    }

    @Nested
    @DisplayName("when change can be given in full")
    inner class OptimalChangeTests {
        @Test
        fun `should compute optimal change for a canonical coin system`() {
            val changeToGive = Amount(500)

            val vendingMachine = VendingMachine(canonicalCoinSystem)
            val change = vendingMachine.dispenseChange(changeToGive)

            val expectedCoins = mapOf(
                Coin(200) to Amount(2),
                Coin(100) to Amount(1)
            )

            with(change) {
                coins shouldBe expectedCoins
                remaining.value shouldBe 0
                complete.shouldBeTrue()
            }
        }

        @Test
        fun `should compute optimal change for a non-canonical coin system`() {
            val changeToGive = Amount(40)

            val vendingMachine = VendingMachine(nonCanonicalCoinSystem)
            val change = vendingMachine.dispenseChange(changeToGive)

            val expectedCoins = mapOf(
                Coin(20) to Amount(2),
            )

            with(change) {
                coins shouldBe expectedCoins
                remaining.value shouldBe 0
                complete.shouldBeTrue()
            }
        }

    }

    @Nested
    @DisplayName("when change cannot be given in full")
    inner class PartialChangeTests {

        @Test
        fun `allows partial change for canonical system`() {
            val changeToGive = Amount(8)

            val vendingMachine = VendingMachine(CoinSystem(setOf(Coin(5)), type = CANONICAL))
            val change = vendingMachine.dispenseChange(changeToGive)

            val expectedCoins = mapOf(
                Coin(5) to Amount(1)
            )

            with(change) {
                coins shouldBe expectedCoins
                remaining.value shouldBe 3
                complete.shouldBeFalse()
            }
        }

        @Test
        fun `allows partial change for non-canonical system`() {
            val changeToGive = Amount(8)

            val vendingMachine = VendingMachine(nonCanonicalCoinSystem)
            val change = vendingMachine.dispenseChange(changeToGive)

            val expectedCoins = mapOf(
                Coin(5) to Amount(1)
            )

            with(change) {
                coins shouldBe expectedCoins
                remaining.value shouldBe 3
                complete.shouldBeFalse()
            }
        }

        @Test
        fun `allows empty change`() {
            val changeToGive = Amount(3)

            val vendingMachine = VendingMachine(nonCanonicalCoinSystem)
            val change = vendingMachine.dispenseChange(changeToGive)

            val expectedCoins = mapOf<Coin, Amount>()

            with(change) {
                coins shouldBe expectedCoins
                remaining.value shouldBe 3
                complete.shouldBeFalse()
            }
        }
    }
}
