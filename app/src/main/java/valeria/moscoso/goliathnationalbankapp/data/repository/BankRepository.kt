package valeria.moscoso.goliathnationalbankapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import valeria.moscoso.goliathnationalbankapp.data.datasource.BankCloudDataSource
import valeria.moscoso.goliathnationalbankapp.data.datasource.BankLocalDataSource
import valeria.moscoso.goliathnationalbankapp.domain.model.CurrencyRate
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction


class BankRepository(
    private val cloudDataSource: BankCloudDataSource,
    private val localDataSource: BankLocalDataSource
) {

    fun downloadRates(): Flow<Unit> =
        cloudDataSource.downloadRates()
            .flatMapConcat { currencyCloudList ->
                localDataSource.storeRates(currencyCloudList)
            }

    fun downloadTransactions(): Flow<Unit> =
        cloudDataSource.downloadTransactions()
            .flatMapConcat { transactionCloudList ->
                localDataSource.storeTransaction(transactionCloudList)
            }

    fun getTransactions(): Flow<List<Transaction>> = localDataSource.getTransactions()

    fun getCurrencyRates(): Flow<List<CurrencyRate>>  = localDataSource.getRates()
}