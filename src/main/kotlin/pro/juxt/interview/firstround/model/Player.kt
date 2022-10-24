package pro.juxt.interview.firstround.model

class Player {

    private val hand = mutableListOf<Card>()

    fun draw(card: Card) {
        hand += card
    }

    fun handValue(): Int {
        val (aces, otherCards) = hand.partition { it.faceValue == FaceValue.ACE }
        val handValue = otherCards.sumOf { it.faceValue.value }

        return if (handValue <= 10 && aces.isNotEmpty()) handValue + 10 + aces.size else handValue + aces.size
    }
}
