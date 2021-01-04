package valeria.moscoso.goliathnationalbankapp.data.mapper

import org.junit.Test
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction
import valeria.moscoso.goliathnationalbankapp.domain.model.dto.TransactionDto

class TransactionDtoMapperTest {

    @Test
    fun `should map dto to domain when invoked`() {
        val transactionDtoList = listOf(TransactionDto("T2006", "10.00", "USD"))
        val transactionExpected = Transaction("T2006", 10.00, "USD")

        val actualTransaction = TransactionDtoMapper.fromListDtoToDomainList(transactionDtoList)[0]

        assert(transactionExpected.sku == actualTransaction.sku)
        assert(transactionExpected.amount == actualTransaction.amount)
        assert(transactionExpected.currency == actualTransaction.currency)
    }
}