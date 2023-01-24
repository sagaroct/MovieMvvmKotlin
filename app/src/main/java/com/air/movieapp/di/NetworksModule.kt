package com.air.movieapp.di

import android.content.Context
import com.air.movieapp.BuildConfig
import com.air.movieapp.common.Constants
import com.air.movieapp.data.network.MovieApiService
import com.air.movieapp.data.network.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//TODO: Can learn about qualifier later
/*@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Genuine

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Test*/

@Module
@InstallIn(SingletonComponent::class)
object NetworksModule {

  @Singleton
  @Provides
  fun provideRetrofit(): Retrofit {
    val builder: OkHttpClient.Builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
      val logging = HttpLoggingInterceptor()
      logging.setLevel(HttpLoggingInterceptor.Level.BODY)
      builder.addInterceptor(logging)
    }
    val okHttpClient: OkHttpClient = builder.build()
    return Retrofit.Builder()
      .baseUrl(Constants.RestConstants.BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Singleton
  @Provides
  fun providesMovieApiService(retrofit: Retrofit): MovieApiService {
    return retrofit.create(MovieApiService::class.java)
  }

  @Singleton
  @Provides
  fun provideNetworkUtils(@ApplicationContext appContext: Context): NetworkUtils {
    return NetworkUtils(appContext)
  }

}