package valeria.moscoso.goliathnationalbankapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.standalone.KoinComponent
import valeria.moscoso.goliathnationalbankapp.R

class MainActivity : AppCompatActivity(), KoinComponent {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.transactionsSkuList.observe(this) {
            Log.d("lista agrupada en activ", it.toString())
        }

        viewModel.onError.observe(this){
            //Show SnackBar here
        }
    }
}
