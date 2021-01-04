package valeria.moscoso.goliathnationalbankapp.data.mapper

import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction
import valeria.moscoso.goliathnationalbankapp.domain.model.dto.TransactionDto

object TransactionDtoMapper {

    fun fromListDtoToDomainList(listDto: List<TransactionDto>): List<Transaction> {
        return listDto.map { fromDtoToDomain(it) }
    }

    private fun fromDtoToDomain(transactionDto: TransactionDto): Transaction {
        return Transaction(
            transactionDto.sku,
            transactionDto.amount.toDouble(),
            transactionDto.currency
        )
    }
}
