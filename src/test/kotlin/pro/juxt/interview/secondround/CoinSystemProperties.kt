package pro.juxt.interview.secondround

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.set
import io.kotest.property.checkAll

@Suppress("unused")
class CoinSystemProperties : StringSpec({

    "the greedy algorithm should be optimal for a canonical coin system" {
        checkAll(
            Arb.set(Arb.positiveInt(max = 10).map { Coin(it) }, range = 1..10),
            Arb.positiveInt(max = 200).map { Amount(it) },
        ) { coins, amount ->

            val coinSystem = CoinSystem(coins)

            when (coinSystem.isCanonical) {
                true -> collect(CoinSystemType.CANONICAL)
                false -> collect(CoinSystemType.NON_CANONICAL)
            }

            if (coinSystem.isCanonical) {
                coinSystem.greedy(amount) shouldBe coinSystem.optimal(amount)
            }
        }
    }
})
