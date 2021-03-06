package com.example.unittestdemo

import androidx.lifecycle.*
import com.example.unittestdemo.ErrorType.USER_ID_ERROR
import com.example.unittestdemo.Resource.Success
import kotlinx.coroutines.launch

class ETagPage1ViewModel(private val repository: ETagRepository) : ViewModel() {

    private val isBusy: MutableLiveData<Boolean> = MutableLiveData()

    private val commonCars: MutableLiveData<List<CommonCar>> = MutableLiveData()

    fun getCommonCars(): LiveData<List<CommonCar>> = commonCars.also {
        fetchCommonCars()
    }

    fun getIsCommonCarVisible(): LiveData<Boolean> = commonCars.map { list ->
        return@map list.isNotEmpty()
    }

//    init {
//        fetchCommonCars()
//    }

    private fun fetchCommonCars() = viewModelScope.launch {
        isBusy.value = true
        val resource = repository.getCommonCars()
        isBusy.value = false

        commonCars.value = when(resource) {
            is Success -> resource.data
            else -> emptyList()
        }
    }

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

    fun deposit() = viewModelScope.launch{
        val (car1, car2, userId) = editCarInfo.value ?: return@launch

        if (userId.length < 8) {
            errorType.value = USER_ID_ERROR
            return@launch
        }

        isBusy.value = true
        val resource = repository.executeTrade()
        isBusy.value = false
        if (resource is Success) {
            tradeTime.value = resource.data
        }
    }

    private val tradeTime: MutableLiveData<String> = MutableLiveData()

    fun getTradeTime(): LiveData<String> {
        return tradeTime
    }

    private val errorType: MutableLiveData<ErrorType> = MutableLiveData()

    fun getErrorType(): LiveData<ErrorType> {
        return errorType
    }
}
