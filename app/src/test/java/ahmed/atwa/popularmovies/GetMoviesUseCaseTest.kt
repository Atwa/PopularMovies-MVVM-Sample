package ahmed.atwa.popularmovies

import ahmed.atwa.popularmovies.domain.mapper.MovieEntity
import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import ahmed.atwa.popularmovies.domain.useCase.GetMovies
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.junit.Test

class GetMoviesUseCaseTest {

    val moviesRepo : MovieRepo = mock<MovieRepo>()
    val getMoviesUseCase by lazy { GetMovies(moviesRepo) }

    @Test
    suspend fun testingGetMoviesResult_Completed(){
        whenever(moviesRepo.getMovies()).thenReturn(flow{emit(emptyList<MovieEntity>())})
        getMoviesUseCase.invoke().collect{}
    }

}