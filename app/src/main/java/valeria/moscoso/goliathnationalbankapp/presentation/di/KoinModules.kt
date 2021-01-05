package valeria.moscoso.goliathnationalbankapp.presentation.di

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import valeria.moscoso.goliathnationalbankapp.data.api.RetrofitAdapter
import valeria.moscoso.goliathnationalbankapp.data.datasource.BankCloudDataSource
import valeria.moscoso.goliathnationalbankapp.data.datasource.BankLocalDataSource
import valeria.moscoso.goliathnationalbankapp.data.repository.BankRepository
import valeria.moscoso.goliathnationalbankapp.domain.usecases.GetInitialDataUseCase
import valeria.moscoso.goliathnationalbankapp.domain.usecases.GetTransactionsGroupByNameUseCase
import valeria.moscoso.goliathnationalbankapp.presentation.main.MainViewModel


val serviceModule = module {
    single { RetrofitAdapter.createBankApiRetrofit() }
}

val dataModule = module {
    single { BankCloudDataSource(bankApi = get()) }
    single { BankLocalDataSource() }
}

val repositoryModule = module {
    single { BankRepository(cloudDataSource = get(), localDataSource = get()) }
}

val useCaseModule = module {
    factory { GetInitialDataUseCase(bankRepository = get()) }
    factory { GetTransactionsGroupByNameUseCase(bankRepository = get()) }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(
            getInitialDataUseCase = get(),
            getTransactionsGroupByNameUseCase = get()
        )
    }
}

val generalModules =
    listOf(serviceModule, dataModule, repositoryModule, useCaseModule, viewModelModule)