package valeria.moscoso.goliathnationalbankapp.domain.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Test
import valeria.moscoso.goliathnationalbankapp.data.repository.BankRepository
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction

class GetTransactionsGroupUseCaseTest {

    private val repository: BankRepository = mock()
    private val useCase = GetTransactionsGroupUseCase(repository)

    @Test
    fun `should return a filtered by sku list when executed`() {
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

            val filteredList = useCase.execute(SKU_01).single()

            assert(filteredList.size == 2)
            assert(filteredList.find { it.sku == SKU_02 } == null)
            assert(filteredList.find { it.sku == SKU_03 } == null)
            assert(filteredList.find { it.sku == SKU_01 } != null)
        }
    }

    companion object {
        val SKU_01 = "T2001"
        val SKU_02 = "T2002"
        val SKU_03 = "T2003"
    }
}