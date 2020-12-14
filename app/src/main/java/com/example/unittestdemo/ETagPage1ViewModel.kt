package com.example.unittestdemo

import androidx.lifecycle.*
import com.example.unittestdemo.Resource.Success
import kotlinx.coroutines.launch

class ETagPage1ViewModel(private val repo: ETagRepository) : ViewModel() {

    private val navigationEvent: MutableLiveData<Unit> = MutableLiveData()

    private val info: MutableLiveData<EditCarInfo> = MutableLiveData(EditCarInfo.EMPTY)

    fun inputCarPlate(car1: String, car2: String) {
        info.value = info.value?.copy(editCar1 = car1, editCar2 = car2)
    }

    fun inputUserId(userId: String) {
        info.value = info.value?.copy(editUserId = userId)
    }

    fun inputCheckBox(isChecked: Boolean) {
        info.value = info.value?.copy(isChecked = isChecked)
    }

    fun getIsButtonEnable(): LiveData<Boolean> = Transformations.map(info) {
        (it.editCar1.isNotEmpty()
                && it.editCar2.isNotEmpty()
                && it.editUserId.isNotEmpty()
                && it.isChecked)
    }

    fun clickDeposit() = viewModelScope.launch {
        when(repo.deposit(info.value!!)) {
            is Success -> navigationEvent.value = Unit
            else -> {
                // error handling
            }
        }
    }

    fun getNavigationEvent(): LiveData<Unit> = navigationEvent
}
