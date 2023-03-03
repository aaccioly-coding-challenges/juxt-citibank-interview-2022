package pro.juxt.interview.secondround

import io.kotest.matchers.types.shouldBeTypeOf
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

        val coinSystem = CoinSystem.create(canonicalCoinSet)

        coinSystem.shouldBeTypeOf<CanonicalCoinSystem>()
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

        val coinSystem = CoinSystem.create(nonCanonicalCoinSet)

        coinSystem.shouldBeTypeOf<NonCanonicalCoinSystem>()
    }

    @Test
    fun `can manually specify a coin system type`() {
        val coins = setOf(Coin(1))

        val coinSystemSetToAuto = CoinSystem.create(coins, type = AUTO)
        coinSystemSetToAuto.shouldBeTypeOf<CanonicalCoinSystem>()

        val coinSystemSetToCanonical = CoinSystem.create(coins, type = CANONICAL)
        coinSystemSetToCanonical.shouldBeTypeOf<CanonicalCoinSystem>()

        val coinSystemSetToNonCanonical = CoinSystem.create(coins, type = NON_CANONICAL)
        coinSystemSetToNonCanonical.shouldBeTypeOf<NonCanonicalCoinSystem>()
    }

}
