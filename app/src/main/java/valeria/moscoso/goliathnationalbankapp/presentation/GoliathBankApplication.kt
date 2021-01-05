package valeria.moscoso.goliathnationalbankapp.presentation

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.standalone.KoinComponent
import valeria.moscoso.goliathnationalbankapp.presentation.di.generalModules


class GoliathBankApplication: Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, generalModules)
    }

}