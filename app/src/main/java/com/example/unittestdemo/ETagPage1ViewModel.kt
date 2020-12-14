package com.example.unittestdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unittestdemo.Resource.Success
import kotlinx.coroutines.launch

class ETagPage1ViewModel(private val repo: ETagRepository) : ViewModel() {

    private val navigationEvent: MutableLiveData<Unit> = MutableLiveData()

    private val info: EditCarInfo = EditCarInfo.EMPTY

    fun inputCarPlate(car1: String, car2: String) {
        info.editCar1 = car1
        info.editCar2 = car2
        checkInfo()
    }

    fun inputUserId(userId: String) {
        info.editUserId = userId
        checkInfo()
    }

    fun inputCheckBox(isChecked: Boolean) {
        info.isChecked = isChecked
        checkInfo()
    }

    private fun checkInfo() {
        isButtonEnable.value = (info.editCar1.isNotEmpty()
                && info.editCar2.isNotEmpty()
                && info.editUserId.isNotEmpty()
                && info.isChecked)
    }

    private val isButtonEnable: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getIsButtonEnable(): LiveData<Boolean> = isButtonEnable

    fun clickDeposit() = viewModelScope.launch {
        when(repo.deposit(info)) {
            is Success -> navigationEvent.value = Unit
            else -> {
                // error handling
            }
        }
    }

    fun getNavigationEvent(): LiveData<Unit> = navigationEvent
}
