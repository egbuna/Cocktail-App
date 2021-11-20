package com.egbuna.cocktailapp.domain.usecase

import com.egbuna.cocktailapp.FakeCocktailRepository
import com.egbuna.cocktailapp.domain.model.Cocktail
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class SaveCocktailTest {

    lateinit var saveCocktail: SaveCocktail
    lateinit var fakeCocktailRepository: FakeCocktailRepository
    lateinit var getFavouriteCocktail: GetFavouriteCocktail

    @Before
    fun setUp() {

        fakeCocktailRepository = FakeCocktailRepository()
        saveCocktail = SaveCocktail(fakeCocktailRepository)
        getFavouriteCocktail = GetFavouriteCocktail(fakeCocktailRepository)

        val fakeCocktailToInsert = mutableListOf<Cocktail>()

        ('a'..'g').forEachIndexed { index, c ->
            fakeCocktailToInsert.add(
                Cocktail(
                    id = index.toString(),
                    category = index.toString(),
                    drink = c.toString(),
                    drinkThumb = c.toString(),
                    imageUrl = null
                )
            )
        }
        fakeCocktailToInsert.shuffle()
        runBlocking {
            fakeCocktailToInsert.forEach {
                fakeCocktailRepository.saveCocktail(
                    it
                )
            }
        }
    }

    @Test
    fun `Save favourite cocktail`() = runBlocking {
        saveCocktail(
            Cocktail(
                id = "fake",
                category = "fake",
                drink = "fake",
                drinkThumb = "fake",
                imageUrl = null
            )
        )
        val cocktails = getFavouriteCocktail().first()
        assertThat(cocktails).hasSize(8)
    }
}