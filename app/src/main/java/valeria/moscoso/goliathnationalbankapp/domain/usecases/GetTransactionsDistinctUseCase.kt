package valeria.moscoso.goliathnationalbankapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import valeria.moscoso.goliathnationalbankapp.data.repository.BankRepository

class GetTransactionsDistinctUseCase(private val bankRepository: BankRepository) {

    fun execute(): Flow<List<String>> =
        bankRepository.getTransactions().map { list ->
            list.map {
                it.sku
            }.distinct()
        }
}