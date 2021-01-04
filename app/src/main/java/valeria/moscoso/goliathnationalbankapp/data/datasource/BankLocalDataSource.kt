package valeria.moscoso.goliathnationalbankapp.data.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import valeria.moscoso.goliathnationalbankapp.domain.model.CurrencyRate
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction

class BankLocalDataSource {

    private val memoryRateList: MutableList<CurrencyRate> = mutableListOf()
    private val memoryTransactionList: MutableList<Transaction> = mutableListOf()

    fun storeRates(ratesList: List<CurrencyRate>): Flow<Unit> = flow {
        memoryRateList.clear()
        memoryRateList.addAll(ratesList)
        emit(Unit)
    }

    fun getRates(): Flow<List<CurrencyRate>> = flow {
        emit(memoryRateList)
    }

    fun storeTransaction(transactionList: List<Transaction>): Flow<Unit> = flow {
        memoryTransactionList.clear()
        memoryTransactionList.addAll(transactionList)
        emit(Unit)
    }

    fun getTransactions(): Flow<List<Transaction>> = flow {
        emit(memoryTransactionList)
    }

}