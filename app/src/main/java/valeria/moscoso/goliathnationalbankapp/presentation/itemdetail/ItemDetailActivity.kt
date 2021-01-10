package valeria.moscoso.goliathnationalbankapp.presentation.itemdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_item_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import valeria.moscoso.goliathnationalbankapp.R

class ItemDetailActivity : AppCompatActivity() {

    private val itemDetailViewModel: ItemDetailViewModel by viewModel()
    private lateinit var itemsDetailAdapter: ItemDetailAdapter

    companion object {
        const val EXTRA_SKU = "EXTRA_SKU"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        val sku = intent.getStringExtra(EXTRA_SKU) ?: ""
        item_detail_sku.text = sku
        itemDetailViewModel.getTransactions(sku)
        setObservers()
        item_detail_recyclerView.layoutManager = LinearLayoutManager(this)
        title = getString(R.string.item_detail_toolbar)
    }

    private fun setObservers() {
        itemDetailViewModel.transactionGroupBySku.observe(this) {
            val amountFormatted = getString(R.string.euro_value, formatAmountToView(it.totalAmountEur))
            item_detail_total_amount.text = amountFormatted
            itemsDetailAdapter = ItemDetailAdapter(it.transactionList)
            item_detail_recyclerView.adapter = itemsDetailAdapter
            itemsDetailAdapter.notifyDataSetChanged()
        }
        itemDetailViewModel.onError.observe(this) {
            Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        }
    }
}