package pro.juxt.interview.secondround

class CoinSystem(coins: Set<Coin>, type: CoinSystemType = CoinSystemType.AUTO) {
    init {
        require(coins.isNotEmpty()) { "Coin system must contain at least one coin" }
    }

    val coins: List<Coin> = coins.sortedBy { it.denomination }

    val isCanonical: Boolean = when (type) {
        CoinSystemType.CANONICAL -> true
        CoinSystemType.NON_CANONICAL -> false
        CoinSystemType.AUTO -> detectIfCoinSystemIsCanonical()
    }

    private fun detectIfCoinSystemIsCanonical(): Boolean {
        val maxCoin = coins.last()
        for (value in 1..2 * maxCoin.denomination) {
            val amount = Amount(value)
            val optimal = optimal(amount)
            val greedy = greedy(amount)
            if (optimal != greedy) {
                return false
            }
        }
        return true
    }

    fun makeAmountInCoins(amount: Amount): AmountInCoins {
        return if (isCanonical) {
            greedy(amount)
        } else {
            optimal(amount)
        }
    }

    internal fun greedy(changeToGive: Amount): AmountInCoins {
        var remainingChange = changeToGive.value
        val changeMap = mutableMapOf<Coin, Amount>()
        for (coin in coins.reversed()) {
            val numberOfCoinsForDenomination = remainingChange / coin.denomination
            if (numberOfCoinsForDenomination > 0) {
                changeMap[coin] = Amount(numberOfCoinsForDenomination)
                remainingChange -= coin.denomination * numberOfCoinsForDenomination
            }
            if (remainingChange == 0) {
                break
            }
        }

        return AmountInCoins(changeMap, Amount(remainingChange))
    }

    internal fun optimal(changeToGive: Amount): AmountInCoins {
        // Dynamic programming solution to the coin change problem
        val changeMap = mutableMapOf<Coin, Amount>()
        val numberOfCoins = Array(changeToGive.value + 1) { Int.MAX_VALUE }
        val lastCoin = Array(changeToGive.value + 1) { 0 }
        numberOfCoins[0] = 0
        for (i in 1..changeToGive.value) {
            for (coin in coins) {
                if (coin.denomination <= i && numberOfCoins[i - coin.denomination] + 1 < numberOfCoins[i]) {
                    numberOfCoins[i] = numberOfCoins[i - coin.denomination] + 1
                    lastCoin[i] = coin.denomination
                }
            }
        }
        var remainingChange = changeToGive.value
        while (remainingChange > 0 && lastCoin[remainingChange] != 0) {
            val coin = Coin(lastCoin[remainingChange])
            changeMap[coin] = changeMap.getOrDefault(coin, Amount(0)) + Amount(1)
            remainingChange -= coin.denomination
        }
        return AmountInCoins(changeMap, Amount(remainingChange))
    }
}

enum class CoinSystemType {
    CANONICAL,
    NON_CANONICAL,
    AUTO
}

@JvmInline
value class Coin(val denomination: Int) {
    init {
        require(denomination > 0) { "Coin face value must be positive" }
    }
}

@JvmInline
value class Amount(val value: Int) {
    init {
        require(value >= 0) { "Amount must be positive" }
    }

    operator fun plus(other: Amount): Amount = Amount(value + other.value)
}

data class AmountInCoins(val coins: Map<Coin, Amount>, val remaining: Amount) {
    val complete: Boolean = remaining.value == 0
}

