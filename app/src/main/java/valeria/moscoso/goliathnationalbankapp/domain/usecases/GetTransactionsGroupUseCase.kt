package valeria.moscoso.goliathnationalbankapp.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import valeria.moscoso.goliathnationalbankapp.data.repository.BankRepository
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction

class GetTransactionsGroupUseCase(
    private val bankRepository: BankRepository
) {

    fun execute(sku: String): Flow<List<Transaction>> =
        bankRepository.getTransactions()
            .map {
                it.filter { transaction ->
                    transaction.sku == sku
                }
            }
}

