package com.example.unittestdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.unittestdemo.Resource.Success
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ETagPage1ViewModelTest {

    @get:Rule
    val coRule = TestCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var SUT: ETagPage1ViewModel

    lateinit var repositoryTd: ETagRepositoryTd

    @Before
    fun setUp() {
        repositoryTd = ETagRepositoryTd()
        SUT = ETagPage1ViewModel(repositoryTd)
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

    @ExperimentalCoroutinesApi
    @Test
    fun getCommonCars_apiSuccess_returnCommonCars() {
        // arrange
        repositoryTd.resourceCommonCars = Success(listOf(
            CommonCar("AAA-1111", "A111111111", "001"),
            CommonCar("BBB-2222", "A222222222", "002"),
            CommonCar("CCC-3333", "A333333333", "003")
        ))
        coRule.testDispatcher.advanceTimeBy(100)

        // act
        val isVisible = SUT.getIsCommonCarVisible().getOrAwaitValue()
        val commonCars = SUT.getCommonCars().getOrAwaitValue()

        // assert
        assertTrue(isVisible)
        val (carPlate, userId, seq) = commonCars[1]
        assertThat(carPlate, `is`("BBB-2222"))
        assertThat(userId, `is`("A222222222"))
        assertThat(seq, `is`("002"))
    }
}