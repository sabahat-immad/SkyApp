package com.nagot.sky.di.module

import android.app.Application
import androidx.annotation.NonNull
import com.nagot.sky.data.RemoteDataSource
import com.nagot.sky.utils.NetworkUtils
import com.nagot.sky.data.api.RemoteMoviesDataSource
import com.nagot.sky.data.api.service.DiscoverService
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://movies-sample.herokuapp.com/"
        private const val CACHE_SIZE: Long = (5 * 1024 * 1024)
        private const val HEADER_CACHE_CONTROL = "Cache-Control"
        private const val PRAGMA = "pragma"
        private const val TTL_ONLINE = 5
        private const val TTL_OFFLINE = 10
    }

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    @Singleton
    fun provideCache(@NonNull application: Application): Cache {

        return Cache(application.cacheDir, CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        @NonNull httpInterceptor: HttpLoggingInterceptor,
        @NonNull application: Application,
        @NonNull appCache: Cache
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .cache(appCache)
            .addInterceptor(httpInterceptor)
            .addInterceptor { chain ->

                var request = chain.request()

                request = if (NetworkUtils.hasNetwork(application)!!) {

                    getOnlineRequest(request)

                } else {

                    getOfflineRequest(request)
                }

                chain.proceed(request)
            }
            .build()
    }

    private fun getOnlineRequest(request: Request): Request {

        val cacheControl = CacheControl.Builder()
            .maxAge(TTL_ONLINE, TimeUnit.SECONDS)
            .build()

        return request.newBuilder()
            .removeHeader(PRAGMA)
            .removeHeader(HEADER_CACHE_CONTROL)
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .build()
    }

    private fun getOfflineRequest(request: Request): Request {

        val cacheControl = CacheControl.Builder()
            .maxStale(TTL_OFFLINE, TimeUnit.MINUTES)
            .build()

        return request.newBuilder()
            .removeHeader(HEADER_CACHE_CONTROL)
            .removeHeader(PRAGMA)
            .cacheControl(cacheControl)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()

            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideDiscoverService(@NonNull retrofit: Retrofit): DiscoverService {

        return retrofit.create(DiscoverService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRemoteDataSource(discoverService: DiscoverService): RemoteDataSource {

        return RemoteMoviesDataSource(discoverService)
    }
}