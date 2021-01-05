package valeria.moscoso.goliathnationalbankapp.presentation.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import valeria.moscoso.goliathnationalbankapp.R
import valeria.moscoso.goliathnationalbankapp.presentation.itemdetail.ItemDetailActivity

class ItemsAdapter(private val itemsList: List<String>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_sku_layout, parent, false)
        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemsViewHolder).bindData(itemsList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ItemDetailActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = itemsList.size
}
