package com.olavarria.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.olavarria.core.common.NullToEmptyStringAdapter
import com.olavarria.core.di.preferences.Preferences
import com.olavarria.data.BuildConfig
import com.olavarria.data.api.ApiConstants
import com.olavarria.data.api.ApiService
import com.olavarria.data.api.ApiServiceToken
import com.olavarria.data.api.interceptor.AuthInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(@Named("OKHTTP") okHttpClient: OkHttpClient,
                               preferences: Preferences) =
        AuthInterceptor(okHttpClient, preferences)

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context = context).build()
    }

    @Provides
    @Singleton
    @Named("OKHTTP")
    fun provideOkHttpClient(
        //applicationInterceptor: ApplicationInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                addNetworkInterceptor(interceptor)
                addInterceptor(chuckerInterceptor)
            }
            //addInterceptor(applicationInterceptor)
        }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    @Named("OKHTTP_INTERCEPTOR")
    fun provideOkHttpClientInterceptor(
        authInterceptor: AuthInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addInterceptor(chuckerInterceptor)
            }
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            addNetworkInterceptor(interceptor)
            addInterceptor(authInterceptor)
        }
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun providerRetrofit(@Named("OKHTTP_INTERCEPTOR")okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("RETROFIT_TOKEN")
    fun providerRetrofitToken(@Named("OKHTTP")okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(NullToEmptyStringAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServiceToken(@Named("RETROFIT_TOKEN") retrofit: Retrofit): ApiServiceToken {
        return retrofit.create(ApiServiceToken::class.java)
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

}