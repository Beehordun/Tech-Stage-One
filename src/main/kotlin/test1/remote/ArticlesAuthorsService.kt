package test1.remote

import test1.entity.AuthorsData
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesAuthorsService {

    @GET("/api/article_users/search")
    suspend fun getAuthorData(@Query("page") page: Int): AuthorsData
}