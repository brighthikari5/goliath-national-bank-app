package valeria.moscoso.goliathnationalbankapp.data.datasource

import kotlinx.coroutines.flow.map
import valeria.moscoso.goliathnationalbankapp.data.api.BankApi
import valeria.moscoso.goliathnationalbankapp.data.mapper.CurrencyRateDtoMapper
import valeria.moscoso.goliathnationalbankapp.data.mapper.TransactionDtoMapper


class BankCloudDataSource(private val bankApi: BankApi) {

    fun downloadRates() = bankApi.getRates().map {
        CurrencyRateDtoMapper.fromListDtoToDomainList(it)
    }

    fun downloadTransactions() = bankApi.getTransactions().map{
        TransactionDtoMapper.fromListDtoToDomainList(it)
    }
}