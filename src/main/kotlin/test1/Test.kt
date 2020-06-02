package test1

import test1.di.ServiceLocator

fun main() {
    val authorProvider = ServiceLocator.getAuthorProvider()
    val test = ArticlesDetails(authorProvider)

    println("Actives authors are: ")
    test.getUserNamesOfActiveAuthors(550).forEach {
        println(it)
    }

    println("User with max comment count is :${test.getUserNameWithHighestCommentCount()}")

    println("User name sorted by record date:")
    test.getUserNamesSortedByRecordDate(1255392958).forEach {
        println(it)
    }
}