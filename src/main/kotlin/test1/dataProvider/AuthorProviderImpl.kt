package test1.dataProvider

import test1.di.ServiceLocator
import test1.entity.Author
import test1.entity.AuthorsData
import kotlinx.coroutines.*

class AuthorProviderImpl : AuthorProvider {
    private val authorsService = ServiceLocator.getAuthorsArticlesService()

    override fun getAllAuthors(): List<Author> = runBlocking {
        val scope = CoroutineScope(Job())
        val allAuthors: MutableList<Author> = mutableListOf()

        val job = scope.launch(Dispatchers.IO) {
            val (totalPages, firstPageContent) = getTotalPagesAndFirstPageContent()
            allAuthors.addAll(firstPageContent)
            val otherPagesContent = getSecondPageToLastPageContent(totalPages)
            allAuthors.addAll(otherPagesContent)
        }

        job.join()
        return@runBlocking allAuthors
    }

    private suspend fun getTotalPagesAndFirstPageContent(): Pair<Int, List<Author>> {
        val data: AuthorsData = authorsService.getAuthorData(1)
        return Pair(data.totalPages, data.authors)
    }

    private suspend fun getSecondPageToLastPageContent(totalPages: Int): List<Author> {
        val authorsData: MutableList<Author> = mutableListOf()

        for (i in 2..totalPages) {
            authorsService.getAuthorData(i).authors.also {
                authorsData.addAll(it)
            }
        }
        return authorsData
    }
}
