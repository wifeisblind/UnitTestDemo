package com.example.unittestdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class ETagPage1ViewModel(repository: ETagRepository) : ViewModel() {

    private val editCarInfo: MutableLiveData<EditCarInfo> = MutableLiveData(EditCarInfo.EMPTY)

    fun inputEditCar1(car1: String) {
        editCarInfo.value = editCarInfo.value?.copy(editCar1 = car1)
    }

    fun inputEditCar2(car2: String) {
        editCarInfo.value = editCarInfo.value?.copy(editCar2 = car2)
    }

    fun inputEditUserId(userId: String) {
        editCarInfo.value = editCarInfo.value?.copy(editUserId = userId)
    }

    fun getIsDepositBtnEnable(): LiveData<Boolean> = editCarInfo.map { editCarInfo ->
        val (editCar1, editCar2, editUserId) = editCarInfo
        return@map editCar1.isNotEmpty() &&
                editCar2.isNotEmpty() &&
                editUserId.isNotEmpty()
    }
}
