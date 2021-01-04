package valeria.moscoso.goliathnationalbankapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import valeria.moscoso.goliathnationalbankapp.data.repository.BankRepository

class GetInitialDataUseCase(private val repository: BankRepository) {

    fun execute(): Flow<Unit> = repository.downloadRates()
}