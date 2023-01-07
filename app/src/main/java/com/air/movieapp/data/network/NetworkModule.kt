package com.air.movieapp.data.network

import android.content.Context
import com.air.movieapp.BuildConfig
import com.air.movieapp.common.RestConstants
import com.air.movieapp.data.database.MovieDatabase
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule(val context: Context) {

    @Provides
    @Singleton
    @Named("SimpleParsing")
    fun provideRetrofit(): Retrofit {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
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
        return NetworkUtils(context)
    }

    @Provides
    @Singleton
    @Named("SimpleService")
    fun providesSimpleService(@Named("SimpleInterface") apiInterface: MovieApiService): MoviesRepository {
        return MoviesRepository(apiInterface, MovieDatabase.getInstance(context))
    }

}