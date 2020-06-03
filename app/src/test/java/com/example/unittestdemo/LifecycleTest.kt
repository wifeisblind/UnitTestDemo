package com.example.demounittest

import org.junit.*

class LifecycleTest {

    companion object {

        @BeforeClass
        @JvmStatic
        fun beforeClass() {
            println("beforeClass")
        }

        @AfterClass
        @JvmStatic
        fun afterClass() {
            println("afterClass")
        }
    }

    @Before
    fun setUp() {
        println("Before")
    }

    @After
    fun tearDown() {
        println("After")
    }

    @Test
    fun test1() {
        println("Test 1")
    }

    @Test
    fun test2() {
        println("Test 2")
    }
}