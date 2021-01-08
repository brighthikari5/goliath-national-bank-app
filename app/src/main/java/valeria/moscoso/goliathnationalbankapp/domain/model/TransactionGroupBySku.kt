package valeria.moscoso.goliathnationalbankapp.domain.model


class TransactionGroupBySku(
    val transactionList: List<Transaction>,
    val totalAmountEur: Double
)
