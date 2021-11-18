# Cocktail-App

The Cocktail App displays a list of popular cocktails, ingredients and measures required to make it, with features to search and also add to your favourite list.

## Note
The home page loads popular cocktails gotten from https://www.thecocktaildb.com. This endpoint is restricted to users that support TheCocktailDB so you'll need
to get an Api key. 

## Features
* Jetpack Compose
* Kotlin Coroutines
* Flow
* Clean Architecture with MVVM
* Dagger Hilt
* Retrofit

## Prerequisite
To build this project, you require:
- Android Studio Artic Fox and above
- Gradle 7.+
- Kotlin 1.5
- Create a key called COCKTAIL_API_KEY with the value being the api key you got from TheCocktailDB(Default is 1)
- Create a key called COCKTAIL_API_VERSION with the value being either v1 or v2 based on the key gotten from TheCocktailDB 

<h2 align="left">Screenshots</h2>

<img src="https://user-images.githubusercontent.com/19890036/142281731-b60af3de-3b4e-4f12-9181-e0d2f9c6f6d6.png" width="250" />  <img src="https://user-images.githubusercontent.com/19890036/142281922-e9c36c3e-a872-4ceb-b639-f80e7eeee45e.png" width="250" />   <img src="https://user-images.githubusercontent.com/19890036/142282605-d4e879ca-776a-4b7c-8115-205b3c1f2960.png" width="250" />

## Todos
* Test both the remote and local data source
* Add a filter feature(let users filter cocktails)