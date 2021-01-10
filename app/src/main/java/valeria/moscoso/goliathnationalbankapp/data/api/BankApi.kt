package valeria.moscoso.goliathnationalbankapp.data.api

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import valeria.moscoso.goliathnationalbankapp.domain.model.dto.CurrencyRateDto
import valeria.moscoso.goliathnationalbankapp.domain.model.dto.TransactionDto

interface BankApi {
    @GET("/rates")
    fun getRates(): Flow<List<CurrencyRateDto>>

    @GET ("/transactions")
    fun getTransactions(): Flow<List<TransactionDto>>
}