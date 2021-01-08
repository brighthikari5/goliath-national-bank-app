package valeria.moscoso.goliathnationalbankapp.presentation.itemdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import valeria.moscoso.goliathnationalbankapp.R
import valeria.moscoso.goliathnationalbankapp.domain.model.Transaction


class ItemDetailAdapter(private val transactionList: List<Transaction>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_transaction_detail, parent, false)
        return ItemDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemDetailViewHolder).bindData(transactionList[position])
    }

    override fun getItemCount() = transactionList.size
}
