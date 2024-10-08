package pro.juxt.interview.secondround

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.shouldForAtLeastOne
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.set
import io.kotest.property.assume
import io.kotest.property.checkAll

@Suppress("unused")
class ChangeMakingAlgorithmProperties : StringSpec({

    "greedy algorithm should be optimal for a canonical coin system" {
        checkAll(
            Arb.set(Arb.positiveInt(max = 10).map { Coin(it) }, range = 1..10),
            Arb.positiveInt(max = 200).map { Amount(it) },
        ) { coinSet, amount ->

            val coinSystem = CoinSystem.create(coinSet)

            when (coinSystem) {
                is CanonicalCoinSystem -> collect(CoinSystemType.CANONICAL)
                is NonCanonicalCoinSystem -> collect(CoinSystemType.NON_CANONICAL)
            }

            if (coinSystem is CanonicalCoinSystem) {
                val coins = coinSystem.coins
                greedy(coins, amount) shouldBe optimal(coins, amount)
            }
        }
    }

    "greedy algorithm isn't optimal for at least one amount in a non-canonical coin system" {
        checkAll(
            Arb.set(Arb.positiveInt(max = 15).map { Coin(it) }, range = 1..10),
        ) { coinSet ->

            val coinSystem = CoinSystem.create(coinSet)

            assume(coinSystem is NonCanonicalCoinSystem)

            val coins = coinSystem.coins
            (1..2 * coins.last().denomination)
                .map { Amount(it) }
                .shouldForAtLeastOne { amount ->
                    greedy(coins, amount) shouldNotBe optimal(coins, amount)
                }

        }
    }
})
