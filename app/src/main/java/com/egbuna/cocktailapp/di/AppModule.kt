package com.egbuna.cocktailapp.di

import android.app.Application
import androidx.room.Room
import com.egbuna.cocktailapp.common.Constant.BASE_URL
import com.egbuna.cocktailapp.data.local.CocktailDao
import com.egbuna.cocktailapp.data.local.CocktailDatabase
import com.egbuna.cocktailapp.data.remote.CocktailApi
import com.egbuna.cocktailapp.data.repository.CocktailRepositoryImpl
import com.egbuna.cocktailapp.domain.repository.CocktailRepository
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

    @Provides
    @Singleton
    fun provideCocktailDatabase(app: Application): CocktailDatabase {
        return Room.databaseBuilder(
            app,
            CocktailDatabase::class.java,
            CocktailDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCocktailApi(): CocktailApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CocktailApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCocktailRepository(api: CocktailApi, cocktailDao: CocktailDatabase): CocktailRepository {
        return CocktailRepositoryImpl(api, cocktailDao = cocktailDao.cocktailDao)
    }
}