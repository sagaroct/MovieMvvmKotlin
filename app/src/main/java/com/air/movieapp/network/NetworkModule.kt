package com.air.movieapp.network

import android.content.Context
import com.air.movieapp.BuildConfig
import com.air.movieapp.common.RestConstants
import com.air.movieapp.data.DatabaseHelper
import java.io.IOException
import javax.inject.Named
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.Throws

@Module
class NetworkModule(context: Context?) {
    private val mContext: Context?

    @Provides
    @Singleton
    @Named("SimpleParsing")
    fun provideRetrofit(): Retrofit {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response? {
                val original: Request = chain.request()
                val response: Response = chain.proceed(original)
                response.cacheResponse()
                return response
            }
        })
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(logging)
        }
        val okHttpClient: OkHttpClient = builder.build()
        return Retrofit.Builder()
                .baseUrl(RestConstants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    @Named("SimpleInterface")
    fun providesApiSimpleInterface(
            @Named("SimpleParsing") retrofit: Retrofit): MovieApiService{
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkUtils(): NetworkUtils {
        return NetworkUtils(mContext)
    }

    @Provides
    @Singleton
    @Named("SimpleService")
    fun providesSimpleService(
            @Named("SimpleInterface") apiInterface: MovieApiService, networkUtils: NetworkUtils, databaseHelper: DatabaseHelper): MoviesRepository {
        return MoviesRepository(apiInterface, networkUtils, databaseHelper)
    }

    init {
        mContext = context
    }
}