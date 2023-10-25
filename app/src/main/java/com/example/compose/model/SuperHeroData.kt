package com.example.compose.model

import com.example.compose.R

class SuperHeroData {
    private val superHeroes:List<SuperheroDataClass> = listOf<SuperheroDataClass>(
        SuperheroDataClass(
            "Superhero 1",
            "Description of Superhero 1",
            imageUrl = R.drawable.android_superhero1
            // Provide ImageVector for Superhero 1
        ),
        SuperheroDataClass(
            "Superhero 2",
            "Description of Superhero 2",
            imageUrl = R.drawable.android_superhero2
        ),
        SuperheroDataClass(
            "Superhero 3",
            "Description of Superhero 3",
            imageUrl = R.drawable.android_superhero3
        ),
        SuperheroDataClass(
            "Superhero 4",
            "Description of Superhero 4",
            imageUrl = R.drawable.android_superhero4
        ),
        SuperheroDataClass(
            "Superhero 5",
            "Description of Superhero 5",
            imageUrl = R.drawable.android_superhero5
        ),
        SuperheroDataClass(
            "Superhero 6",
            "Description of Superhero 6",
            imageUrl = R.drawable.android_superhero6
        )
    )


    fun getSuperHeroes() : List<SuperheroDataClass> {
        return superHeroes
    }
}