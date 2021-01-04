package valeria.moscoso.goliathnationalbankapp.data.api

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import valeria.moscoso.goliathnationalbankapp.domain.model.dto.CurrencyRateDto

interface BankApi {
    @GET("/rates.json")
    fun getRates(): Flow<List<CurrencyRateDto>>
}