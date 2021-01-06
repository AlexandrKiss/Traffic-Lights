package ua.kiss.trafficlights

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.kiss.trafficlights.utils.Status

class MainActivityViewModel : ViewModel() {
    val updateStatus: MutableLiveData<Status> by lazy { MutableLiveData<Status>() }
    private lateinit var corutineScope: Job

    fun onStart() {
        corutineScope = viewModelScope.launch {
            while (true) {
                updateStatus.postValue(Status.STOP)
                delay(5000)
                updateStatus.postValue(Status.ATTENTION)
                delay(3000)
                updateStatus.postValue(Status.START)
                delay(5000)
            }
        }
    }
    fun onStop() {
        corutineScope.cancel()
    }
}