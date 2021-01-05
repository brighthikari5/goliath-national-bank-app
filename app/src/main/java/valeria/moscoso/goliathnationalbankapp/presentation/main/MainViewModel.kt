package valeria.moscoso.goliathnationalbankapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import valeria.moscoso.goliathnationalbankapp.domain.usecases.GetInitialDataUseCase


class MainViewModel(private val getInitialDataUseCase: GetInitialDataUseCase) : ViewModel() {


     fun downloadData() {
        viewModelScope.launch {
            getInitialDataUseCase.execute()
                .map {

                }
                .flowOn(Dispatchers.IO)
                .catch {  }
                .collect {

                }
        }
    }


}