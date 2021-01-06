package ua.kiss.trafficlights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import ua.kiss.trafficlights.utils.Status

class MainActivityViewModel : ViewModel() {
    val updateStatus: MutableLiveData<Status> by lazy { MutableLiveData<Status>() }
    private lateinit var coroutineScope: Job
    var isVisibleDialog: Boolean = false

    fun onStart() {
        if (!::coroutineScope.isInitialized)  changeOfStatus()
        else if (!coroutineScope.isActive && !isVisibleDialog) changeOfStatus()
    }

    fun onStop(drop: Boolean = false) {
        coroutineScope.cancel()
        if (drop) updateStatus.postValue(null)
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }

    private fun changeOfStatus() {
        coroutineScope = viewModelScope.async {
            while (true) {
                if (updateStatus.value == Status.START || updateStatus.value == null) {
                    updateStatus.postValue(Status.STOP)
                    println(Status.STOP)
                    delay(3000)
                }
                if (updateStatus.value == Status.STOP) {
                    updateStatus.postValue(Status.ATTENTION)
                    println(Status.ATTENTION)
                    delay(2000)
                }
                if (updateStatus.value == Status.ATTENTION) {
                    updateStatus.postValue(Status.START)
                    println(Status.START)
                    delay(3000)
                }
            }
        }
    }
}