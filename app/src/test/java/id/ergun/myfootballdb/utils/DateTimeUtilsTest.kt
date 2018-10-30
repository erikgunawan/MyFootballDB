package id.ergun.myfootballdb.utils

import org.junit.Assert
import org.junit.Test

class DateTimeUtilsTest {

    @Test
    fun toLocalDateTest() {
        val dateString = "2018-09-30"
        Assert.assertEquals("Min, 30 Sep 2018", toLocalDate(dateString))
    }


    @Test
    fun toLocalDateErrorTest() {
        val dateString = "30/09/2018"
        Assert.assertEquals("30/09/2018", toLocalDate(dateString))
    }

    @Test
    fun toLocalTimeTest() {
        val timeString = "14:00:00+0000"
        Assert.assertEquals("21:00", toLocalTime(timeString))
    }

    @Test
    fun toLocalTimeErrorTest() {
        val timeString = "14:00:00"
        Assert.assertEquals("14:00:00", toLocalTime(timeString))
    }

}