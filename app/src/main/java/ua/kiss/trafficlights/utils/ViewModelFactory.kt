package ua.kiss.trafficlights.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ua.kiss.trafficlights.MainActivityViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel() as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}