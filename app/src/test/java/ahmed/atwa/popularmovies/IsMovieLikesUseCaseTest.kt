package ahmed.atwa.popularmovies

import ahmed.atwa.popularmovies.domain.repository.MovieRepo
import ahmed.atwa.popularmovies.domain.useCase.IsMovieLiked
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

class IsMovieLikesUseCaseTest {

    val moviesRepo: MovieRepo = mock<MovieRepo>()
    val isMovieLikedUseCase by lazy { IsMovieLiked(moviesRepo) }

    @Test
    fun testingIsMovieLiked() {
        whenever(moviesRepo.isMovieLiked(any())).thenReturn(true)
        assert(isMovieLikedUseCase.invoke(1) == true)
    }

}