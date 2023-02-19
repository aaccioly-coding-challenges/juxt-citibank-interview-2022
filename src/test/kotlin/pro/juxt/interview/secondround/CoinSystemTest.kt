package pro.juxt.interview.secondround

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.jupiter.api.Test
import pro.juxt.interview.secondround.CoinSystemType.*

class CoinSystemTest {

    @Test
    fun `can detect when a coin system is canonical`() {
        val canonicalCoinSet = setOf(
            Coin(1),
            Coin(5),
            Coin(10),
            Coin(25),
            Coin(50),
            Coin(100),
        )

        val coinSystem = CoinSystem(canonicalCoinSet)

        coinSystem.isCanonical.shouldBeTrue()
    }

    @Test
    fun `can detect when a coin system is non-canonical`() {
        val nonCanonicalCoinSet = setOf(
            Coin(1),
            Coin(5),
            Coin(10),
            Coin(20),
            Coin(25)
        )

        val coinSystem = CoinSystem(nonCanonicalCoinSet)

        coinSystem.isCanonical.shouldBeFalse()
    }

    @Test
    fun `can manually specify a coin system type`() {
        val coins = setOf(Coin(1))

        val coinSystemSetToAuto = CoinSystem(coins, type = AUTO)
        coinSystemSetToAuto.isCanonical.shouldBeTrue()

        val coinSystemSetToCanonical = CoinSystem(coins, type = CANONICAL)
        coinSystemSetToCanonical.isCanonical.shouldBeTrue()

        val coinSystemSetToNonCanonical = CoinSystem(coins, type = NON_CANONICAL)
        coinSystemSetToNonCanonical.isCanonical.shouldBeFalse()
    }

}
