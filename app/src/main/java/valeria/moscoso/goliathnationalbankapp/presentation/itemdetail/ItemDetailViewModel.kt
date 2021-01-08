package valeria.moscoso.goliathnationalbankapp.presentation.itemdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction
import valeria.moscoso.goliathnationalbankapp.domain.model.TransactionGroupBySku
import valeria.moscoso.goliathnationalbankapp.domain.usecases.GetTotalAmountFromTransactionsUseCase
import valeria.moscoso.goliathnationalbankapp.domain.usecases.GetTransactionsGroupUseCase

class ItemDetailViewModel(
    private val getTransactionsGroupUseCase: GetTransactionsGroupUseCase,
    private val getTotalAmountFromTransactionsUseCase: GetTotalAmountFromTransactionsUseCase,
) : ViewModel() {

    var transactionGroupBySku = MutableLiveData<TransactionGroupBySku>()

    var onError = MutableLiveData<String>()

    fun getTransactions(sku: String) {
        viewModelScope.launch {
            getTransactionsGroupUseCase.execute(sku)
                .flatMapConcat { transactionList ->
                    getTotalAmountFromTransactionsUseCase.execute(transactionList)
                        .map {
                            TransactionGroupBySku(transactionList, it)
                        }
                }
                .flowOn(Dispatchers.IO)
                .catch {
                    onError.postValue(it.message)
                }
                .collect {
                    transactionGroupBySku.postValue(it)
                }
        }
    }
}