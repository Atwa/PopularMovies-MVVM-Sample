package ahmed.atwa.popularmovies

import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import ahmed.atwa.popularmovies.domain.useCase.ChangeLikeState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class ChangeLikeStateUseCaseTest {

    val moviesRepo: MovieRepo = mock<MovieRepo>()
    val changeLikeStateUseCase by lazy { ChangeLikeState(moviesRepo) }

    @Test
    fun testingChangeLikeState_FromFalseToTrue() {
        runBlockingTest {
            whenever(moviesRepo.changeLikeState(10, true)).thenReturn(1)
            val result = changeLikeStateUseCase.invoke(10,true)
            assert(result == 1)
        }
    }

}