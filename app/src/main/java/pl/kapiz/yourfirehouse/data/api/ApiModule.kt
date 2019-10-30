package pl.kapiz.yourfirehouse.data.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import pl.kapiz.yourfirehouse.BuildConfig
import pl.kapiz.yourfirehouse.data.api.interceptor.JWTInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideApiService(
        context: Context,
        okHttpClient: OkHttpClient,
        jwtInterceptor: JWTInterceptor
    ): ApiService = Retrofit.Builder()
        .baseUrl("https://e-remiza.pl/Terminal/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(
            okHttpClient.newBuilder()
                .followRedirects(true)
                .callTimeout(30, SECONDS)
                .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) level = Level.BODY
                })
                .addInterceptor(jwtInterceptor)
                .addInterceptor(ChuckerInterceptor(context))
                .build()
        )
        .build()
        .create()


    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient()
}
