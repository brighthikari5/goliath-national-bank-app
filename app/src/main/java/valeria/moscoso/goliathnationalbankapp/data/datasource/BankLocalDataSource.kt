package valeria.moscoso.goliathnationalbankapp.data.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import valeria.moscoso.goliathnationalbankapp.domain.model.CurrencyRate

class BankLocalDataSource {

    private val memoryRateList: MutableList<CurrencyRate> = mutableListOf()

    fun storeRates(ratesList: List<CurrencyRate>): Flow<Unit> = flow {
        memoryRateList.clear()
        memoryRateList.addAll(ratesList)
        emit(Unit)
    }

    fun getRates(): Flow<MutableList<CurrencyRate>> = flow {
        emit(memoryRateList)
    }
}