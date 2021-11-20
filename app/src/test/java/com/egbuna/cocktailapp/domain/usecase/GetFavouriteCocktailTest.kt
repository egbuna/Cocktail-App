package com.egbuna.cocktailapp.domain.usecase

import com.egbuna.cocktailapp.FakeCocktailRepository
import com.egbuna.cocktailapp.domain.model.Cocktail
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFavouriteCocktailTest {

    lateinit var getFavouriteCocktail: GetFavouriteCocktail
    lateinit var fakeCocktailRepository: FakeCocktailRepository

    @Before
    fun setUp() {

        fakeCocktailRepository = FakeCocktailRepository()
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
    fun `Get all favourite cocktail`() = runBlocking {
        val cocktails = getFavouriteCocktail().first()
        assertThat(cocktails).hasSize(7)
    }
}