package pro.juxt.interview.firstround.model

class Deck {
    val cards = Suit.values()
        .flatMap { suit -> FaceValue.values().map { faceValue -> Card(suit, faceValue) } }.toMutableList()

    fun shuffle() {
        cards.shuffle()
    }

    fun firstCard(): Card = cards.removeFirst()
}
