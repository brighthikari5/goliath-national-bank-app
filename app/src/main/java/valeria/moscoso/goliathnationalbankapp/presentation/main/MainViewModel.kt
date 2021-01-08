package valeria.moscoso.goliathnationalbankapp.presentation.main

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import valeria.moscoso.goliathnationalbankapp.domain.usecases.GetInitialDataUseCase
import valeria.moscoso.goliathnationalbankapp.domain.usecases.GetTransactionsDistinctUseCase


class MainViewModel(
    private val getInitialDataUseCase: GetInitialDataUseCase,
    private val getTransactionsDistinctUseCase: GetTransactionsDistinctUseCase
) : ViewModel() {

    var transactionsSkuList = MutableLiveData<List<String>>()
        .also {
            downloadData()
        }
    var onError = MutableLiveData<String>()

    private fun downloadData() {
        viewModelScope.launch {
            getInitialDataUseCase.execute()
                .flatMapConcat {
                    getTransactionsDistinctUseCase.execute()
                }
                .flowOn(Dispatchers.IO)
                .catch {
                    onError.postValue(it.message)
                }
                .collect {
                    transactionsSkuList.postValue(it)
                }
        }
    }

}