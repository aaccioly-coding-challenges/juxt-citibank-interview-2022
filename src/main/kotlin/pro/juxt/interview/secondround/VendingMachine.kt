package pro.juxt.interview.secondround

typealias Change = AmountInCoins

class VendingMachine(val coinSystem: CoinSystem) {

    fun dispenseChange(changeToGive: Amount): Change {
        return coinSystem.makeAmountInCoins(changeToGive)
    }
}
