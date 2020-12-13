package com.example.unittestdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.mockk
import io.mockk.verifyOrder
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class ETagPage1ViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var SUT: ETagPage1ViewModel

    @Before
    fun setUp() {
        SUT = ETagPage1ViewModel()
    }

//    Scenario: 使用者輸入所有資訊
//    When: 使用者輸入車牌號碼、用戶證號、勾選注意事項時
//    Then: 儲值按鈕由Disable變成Enable

    @Test
    fun getIsButtonEnable_inputAllInfo_isButtonEnableFromFalseToTrue() {
        // arrange
        val isButtonEnable = mockk<Observer<Boolean>>()
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
}