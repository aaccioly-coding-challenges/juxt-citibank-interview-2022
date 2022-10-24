package pro.juxt.interview.secondround

class VendingMachine(coinTypes: Set<Int>) {

    val availableDenominations = coinTypes.sortedDescending()

    fun dispenseChange(changeToGive: Int): Map<Int, Int> {
        var remainingChange = changeToGive
        val change = mutableMapOf<Int, Int>()
        for (faceValue in availableDenominations) {
            val numberOfCoinsForDenomination = remainingChange / faceValue
            if (numberOfCoinsForDenomination > 0) {
                change[faceValue] = numberOfCoinsForDenomination
                remainingChange -= faceValue * numberOfCoinsForDenomination
            }
        }

        return change
    }

}
