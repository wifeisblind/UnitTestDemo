package com.example.unittestdemo

import org.hamcrest.core.Is.`is`
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class CheckInputNumberTest {

    private lateinit var SUT: CheckInputNumber

    @Before
    fun setUp() {
        SUT = CheckInputNumber()
    }

    @Test
    fun testIsNumberValid_whenInputIs50_thenReturnTrue() {

        val value = SUT.isNumberValid(50)

        assertThat(value, `is`(true))
    }
}