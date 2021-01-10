package valeria.moscoso.goliathnationalbankapp.presentation.itemdetail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_transaction_detail.view.*
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction


class ItemDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(transaction: Transaction) {
        itemView.item_amount_value.text = formatAmountToView(transaction.amount)
        itemView.item_amount_currency_value.text = transaction.currency
    }
}