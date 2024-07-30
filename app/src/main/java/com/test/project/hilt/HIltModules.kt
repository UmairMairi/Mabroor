package com.test.project.hilt

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.test.project.common.Constants
import com.test.project.data.remote.APIPaths
import com.test.project.data.remote.APIPathsTwo
import com.test.project.data.remote.AuthInterceptor
import com.test.project.data.repository.CommonRepositoryImpl
import com.test.project.data.repository.CommonRepositoryImplTwo
import com.test.project.data.room.ItemToSellDao
import com.test.project.data.room.ItemToSellDatabase
import com.test.project.domain.repository.CommonRepository
import com.test.project.domain.repository.CommonRepositoryTwo
import com.test.project.utility.interfaces.TokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun provideTokenProvider(): TokenProvider {
        return object : TokenProvider {
            override fun getToken(): String {
                return "YOUR_TOKEN_HERE"
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenProvider: TokenProvider): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenProvider))
            .build()
    }

    @Provides
    @Named("baseUrlOne")
    @Singleton
    fun provideRetrofitOne(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named("baseUrlTwo")
    @Singleton
    fun provideRetrofitTwo(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_TWO)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Named("apiPathsOne")
    @Singleton
    fun provideAPIPathsOne(@Named("baseUrlOne") retrofit: Retrofit): APIPaths {
        return retrofit.create(APIPaths::class.java)
    }

    @Provides
    @Named("apiPathsTwo")
    @Singleton
    fun provideAPIPathsTwo(@Named("baseUrlTwo") retrofit: Retrofit): APIPathsTwo {
        return retrofit.create(APIPathsTwo::class.java)
    }

    @Provides
    fun provideCallDataModel(@Named("apiPathsOne") apiPaths: APIPaths): CommonRepository {
        return CommonRepositoryImpl(apiPaths)
    }

    @Provides
    fun provideDataModel(@Named("apiPathsTwo") apiPaths: APIPathsTwo): CommonRepositoryTwo {
        return CommonRepositoryImplTwo(apiPaths)
    }

    @Provides
    fun provideItemToSellDao(database: ItemToSellDatabase): ItemToSellDao {
        return database.itemToSellDao()
    }

    @Provides
    fun provideItemToSellDatabase(context: Context): ItemToSellDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ItemToSellDatabase::class.java,
            "item_to_sell_database"
        ).build()
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}
