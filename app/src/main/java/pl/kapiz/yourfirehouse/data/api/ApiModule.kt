package pl.kapiz.yourfirehouse.data.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.threeten.bp.LocalDateTime
import pl.kapiz.yourfirehouse.BuildConfig
import pl.kapiz.yourfirehouse.data.api.adapter.LocalDateTimeAdapter
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
    fun provideOkHttpClient(context: Context, jwtInterceptor: JWTInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .followRedirects(true)
            .callTimeout(30, SECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) level = Level.BODY
            })
            .addInterceptor(jwtInterceptor)
            .addInterceptor(ChuckerInterceptor(context))
            .build()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create(
        GsonBuilder()
            .setLenient()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
            .create()
    )

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://e-remiza.pl/Terminal/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create()
}
