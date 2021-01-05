package valeria.moscoso.goliathnationalbankapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import valeria.moscoso.goliathnationalbankapp.R

class ItemsAdapter(private val itemsList: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_sku_layout, parent, false)
        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemsViewHolder).bindData(itemsList[position])
    }

    override fun getItemCount() = itemsList.size
}
