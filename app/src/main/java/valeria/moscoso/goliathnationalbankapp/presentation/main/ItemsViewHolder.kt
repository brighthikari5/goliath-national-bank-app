package valeria.moscoso.goliathnationalbankapp.presentation.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_sku_layout.view.*

class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(sku: String) {
        itemView.sku_name_tv.text = sku
    }
}