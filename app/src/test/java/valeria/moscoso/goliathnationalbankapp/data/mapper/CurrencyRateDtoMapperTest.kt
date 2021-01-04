package valeria.moscoso.goliathnationalbankapp.data.mapper

import org.junit.Test
import valeria.moscoso.goliathnationalbankapp.domain.model.CurrencyRate
import valeria.moscoso.goliathnationalbankapp.domain.model.dto.CurrencyRateDto


class CurrencyRateDtoMapperTest {

    @Test
    fun `should map dto to domain when invoked`() {
        val currencyRateDtoList = listOf(CurrencyRateDto("EUR", "USD", "1.45"))
        val currencyRateExpected = CurrencyRate("EUR", "USD", 1.45)

        val actualCurrencyRate = CurrencyRateDtoMapper.fromListDtoToDomainList(currencyRateDtoList)[0]

        assert(currencyRateExpected.from == actualCurrencyRate.from)
        assert(currencyRateExpected.to == actualCurrencyRate.to)
        assert(currencyRateExpected.rate == actualCurrencyRate.rate)
    }
}