package id.ergun.myfootballdb.configs

import android.util.Log
import id.ergun.myfootballdb.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object {
        private fun getClient(): Retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL + "api/v1/json/1/")
                    .client(mClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

        private fun mClient(): OkHttpClient = OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor())
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()

        private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
            val httpLoggingInterceptor = HttpLoggingInterceptor {
                message -> Log.e("HTTP Log: ", message)
            }
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return httpLoggingInterceptor
        }

        fun getApiService(): ApiService = getClient().create(ApiService::class.java)
    }
}