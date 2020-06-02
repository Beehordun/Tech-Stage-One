package test1.dataProvider

import test1.entity.Author

interface AuthorProvider {
    fun getAllAuthors(): List<Author>
}
