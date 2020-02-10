package ahmed.atwa.popularmovies

import ahmed.atwa.popularmovies.domain.mapper.MovieEntity
import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import ahmed.atwa.popularmovies.domain.useCase.GetMovies
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class GetMoviesUseCaseTest {

    val moviesRepo: MovieRepo = mock()
    val getMoviesUseCase by lazy { GetMovies(moviesRepo) }

    @Test
    fun testingGetMoviesResult_Completed() {
        runBlockingTest {
            whenever(moviesRepo.getMovies()).thenReturn(flow { emit(emptyList<MovieEntity>()) })
            getMoviesUseCase.invoke().collect {
               assert(it == emptyList<MovieEntity>())
           }
        }
    }

    @Test
    fun testingGetMoviesResult_Result_Null() {
        runBlockingTest {
            whenever(moviesRepo.getMovies()).thenReturn(flow { emit(null) })
            getMoviesUseCase.invoke().collect {
                assert(it == null)
            }
        }
    }

}