package com.example.kotlin.myapplication.utils.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseAndroidViewModel() : AndroidViewModel(Application()) {

    var parentJob = Job()
    val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO
    val scope = CoroutineScope(coroutineContext)


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()

    }

    fun getCoroutineScope(): CoroutineScope {
        return scope
    }
}