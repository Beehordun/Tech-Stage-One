package test1

import test1.entity.Author
import test1.dataProvider.AuthorProvider

class ArticlesDetails (dataSource: AuthorProvider) {

    private var authors: List<Author> = dataSource.getAllAuthors()

    fun getUserNamesOfActiveAuthors(submissionCountThreshold: Int): List<String> =
        authors.filter {
            it.submissionCount >= submissionCountThreshold
        }.map {
            it.userName
        }

    fun getUserNameWithHighestCommentCount(): String = authors.maxBy { it.commentCount }?.userName!!

    fun getUserNamesSortedByRecordDate(threshold: Int): List<String> =
        authors.filter {
            it.createdAt >= threshold
        }.sortedBy {
            it.createdAt
        }.map {
            it.userName
        }
}