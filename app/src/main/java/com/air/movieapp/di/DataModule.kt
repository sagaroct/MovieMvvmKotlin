package com.air.movieapp.di

import android.content.Context
import com.air.movieapp.BuildConfig
import com.air.movieapp.common.Constants
import com.air.movieapp.data.database.MovieDatabase
import com.air.movieapp.data.network.MovieApiService
import com.air.movieapp.data.network.NetworkUtils
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

  @Provides
  @Singleton
  fun provideAppDatabase(@ApplicationContext appContext: Context): MovieDatabase {
    return MovieDatabase.getInstance(appContext)
  }

  @Provides
  fun provideDispatcher() = Dispatchers.IO


}