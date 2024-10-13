package com.apptick.currenttime.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.apptick.currenttime.data.MainRepository
import com.apptick.currenttime.data.TimeModel
import com.codeclan.teammember.data.core.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    val timeResponse = MutableLiveData<Resource<TimeModel>>()
    fun getCurrentTime() {
        viewModelScope.launch {
            repository.getCurrentTime().collectLatest {
                timeResponse.value = it
            }
        }
    }
}