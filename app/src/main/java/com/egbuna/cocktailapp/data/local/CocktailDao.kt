package com.egbuna.cocktailapp.data.local

import androidx.room.*
import com.egbuna.cocktailapp.domain.model.Cocktail
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {

    @Query("SELECT * FROM cocktail")
    fun getCocktails(): Flow<List<Cocktail>>

    @Query("SELECT * FROM cocktail WHERE id = :id")
    suspend fun getCocktailById(id: String): Cocktail?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktail(note: Cocktail)

    @Delete
    suspend fun deleteCocktail(cocktail: Cocktail)

}
