package id.ergun.myfootballdb.utils

import org.junit.Assert
import org.junit.Test

class DateUtilsTest {

    @Test
    fun toLocalDateTest() {
        val dateString = "2018-09-30"
        Assert.assertEquals("Min, 30 Sep 2018", toLocalDate(dateString))
    }
}