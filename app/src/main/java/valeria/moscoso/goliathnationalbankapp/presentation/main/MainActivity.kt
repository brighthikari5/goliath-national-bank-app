package valeria.moscoso.goliathnationalbankapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.standalone.KoinComponent
import valeria.moscoso.goliathnationalbankapp.R

class MainActivity : AppCompatActivity(), KoinComponent {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setObservers()
        main_recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setObservers() {
        progressBar_layout.visibility = View.VISIBLE
        viewModel.transactionsSkuList.observe(this) {
            progressBar_layout.visibility = View.INVISIBLE
            itemsAdapter = ItemsAdapter(it, this)
            main_recyclerView.adapter = itemsAdapter
            itemsAdapter.notifyDataSetChanged()
        }

        viewModel.onError.observe(this) {
            progressBar_layout.visibility = View.INVISIBLE
            Toast.makeText(this, "And error ocurred, please try again", Toast.LENGTH_SHORT).show()
        }
    }
}
