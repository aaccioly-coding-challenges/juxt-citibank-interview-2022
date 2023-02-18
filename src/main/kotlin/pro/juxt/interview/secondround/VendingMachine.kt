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
        require(value > 0) { "Amount must be positive" }
    }
}

class VendingMachine(coinTypes: Set<Coin>) {

    val availableCoins = coinTypes.sortedByDescending { it.denomination }

    fun dispenseChange(changeToGive: Amount): Map<Coin, Amount> {
        var remainingChange = changeToGive.value
        val change = mutableMapOf<Coin, Amount>()
        for (coin in availableCoins) {
            val numberOfCoinsForDenomination = remainingChange / coin.denomination
            if (numberOfCoinsForDenomination > 0) {
                change[coin] = Amount(numberOfCoinsForDenomination)
                remainingChange -= coin.denomination * numberOfCoinsForDenomination
            }
        }

        return change
    }

}
