package pro.juxt.interview.secondround

import pro.juxt.interview.secondround.CoinSystemType.*

enum class CoinSystemType {
    CANONICAL,
    NON_CANONICAL,
    AUTO
}

sealed class CoinSystem(val coins: List<Coin>) {

    companion object {
        @JvmStatic
        fun create(coinSet: Set<Coin>, type: CoinSystemType = AUTO): CoinSystem {

            val coins: List<Coin> = coinSet.sortedBy { it.denomination }

            fun detectIfCoinSystemIsCanonical(): Boolean {
                val maxCoin = coins.last()
                for (value in 1..2 * maxCoin.denomination) {
                    val amount = Amount(value)
                    val optimal = optimal(coins, amount)
                    val greedy = greedy(coins, amount)
                    if (optimal != greedy) {
                        return false
                    }
                }
                return true
            }

            return when (type) {
                CANONICAL -> CanonicalCoinSystem(coins)
                NON_CANONICAL -> NonCanonicalCoinSystem(coins)
                AUTO -> if (detectIfCoinSystemIsCanonical()) {
                    CanonicalCoinSystem(coins)
                } else {
                    NonCanonicalCoinSystem(coins)
                }
            }
        }
    }

    init {
        require(coins.isNotEmpty()) { "Coin system must contain at least one coin" }
    }

    abstract fun makeAmountInCoins(amount: Amount): AmountInCoins
}

class CanonicalCoinSystem(coins: List<Coin>) : CoinSystem(coins) {
    override fun makeAmountInCoins(amount: Amount): AmountInCoins {
        return greedy(coins, amount)
    }
}

class NonCanonicalCoinSystem(coins: List<Coin>) : CoinSystem(coins) {
    override fun makeAmountInCoins(amount: Amount): AmountInCoins {
        return optimal(coins, amount)
    }
}
