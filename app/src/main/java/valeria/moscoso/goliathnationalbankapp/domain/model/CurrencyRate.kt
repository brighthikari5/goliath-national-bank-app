package valeria.moscoso.goliathnationalbankapp.domain.model

import java.math.BigDecimal
import java.math.RoundingMode


data class CurrencyRate(
    val currencyFrom: String,
    val currencyTo: String,
    val rate: Double,
) {
    fun calculateExchangeAmount(amount: Double): Double {
        return BigDecimal(amount * rate).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    }

    companion object {
        const val EUR = "EUR"
    }
}
