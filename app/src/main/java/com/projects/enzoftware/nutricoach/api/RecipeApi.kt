package com.projects.enzoftware.nutricoach.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.projects.enzoftware.nutricoach.BuildConfig
import com.projects.enzoftware.nutricoach.model.RecipesContainer
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    // 1 - Get your Food2Fork API key from http://food2fork.com/about/api
    // 2 - Create a keystore.properties file with the following content (including the quotes):
    //     FOOD2FORK_API_KEY="YOUR API KEY"

    @GET("search?key=" + BuildConfig.FOOD2FORK_API_KEY)
    fun search(@Query("q") query: String): Call<RecipesContainer>

    companion object Factory {
        fun create(): RecipeApi {
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://food2fork.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(RecipeApi::class.java)
        }
    }
}