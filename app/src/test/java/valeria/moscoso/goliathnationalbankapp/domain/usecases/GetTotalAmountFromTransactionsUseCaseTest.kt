package valeria.moscoso.goliathnationalbankapp.domain.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import valeria.moscoso.goliathnationalbankapp.data.repository.BankRepository
import valeria.moscoso.goliathnationalbankapp.domain.mapper.CurrencyExchangeMapper
import valeria.moscoso.goliathnationalbankapp.domain.model.CurrencyRate
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction

class GetTotalAmountFromTransactionsUseCaseTest {
    private val repository: BankRepository = mock()
    private val currencyExchangeMapper = CurrencyExchangeMapper()
    private val useCase = GetTotalAmountFromTransactionsUseCase(repository, currencyExchangeMapper)

    @Test
    fun `should sum the amount expected when executed`() {
        runBlocking {
            val currencyList = listOf(
                CurrencyRate("CAD", "EUR", 1.24),
                CurrencyRate("EUR", "USD", 0.81))
            val filteredTransactionList = listOf<Transaction>(
                Transaction("T2005", 3.25, "CAD"),
                Transaction("T2005", 1.04, "CAD"),
            )
            whenever(repository.getCurrencyRates()).thenReturn(flowOf(currencyList))

            val totalAmount = useCase.execute(filteredTransactionList).single()

            assert(totalAmount == 5.32)

        }
    }

    @Test
    fun `should return expected error value when executed`() {
        runBlocking {
            val currencyList = listOf(
                CurrencyRate("YEN", "EUR", 1.24),
                CurrencyRate("EUR", "USD", 0.81))
            val filteredTransactionList = listOf<Transaction>(
                Transaction("T2005", 3.25, "CAD"),
                Transaction("T2005", 1.04, "CAD"),
            )
            whenever(repository.getCurrencyRates()).thenReturn(flowOf(currencyList))

            val totalAmount = useCase.execute(filteredTransactionList).single()

            assert(totalAmount == -1.0)

        }
    }
}