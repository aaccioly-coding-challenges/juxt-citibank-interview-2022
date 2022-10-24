package pro.juxt.interview.secondround

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VendingMachineTest {

    @Test
    fun `can be initialized with coins`() {
        val coinTypes = setOf<Int>(1, 2, 5, 10, 20, 50, 100, 200)
        val expectedDenominations = listOf(200, 100, 50, 20, 10, 5, 2, 1)
        val vendingMachine = VendingMachine(coinTypes)

        assertThat(vendingMachine.availableDenominations).isEqualTo(expectedDenominations)
    }

    @Test
    fun `can compute optimal change`() {
        val coinTypes = setOf<Int>(1, 2, 5, 10, 20, 50, 100, 200)
        val changeToGive = 500
        val vendingMachine = VendingMachine(coinTypes)

        val change = vendingMachine.dispenseChange(changeToGive)

        val expectChange = mapOf(200 to 2, 100 to 1)

        assertThat(change).isEqualTo(expectChange)
    }

}
