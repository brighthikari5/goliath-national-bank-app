package valeria.moscoso.goliathnationalbankapp.domain.mapper

import org.junit.Test
import valeria.moscoso.goliathnationalbankapp.domain.model.CurrencyRate
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction
import java.lang.RuntimeException

class CurrencyExchangeMapperTest {

    private val mapper = CurrencyExchangeMapper()

    private companion object {
        const val SKU = "T2006"

        val TRANSACTION_ORIGINAL_REGULAR = Transaction(SKU, 10.00, "USD")
        val TRANSACTION_ORIGINAL_NO_CONVERSION = Transaction(SKU, 9.09, "CAD")
        val TRANSACTION_EXPECTED = Transaction(SKU, 12.4, "EUR")
        val TRANSACTION_ERROR = Transaction(SKU, 10.00, "TFF")
        val TRANSACTION_ERROR_NO_EXCHANGE = Transaction(SKU, 10.00, "RFF")

        val SERVER_CURRENCY_LIST = listOf(
            CurrencyRate("USD", "EUR", 1.24),
            CurrencyRate("EUR", "USD", 0.81),
            CurrencyRate("USD", "CAD", 0.91),
            CurrencyRate("CAD", "USD", 1.1),
            CurrencyRate("EUR", "AUD", 1.49),
            CurrencyRate("RRF", "USS", 7.72),
            CurrencyRate("AUD", "EUR", 0.67)
        )
    }


    @Test
    fun `should map to transaction expected when invoked with one in the list to EUR`() {

        val result: Transaction =
            mapper.mapTransactionsLogic(TRANSACTION_ORIGINAL_REGULAR, SERVER_CURRENCY_LIST)

        assert(result.sku == TRANSACTION_EXPECTED.sku)
        assert(result.amount == TRANSACTION_EXPECTED.amount)
        assert(result.currency == TRANSACTION_EXPECTED.currency)
    }

    @Test
    fun `should map to transaction expected when invoked without one in the list to EUR`() {

        val result: Transaction =
            mapper.mapTransactionsLogic(TRANSACTION_ORIGINAL_NO_CONVERSION, SERVER_CURRENCY_LIST)

        assert(result.sku == TRANSACTION_EXPECTED.sku)
        assert(result.amount == TRANSACTION_EXPECTED.amount)
        assert(result.currency == TRANSACTION_EXPECTED.currency)
    }

    @Test(expected = RuntimeException::class)
    fun `should throw expected error when invoked without exchange in list`() {

        mapper.mapTransactionsLogic(TRANSACTION_ERROR_NO_EXCHANGE, SERVER_CURRENCY_LIST)
    }

    @Test(expected = RuntimeException::class)
    fun `should throw expected error when invoked without currency in list`() {

        mapper.mapTransactionsLogic(TRANSACTION_ERROR, SERVER_CURRENCY_LIST)
    }

    @Test
    fun `should return list conaining eur at the end when executed with list ok`() {
        val currencyRatesList = listOf(
            CurrencyRate("CAD", "AUD", 1.43),
            CurrencyRate("AUD", "CAD", 0.7),
            CurrencyRate("CAD", "EUR", 1.5),
            CurrencyRate("EUR", "CAD", 0.67),
            CurrencyRate("AUD", "USD", 1.05),
            CurrencyRate("USD", "AUD", 0.95)
        )
        val transaction = Transaction(SKU, 9.09, "USD")

        val result = mapper.mapTransactionsLogic(transaction, currencyRatesList)

        assert(result.currency == "EUR")
    }

    @Test(expected = RuntimeException::class)
    fun `should return error when executed with list ko`() {
        val currencyRatesList = listOf(
            CurrencyRate("CAD", "AUD", 1.43),
            CurrencyRate("AUD", "CAD", 0.7),
            CurrencyRate("CAD", "EUR", 1.5),
            CurrencyRate("EUR", "CAD", 0.67),
            CurrencyRate("AUD", "USD", 1.05),
            CurrencyRate("USD", "AUD", 0.95)
        )
        val transaction = Transaction(SKU, 9.09, "YEN")

        mapper.mapTransactionsLogic(transaction, currencyRatesList)
    }

    @Test(expected = RuntimeException::class)
    fun `should return error when executed with list but no eur conversion`() {
        val currencyRatesList = listOf(
            CurrencyRate("CAD", "AUD", 1.43),
            CurrencyRate("CAD", "EUR", 1.5),
            CurrencyRate("EUR", "CAD", 0.67),
            CurrencyRate("AUD", "USD", 1.05),
            CurrencyRate("USD", "AUD", 0.95)
        )
        val transaction = Transaction(SKU, 9.09, "AUD")

        mapper.mapTransactionsLogic(transaction, currencyRatesList)
    }

    @Test(expected = RuntimeException::class)
    fun `should return error when executed with list but no eur conversion and several currencyFrom`() {
        val currencyRatesList = listOf(
            CurrencyRate("CAD", "AUD", 1.43),
            CurrencyRate("CAD", "USD", 1.5),
            CurrencyRate("CAD", "YEN", 1.5),
            CurrencyRate("EUR", "CAD", 0.67),
            CurrencyRate("AUD", "USD", 1.05),
            CurrencyRate("USD", "AUD", 0.95)
        )

        val transaction = Transaction(SKU, 9.09, "CAD")

        mapper.mapTransactionsLogic(transaction, currencyRatesList)
    }
}