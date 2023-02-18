package pro.juxt.interview.secondround

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
}

data class Change(val coins: Map<Coin, Amount>, val remainingChange: Amount) {
    val completeChange: Boolean = remainingChange.value == 0
}

class VendingMachine(coinTypes: Set<Coin>) {

    val availableCoins = coinTypes.sortedByDescending { it.denomination }

    fun dispenseChange(changeToGive: Amount): Change {
        var remainingChange = changeToGive.value
        val changeMap = mutableMapOf<Coin, Amount>()
        for (coin in availableCoins) {
            val numberOfCoinsForDenomination = remainingChange / coin.denomination
            if (numberOfCoinsForDenomination > 0) {
                changeMap[coin] = Amount(numberOfCoinsForDenomination)
                remainingChange -= coin.denomination * numberOfCoinsForDenomination
            }
            if (remainingChange == 0) {
                break
            }
        }

        return Change(changeMap, Amount(remainingChange))
    }

}
