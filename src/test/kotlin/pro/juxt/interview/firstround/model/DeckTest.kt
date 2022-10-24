package pro.juxt.interview.firstround.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    fun `has 52 cards`() {
        val deck = Deck()

        assertThat(deck.cards).hasSize(52)
    }

    @Test
    fun `can have a card drawn from it`() {
        val deck = Deck()
        val card: Card = deck.firstCard()
        assertThat(card).isEqualTo(Card(Suit.DIAMONDS, FaceValue.TWO))
    }
}
