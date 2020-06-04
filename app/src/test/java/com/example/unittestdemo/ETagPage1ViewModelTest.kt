package com.example.unittestdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.unittestdemo.Resource.NetworkError
import com.example.unittestdemo.Resource.Success
import io.mockk.*
import io.mockk.impl.annotations.MockK
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

    @MockK
    lateinit var mockRepository: ETagRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repositoryTd = ETagRepositoryTd()
        SUT = ETagPage1ViewModel(mockRepository)
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

    @ExperimentalCoroutinesApi
    @Test
    fun getCommonCars_apiSuccess_returnEmptyList() {
        // arrange
        coEvery {
            mockRepository.getCommonCars()
        } returns NetworkError("404 Not found")

        // act
        val commonCars = SUT.getCommonCars().getOrAwaitValue()
        val isVisible = SUT.getIsCommonCarVisible().getOrAwaitValue()

        // assert
        assertFalse(isVisible)
        assertTrue(commonCars.isEmpty())
    }
}