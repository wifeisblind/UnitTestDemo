package com.example.unittestdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ETagPage1ViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var SUT: ETagPage1ViewModel

    @Before
    fun setUp() {
        SUT = ETagPage1ViewModel(DummyRepository())
    }

    @Test
    fun getIsDepositBtnEnable_fillAllInput_returnFromFalseToTrue() {
        // arrange
        val observer = mockk<Observer<Boolean>>(relaxUnitFun = true)
        SUT.getIsDepositBtnEnable().observeForever(observer)

        // act
        SUT.inputEditCar1("ABC")
        SUT.inputEditCar2("1234")
        SUT.inputEditUserId("A12345678")

        // assert
        verifyOrder {
            observer.onChanged(false)
            observer.onChanged(true)
        }
    }
}