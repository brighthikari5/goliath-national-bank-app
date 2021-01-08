package valeria.moscoso.goliathnationalbankapp.domain.mapper


import valeria.moscoso.goliathnationalbankapp.domain.model.CurrencyRate
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction
import java.lang.RuntimeException


class CurrencyExchangeMapper {

    fun mapTransactionsLogic(
        transaction: Transaction,
        currencyRatesList: List<CurrencyRate>,
    ): Transaction = if (transaction.currency == CurrencyRate.EUR) {
        transaction
    } else {
        createTransactionByCurrency(transaction, currencyRatesList)
    }

    private fun createTransactionByCurrency(
        transaction: Transaction,
        currencyRatesList: List<CurrencyRate>,
    ): Transaction {
        return findCurrencyFromToEur(transaction.currency,
            currencyRatesList)?.let { currencyRateEur ->
            calculateCurrencyInEur(transaction, currencyRateEur)
        } ?: transformAmountInEurWhenNotProvided(
            transaction,
            currencyRatesList
        )
    }

    private fun findCurrencyFromToEur(
        currencyFrom: String,
        currencyRatesList: List<CurrencyRate>,
    ): CurrencyRate? =
        currencyRatesList.find { it.currencyFrom == currencyFrom && it.currencyTo == CurrencyRate.EUR }

    private fun calculateCurrencyInEur(
        transaction: Transaction,
        currencyRateEur: CurrencyRate,
    ): Transaction {
        val amount = currencyRateEur.calculateExchangeAmount(transaction.amount)
        return Transaction(transaction.sku, amount, CurrencyRate.EUR)
    }

    private fun transformAmountInEurWhenNotProvided(
        transaction: Transaction,
        currencyRatesList: List<CurrencyRate>,
    ): Transaction {
        val pathToEurCurrency = searchRateInEur(currencyRatesList, transaction.currency)

        var auxAmount = transaction.amount
        pathToEurCurrency.forEach {
            auxAmount = it.calculateExchangeAmount(auxAmount)
        }

        return Transaction(transaction.sku, auxAmount, CurrencyRate.EUR)
    }

    private fun searchRateInEur(
        currencyRatesList: List<CurrencyRate>,
        originalCurrency: String,
    ): List<CurrencyRate> {
        val resultList = currencyRatesList
            .filter { it.currencyFrom == originalCurrency }
            .flatMap {
                findCurrencyPath(currencyRatesList.toMutableList(), it)
            }

        if (resultList.isEmpty()) {
            throw RuntimeException("NO EUR CONVERSION FOUND")
        }

        return resultList
    }

    private fun findCurrencyPath(
        currencyRatesList: MutableList<CurrencyRate>,
        initialCurrency: CurrencyRate,
    ): List<CurrencyRate> {

        val mutableListRates = mutableListOf<CurrencyRate>()
        mutableListRates.add(initialCurrency)

        val listCurrenciesWithOriginalCurrencyTo: List<CurrencyRate> =
            currencyRatesList
                .filter { it.currencyFrom == initialCurrency.currencyTo }

        if (listCurrenciesWithOriginalCurrencyTo.isNotEmpty()) {
            val eurCurrencyRate: CurrencyRate? =
                getCurrencyToEurByList(listCurrenciesWithOriginalCurrencyTo)

            if (eurCurrencyRate == null) {
                findCurrencyEurInSubList(
                    listCurrenciesWithOriginalCurrencyTo,
                    currencyRatesList,
                    mutableListRates
                )
            } else {
                mutableListRates.add(eurCurrencyRate)
            }
        }

        if (!isCurrencyToEurInList(mutableListRates)) {
            mutableListRates.clear()
        }

        return mutableListRates
    }

    private fun findCurrencyEurInSubList(
        listCurrenciesWithOriginalCurrencyTo: List<CurrencyRate>,
        currencyRatesList: MutableList<CurrencyRate>,
        mutableListRates: MutableList<CurrencyRate>,
    ) {
        var found = false
        val iterator = listCurrenciesWithOriginalCurrencyTo.iterator()
        do {
            val currencyNext = iterator.next()
            currencyRatesList.remove(currencyNext)
            val subList = findCurrencyPath(currencyRatesList, currencyNext)
            if (isCurrencyToEurInList(subList)) {
                found = true
                mutableListRates.addAll(subList)
            }
        } while (iterator.hasNext() && !found)
    }

    private fun getCurrencyToEurByList(listCurrenciesWithOriginalCurrencyTo: List<CurrencyRate>): CurrencyRate? {
        return listCurrenciesWithOriginalCurrencyTo.find { it.currencyTo == CurrencyRate.EUR }
    }

    private fun isCurrencyToEurInList(listCurrenciesWithOriginalCurrencyTo: List<CurrencyRate>): Boolean {
        return getCurrencyToEurByList(listCurrenciesWithOriginalCurrencyTo) != null
    }
}