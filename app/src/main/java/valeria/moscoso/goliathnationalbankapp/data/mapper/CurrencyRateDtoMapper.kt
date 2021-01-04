package valeria.moscoso.goliathnationalbankapp.data.mapper

import valeria.moscoso.goliathnationalbankapp.domain.model.CurrencyRate
import valeria.moscoso.goliathnationalbankapp.domain.model.dto.CurrencyRateDto

object CurrencyRateDtoMapper {

    fun fromListDtoToDomainList(listDto: List<CurrencyRateDto>): List<CurrencyRate> {
        return listDto.map { fromDtoToDomain(it) }
    }

    private fun fromDtoToDomain(currencyRateDto: CurrencyRateDto): CurrencyRate {
        return CurrencyRate(
            currencyRateDto.from,
            currencyRateDto.to,
            currencyRateDto.rate.toDouble()
        )
    }
}