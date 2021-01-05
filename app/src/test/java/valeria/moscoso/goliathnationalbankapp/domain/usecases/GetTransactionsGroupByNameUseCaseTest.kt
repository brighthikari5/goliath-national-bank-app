package valeria.moscoso.goliathnationalbankapp.domain.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking

import org.junit.Test
import valeria.moscoso.goliathnationalbankapp.data.repository.BankRepository
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction

class GetTransactionsGroupByNameUseCaseTest {

    private val repository: BankRepository = mock()
    private val useCase = GetTransactionsGroupByNameUseCase(repository)

    @Test
    fun `should trigger expected repository calls when executed`() {
        runBlocking {
            val transactionsList = listOf<Transaction>(
                Transaction(SKU_01, 1.25, "EUR"),
                Transaction(SKU_02, 1.25, "EUR"),
                Transaction(SKU_01, 1.25, "EUR"),
                Transaction(SKU_03, 1.25, "EUR"),
                Transaction(SKU_02, 1.25, "EUR"),
                Transaction(SKU_03, 1.25, "EUR")
            )
            whenever(repository.getTransactions()).thenReturn(flowOf(transactionsList))

            val transactionListGrouped = useCase.execute().single()

            verify(repository).getTransactions()
            assert(transactionListGrouped.filter { it == SKU_01 }.count() == 1)
            assert(transactionListGrouped.filter { it == SKU_02 }.count() == 1)
            assert(transactionListGrouped.filter { it == SKU_03 }.count() == 1)
        }
    }

    companion object {
        val SKU_01 = "T2001"
        val SKU_02 = "T2002"
        val SKU_03 = "T2003"
    }
}