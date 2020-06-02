package test1.di

import com.google.gson.Gson
import test1.remote.ArticlesAuthorsService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test1.dataProvider.AuthorProviderImpl
import test1.dataProvider.FakeAuthorProvider

object ServiceLocator {
    private const val baseUrl = "https://jsonmock.hackerrank.com"
    private var authorInstanceArticles: ArticlesAuthorsService? = null

    private fun getRetrofit() = Retrofit.Builder().baseUrl(baseUrl)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

    fun getAuthorsArticlesService(): ArticlesAuthorsService {
        if (authorInstanceArticles == null) {
            authorInstanceArticles = getRetrofit().create(ArticlesAuthorsService::class.java)
        }
        return authorInstanceArticles!!
    }

    fun getAuthorProvider() = AuthorProviderImpl()

    fun getFakeAuthorProvider() = FakeAuthorProvider()
}
