package pro.juxt.interview.firstround.model

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    fun `has 52 cards`() {
        val deck = Deck()

        deck.cards shouldHaveSize 52
    }

    @Test
    fun `can have a card drawn from it`() {
        val deck = Deck()
        val card: Card = deck.firstCard()

        card shouldBe Card(Suit.DIAMONDS, FaceValue.TWO)
    }
}
