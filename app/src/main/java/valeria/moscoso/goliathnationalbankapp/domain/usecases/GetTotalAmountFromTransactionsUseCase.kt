package valeria.moscoso.goliathnationalbankapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import valeria.moscoso.goliathnationalbankapp.data.repository.BankRepository
import valeria.moscoso.goliathnationalbankapp.domain.mapper.CurrencyExchangeMapper
import valeria.moscoso.goliathnationalbankapp.domain.model.CurrencyRate
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction
import java.math.BigDecimal
import java.math.RoundingMode


class GetTotalAmountFromTransactionsUseCase(
    private val bankRepository: BankRepository,
    private val currencyExchangeMapper: CurrencyExchangeMapper,
) {
    fun execute(transactionListFiltered: List<Transaction>): Flow<Double> =
        bankRepository.getCurrencyRates()
            .flatMapConcat { currencyRatesList ->
                flow {
                    val totalAmount =
                        sumEurByListFiltered(transactionListFiltered, currencyRatesList)
                    emit(totalAmount)
                }.catch {
                    emit(-1.0)
                }
            }

    private fun sumEurByListFiltered(
        transactionListFiltered: List<Transaction>,
        currencyRatesList: List<CurrencyRate>,
    ): Double {
        val totalAmount = transactionListFiltered
            .map { transaction ->
                currencyExchangeMapper.mapTransactionsLogic(
                    transaction,
                    currencyRatesList
                )
            }
            .map { it.amount }
            .sum()
        return BigDecimal(totalAmount)
            .setScale(2, RoundingMode.HALF_EVEN)
            .toDouble()
    }
}