package valeria.moscoso.goliathnationalbankapp.presentation.itemdetail

import java.text.DecimalFormat

fun formatAmountToView(amount: Double): String {
    val formatter = DecimalFormat("###,###,##0.00")
    return formatter.format(amount)
}