package com.wolfcodea.randombleps.di

import com.wolfcodea.randombleps.core.endpoints.Endpoints
import com.wolfcodea.randombleps.data.datasource.AnimalsRandomRemoteDatasource
import com.wolfcodea.randombleps.data.datasource.AnimalsTagRemoteDatasource
import com.wolfcodea.randombleps.data.datasource.IAnimalsRandomApi
import com.wolfcodea.randombleps.data.datasource.IAnimalsTagApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAnimalsTagRemoteDatasource(api: IAnimalsTagApi) = AnimalsTagRemoteDatasource(api)

    @Singleton
    @Provides
    fun provideAnimalsApi(): IAnimalsTagApi {
        return Retrofit.Builder().baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IAnimalsTagApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAnimalsRandomRemoteDatasource(api: IAnimalsRandomApi) = AnimalsRandomRemoteDatasource(api)

    @Singleton
    @Provides
    fun provideAnimalsRandomApi(): IAnimalsRandomApi {
        return Retrofit.Builder().baseUrl(Endpoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IAnimalsRandomApi::class.java)
    }

}