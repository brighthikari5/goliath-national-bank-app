package valeria.moscoso.goliathnationalbankapp.domain.usecases

import kotlinx.coroutines.flow.*
import valeria.moscoso.goliathnationalbankapp.data.repository.BankRepository

class GetInitialDataUseCase(private val bankRepository: BankRepository) {

    fun execute(): Flow<Unit> =
        bankRepository.downloadRates()
            .flatMapConcat {
                bankRepository.downloadTransactions()
            }
}