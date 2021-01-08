package valeria.moscoso.goliathnationalbankapp.presentation.itemdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_item_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import valeria.moscoso.goliathnationalbankapp.R

class ItemDetailActivity : AppCompatActivity() {

    private val itemDetailViewModel: ItemDetailViewModel by viewModel()

    companion object {
        val EXTRA_SKU = "EXTRA_SKU"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        val sku = intent.getStringExtra(EXTRA_SKU)?:""

        itemDetailViewModel.getTransactions(sku)
        setObservers()

    }

    private fun setObservers() {
        itemDetailViewModel.transactionGroupBySku.observe(this) {
            Log.d("suma total", it.totalAmountEur.toString())
            Log.d ("lista", it.transactionList.toString())
        }

        itemDetailViewModel.onError.observe(this){
            Log.e ("errorr", it.toString())
            Toast.makeText(this, "And error ocurred, please try again", Toast.LENGTH_SHORT).show()
        }

    }
}