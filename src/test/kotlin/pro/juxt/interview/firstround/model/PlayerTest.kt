package pro.juxt.interview.firstround.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `can draw the first card of a deck`() {
        val player = Player()
        val deck = Deck()

        player.draw(deck.firstCard())

        Assertions.assertThat(player.handValue()).isEqualTo(2)
    }

    @Test
    fun `hand value with only an ace is worth 11`() {
        val player = Player()

        player.draw(Card(Suit.SPADES, FaceValue.ACE))

        Assertions.assertThat(player.handValue()).isEqualTo(11)
    }

    @Test
    fun `hand value with only two aces is worth 12`() {
        val player = Player()

        player.draw(Card(Suit.SPADES, FaceValue.ACE))
        player.draw(Card(Suit.DIAMONDS, FaceValue.ACE))

        Assertions.assertThat(player.handValue()).isEqualTo(12)
    }
}
