package pro.juxt.interview.secondround

class VendingMachine(val coinSystem: CoinSystem) {

    fun dispenseChange(changeToGive: Amount): Change {
        return coinSystem.makeChange(changeToGive)
    }
}
