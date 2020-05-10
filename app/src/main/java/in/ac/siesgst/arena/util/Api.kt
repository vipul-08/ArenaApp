package `in`.ac.siesgst.arena.util

import `in`.ac.siesgst.arena.model.Contest
import `in`.ac.siesgst.arena.model.Problem
import `in`.ac.siesgst.arena.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("user")
    fun user(
        @Query("username") username: String
    ): Call<User>

    @GET("contests")
    fun contests(): Call<List<Contest>>

    @GET("problems")
    fun problems(): Call<List<Problem>>

}