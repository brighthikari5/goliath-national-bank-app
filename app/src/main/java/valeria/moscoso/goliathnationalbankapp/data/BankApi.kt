package valeria.moscoso.goliathnationalbankapp.data

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import valeria.moscoso.goliathnationalbankapp.domain.CurrencyRate

interface BankApi {
    @GET("/rates.json")
    fun getRates(): Flow<List<CurrencyRate>>
}