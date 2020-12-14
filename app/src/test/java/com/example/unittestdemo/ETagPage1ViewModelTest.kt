package com.example.unittestdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.unittestdemo.Resource.Success
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class ETagPage1ViewModelTest {

    @get:Rule
    val coRule = TestCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    lateinit var repo: ETagRepository

    lateinit var SUT: ETagPage1ViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = ETagPage1ViewModel(repo)
    }

//    Scenario: 使用者輸入所有資訊
//    When: 使用者輸入車牌號碼、用戶證號、勾選注意事項時
//    Then: 儲值按鈕由Disable變成Enable

    @Test
    fun getIsButtonEnable_inputAllInfo_isButtonEnableFromFalseToTrue() {
        // arrange
        val isButtonEnable = mockk<Observer<Boolean>>(relaxUnitFun = true)
        SUT.getIsButtonEnable().observeForever(isButtonEnable)

        // act
        SUT.inputCarPlate("A", "1")
        SUT.inputUserId("A123")
        SUT.inputCheckBox(true)

        // assert
        verifyOrder {
            isButtonEnable.onChanged(false)
            isButtonEnable.onChanged(true)
        }
    }

//    Scenario: 使用者按下儲值
//    Given: API postDeposit 成功
//    When: 使用者按下儲值
//    Then: 進入下一頁


    @Test
    fun getNavigationEvent_whenApiSuccessAndPressTheButton_navigateToNextPage() {
        // arrange
        val navigationEvent = mockk<Observer<Unit>>(relaxUnitFun = true)
        SUT.getNavigationEvent().observeForever(navigationEvent)
        coEvery {
            repo.deposit(any())
        } returns Success(Unit)
        SUT.inputCarPlate("AAA", "1234")
        SUT.inputUserId("A12345678")
        SUT.inputCheckBox(true)

        // act
        SUT.clickDeposit()

        // assert
        verify {
            navigationEvent.onChanged(Unit)
        }
    }
}