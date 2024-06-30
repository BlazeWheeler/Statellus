package com.blazewheeler.statellus

import com.blazewheeler.statellus.utils.StringUtils
import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.math.BigDecimal

class StringUtilsUnitTest {

    @Test
    fun  zeroDecimalToInt() {
        assertEquals("1,234", StringUtils.zeroDecimalToInt(1234f))
        assertEquals("5,000,000", StringUtils.zeroDecimalToInt(5000000f))
    }


    @Test
    fun `test bigZeroDecimalToInt with integer value`() {
        val value = BigDecimal("1234")
        val expected = "1,234"
        val result = StringUtils.bigZeroDecimalToInt(value)
        assertEquals(expected, result)
    }

    @Test
    fun `test bigZeroDecimalToInt with decimal value`() {
        val value = BigDecimal("1234.567")
        val expected = "1,234.567"
        val result = StringUtils.bigZeroDecimalToInt(value)
        assertEquals(expected, result)
    }

    @Test
    fun `test bigZeroDecimalToInt with negative value`() {
        val value = BigDecimal("-1234.567")
        val expected = "-1,234.567"
        val result = StringUtils.bigZeroDecimalToInt(value)
        assertEquals(expected, result)
    }

    @Test
    fun `test bigZeroDecimalToInt with zero value`() {
        val value = BigDecimal.ZERO
        val expected = "0"
        val result = StringUtils.bigZeroDecimalToInt(value)
        assertEquals(expected, result)
    }

    @Test
    fun `test removeTrailingComma`() {
        val inputWithComma = "1, 2, 3,"
        val expectedWithoutComma = "1, 2, 3"
        val actualWithoutComma = StringUtils.removeTrailingComma(inputWithComma)
        assertEquals(expectedWithoutComma, actualWithoutComma)
    }

    @Test
    fun `test formatWithCommas`() {
        val decimalWithoutComma = 5000.123456
        val expectedWithComma = "5,000.123"
        val actualWithComma = StringUtils.formatWithCommas(decimalWithoutComma)
        assertEquals(expectedWithComma, actualWithComma)
    }
}