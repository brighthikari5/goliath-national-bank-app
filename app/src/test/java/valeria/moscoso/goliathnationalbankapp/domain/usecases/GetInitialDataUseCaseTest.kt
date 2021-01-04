package valeria.moscoso.goliathnationalbankapp.domain.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Test
import valeria.moscoso.goliathnationalbankapp.data.repository.BankRepository


class GetInitialDataUseCaseTest {

    private val repository: BankRepository = mock()

    private val useCase = GetInitialDataUseCase(repository)

    @Test
    fun `should trigger expected repository calls when executed`() {
        runBlocking {
            whenever(repository.downloadRates()).thenReturn(flowOf(Unit))
            whenever(repository.downloadTransactions()).thenReturn(flowOf(Unit))

            useCase.execute().single()

            verify(repository).downloadRates()
            verify(repository).downloadTransactions()
        }
    }
}