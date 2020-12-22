import intividual.task1.ticTacToe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun ticTacToe() {
        assertEquals(1 to 2, ticTacToe("input/individual task tests/test1.txt", "o"))
        assertEquals(7 to 5, ticTacToe("input/individual task tests/test2.txt", "o"))
        assertEquals(1 to 1, ticTacToe("input/individual task tests/test3.txt", "o"))
        assertEquals(7 to 8, ticTacToe("input/individual task tests/test4.txt", "o"))
        assertEquals(8 to 3, ticTacToe("input/individual task tests/test5.txt", "o"))
        assertEquals(4 to 4, ticTacToe("input/individual task tests/test6.txt", "o"))
        assertEquals(12 to 8, ticTacToe("input/individual task tests/test7.txt", "x"))
        assertEquals(null, ticTacToe("input/individual task tests/test8.txt", "o"))
    }
}