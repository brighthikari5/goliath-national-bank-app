package valeria.moscoso.goliathnationalbankapp.data.api


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import valeria.moscoso.goliathnationalbankapp.BuildConfig
import java.util.concurrent.TimeUnit


object OkHttpClient {

    fun createOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(getLogInterceptor())
        .addInterceptor(buildHeaderInterceptor())
        .connectTimeout(ApiConstants.TIMEOUT_CONNECTION_VALUE, TimeUnit.SECONDS)
        .readTimeout(ApiConstants.TIMEOUT_READ_VALUE, TimeUnit.SECONDS)
        .writeTimeout(ApiConstants.TIMEOUT_WRITE_VALUE, TimeUnit.SECONDS)
        .build()

    private fun getLogInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    private fun buildHeaderInterceptor() =
        Interceptor { chain ->
            val request: Request =
                chain.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .build()
            chain.proceed(request)
        }
}