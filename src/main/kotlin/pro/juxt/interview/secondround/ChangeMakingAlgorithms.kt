package pro.juxt.interview.secondround

typealias ChangeMaker = (coins: List<Coin>, changeToGive: Amount) -> Change

val greedy: ChangeMaker = { coins, changeToGive ->
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

    Change(changeMap, Amount(remainingChange))
}

val dynamicProgramming: ChangeMaker = { coins, changeToGive ->
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

    Change(changeMap, Amount(remainingChange))
}

val optimal = dynamicProgramming

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

data class Change(val coins: Map<Coin, Amount>, val remaining: Amount) {
    val complete: Boolean = remaining.value == 0
}
