package com.example.unittestdemo

import android.os.Handler
import com.example.unittestdemo.Resource.Success
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class ETagRepositoryImp : ETagRepository {
    override suspend fun getCommonCars(): Resource<List<CommonCar>> = suspendCancellableCoroutine { cont ->
        Handler().postDelayed({
            cont.resume(Success(listOf(CommonCar("ABC-1234", "A12345678", "001"))))
//            cont.resume(Success(emptyList()))
        }, 1000)
    }
}
