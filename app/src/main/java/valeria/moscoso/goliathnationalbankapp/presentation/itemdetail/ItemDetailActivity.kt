package valeria.moscoso.goliathnationalbankapp.presentation.itemdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_item_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import valeria.moscoso.goliathnationalbankapp.R
import valeria.moscoso.goliathnationalbankapp.domain.model.TransactionGroupBySku

class ItemDetailActivity : AppCompatActivity() {

    private val itemDetailViewModel: ItemDetailViewModel by viewModel()
    private lateinit var itemsDetailAdapter: ItemDetailAdapter

    companion object {
        val EXTRA_SKU = "EXTRA_SKU"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val sku = intent.getStringExtra(EXTRA_SKU) ?: ""
        item_detail_sku.text = sku
        itemDetailViewModel.getTransactions(sku)
        setObservers()
        item_detail_recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setObservers() {
        itemDetailViewModel.transactionGroupBySku.observe(this) {
            item_detail_total_amount.text = it.totalAmountEur.toString()
            itemsDetailAdapter = ItemDetailAdapter(it.transactionList)
            item_detail_recyclerView.adapter = itemsDetailAdapter
            itemsDetailAdapter.notifyDataSetChanged()
        }

        itemDetailViewModel.onError.observe(this) {
            Log.e("errorr", it.toString())
            Toast.makeText(this, "And error ocurred, please try again", Toast.LENGTH_SHORT).show()
        }
    }
}