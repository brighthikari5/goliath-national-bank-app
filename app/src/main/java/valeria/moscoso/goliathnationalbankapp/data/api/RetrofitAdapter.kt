package valeria.moscoso.goliathnationalbankapp.data.api

import me.sianaki.flowretrofitadapter.FlowCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAdapter {

    fun createBankApiRetrofit(): BankApi = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .client(OkHttpClient.createOkHttpClient())
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(BankApi::class.java)
}