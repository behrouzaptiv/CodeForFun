package com.aptiv.fika.presentation.viewmodel

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aptiv.fika.domain.entity.PersonAndEvent
import com.aptiv.fika.domain.extension.Result
import com.aptiv.fika.domain.usecase.AssignFika
import com.aptiv.fika.domain.usecase.GetAllPerson
import com.aptiv.fika.domain.usecase.GetPersonAndEvent
import com.aptiv.fika.domain.usecase.GetPersonById
import com.aptiv.fika.domain.usecase.RemovePerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val getAllPerson: GetAllPerson,
    private val getPersonById: GetPersonById,
    private val removePerson: RemovePerson,
    private val assignFika: AssignFika,
    private val getPersonAndEvent: GetPersonAndEvent
): ViewModel() {

    private val TAG = "PersonViewModel"


    init {
        getPersonList()
        getPersonEventList()
        Log.d(TAG, "init() called")
    }

    private val _state: MutableStateFlow<List<PersonAndEvent>> = MutableStateFlow(emptyList())
    val state: StateFlow<List<PersonAndEvent>> = _state

    private val _loadingState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> = _loadingState

    private val _errorState = MutableStateFlow("")
    val errorState: StateFlow<String> = _errorState

    @VisibleForTesting
    internal fun getPersonList() = viewModelScope.launch {
        getAllPerson.invoke().collect {
            Log.d(TAG, "getPersonList()  collect called $it")
            when(it) {
                is Result.Loading -> {
                    _loadingState.value = true
                }
                is Result.Success -> {
                    _loadingState.value = false
                    //  _state.value = it.data
                }
                is Result.Error -> {
                    _loadingState.value = false
                    _errorState.value = it.error
                }
            }
        }
    }

    @VisibleForTesting
    internal fun getPersonEventList() = viewModelScope.launch {
        getPersonAndEvent.invoke().collect {
            Log.d(TAG, "getPersonEventList() collect called $it")
            when(it) {
                is Result.Loading -> {
                    _loadingState.value = true
                }
                is Result.Success -> {
                    _loadingState.value = false
                    _state.value = it.data
                }
                is Result.Error -> {
                    _loadingState.value = false
                    _errorState.value = it.error
                }
            }
        }
    }

}