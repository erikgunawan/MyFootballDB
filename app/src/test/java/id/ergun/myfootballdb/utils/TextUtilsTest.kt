package id.ergun.myfootballdb.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class TextUtilsTest {

    @Test
    fun formatStringTest() {
        val text = "Anthony Knockaert; Dale Stephens; Davy Proepper; Solly March; Pascal Gross; "
        val oldValue = ";"
        val newValue = ";\n"

        val expected = "Anthony Knockaert;\n Dale Stephens;\n Davy Proepper;\n Solly March;" +
                "\n Pascal Gross;\n "
        assertEquals(expected, formatString(text, oldValue, newValue))
    }
}