package com.egbuna.cocktailapp.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.egbuna.cocktailapp.domain.model.Cocktail

@Database(
    entities = [Cocktail::class],
    version = 1,
    exportSchema = false
)
abstract class CocktailDatabase : RoomDatabase() {

    abstract val cocktailDao: CocktailDao

    companion object {
        const val DATABASE_NAME = "cocktail_db"
    }

}