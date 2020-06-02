import test1.ArticlesDetails
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import test1.di.ServiceLocator

class ArticlesDetailsTest {

    lateinit var articleDetails: ArticlesDetails

    @Before
    fun setUp() {
        val dataSource = ServiceLocator.getFakeAuthorProvider()
        articleDetails = ArticlesDetails(dataSource)
    }

    @Test
    fun getUserNamesOfActiveAuthorsReturnsValidResultWhenThresholdIsValid() {
        val result = articleDetails.getUserNamesOfActiveAuthors(5)
        Assert.assertEquals(result.size, 6)
    }

    @Test
    fun getUserNamesOfActiveAuthorsReturnsEmptyListWhenThresholdDoesNotMatch() {
        val result = articleDetails.getUserNamesOfActiveAuthors(12)
        Assert.assertEquals(0, result.size)
    }

    @Test
    fun getUserNameWithHighestCommentCountReturnsValidResult() {
        val result = articleDetails.getUserNameWithHighestCommentCount()
        Assert.assertEquals(result, "George Floyd")
    }

    @Test
    fun getUserNamesSortedByRecordDateReturnsValidResultWhenThresholdIsValid() {
        val result = articleDetails.getUserNamesSortedByRecordDate(99)
        val expectedResult = listOf("Kelvin Kay", "Ugo Bee",
            "Nathan E", "George Floyd", "Derek Chauvin")
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun getUserNamesSortedByRecordDateReturnsEmptyListWhenThresholdDoesNotMatch() {
        val result = articleDetails.getUserNamesSortedByRecordDate(9999)
        Assert.assertEquals(0, result.size)
    }
}
