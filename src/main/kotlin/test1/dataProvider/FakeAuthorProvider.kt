package test1.dataProvider

import test1.entity.Author

class FakeAuthorProvider (): AuthorProvider {

    override fun getAllAuthors(): List<Author> {
       return listOf(
           Author(1, "Ugo Bee", 5, 6, 100),
           Author(2, "Kelvin Kay", 6, 9, 99),
           Author(3, "Nathan E", 7, 9, 101),
           Author(4, "George Floyd", 9, 78, 102),
           Author(5, "Derek Chauvin", 10, 9, 104),
           Author(6, "Darnella Frazier", 11, 9, 98)
       )
    }

}