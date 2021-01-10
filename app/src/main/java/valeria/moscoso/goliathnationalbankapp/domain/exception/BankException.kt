package valeria.moscoso.goliathnationalbankapp.domain.exception


class BankException(
    errorType: ErrorType
) : RuntimeException(
    errorType.message
)

enum class ErrorType(val message: String) {
    CONVERSION_NOT_FOUND("No Euro conversion found")
}

