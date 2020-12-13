package com.example.unittestdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ETagPage1ViewModel : ViewModel() {

    fun inputCarPlate(car1: String, car2: String) {

    }

    fun inputUserId(userId: String) {

    }

    fun inputCheckBox(isChecked: Boolean) {

    }

    fun getIsButtonEnable(): LiveData<Boolean> {
        return MutableLiveData()
    }
}
