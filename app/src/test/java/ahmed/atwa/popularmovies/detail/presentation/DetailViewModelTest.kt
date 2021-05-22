package ahmed.atwa.popularmovies.detail.presentation

import ahmed.atwa.popularmovies.CoroutineTestingRule
import ahmed.atwa.popularmovies.detail.data.TrailerRemote
import ahmed.atwa.popularmovies.movies.data.MovieRepo
import ahmed.atwa.popularmovies.movies.domain.MovieEntity
import ahmed.atwa.popularmovies.base.ViewState
import ahmed.atwa.popularmovies.config.commons.TestDispatcher
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DetailViewModelTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutineTestingRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var moviesRepo: MovieRepo
    private lateinit var detailViewModel: DetailViewModel
    private val mockTrailer = TrailerRemote("1", "", "", "", "test", "youtube", 23, "trailer")
    private val mockTrailerResponse = arrayListOf(mockTrailer, mockTrailer, mockTrailer)

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)
        moviesRepo = mock()
        detailViewModel = DetailViewModel(
                moviesRepo,
                TestDispatcher()
        )
    }

    @Test
    fun test_GetTrailers_success() {
        val expected = mockTrailerResponse
        runBlockingTest {
            whenever(moviesRepo.fetchMovieTrailers(any())).thenReturn(mockTrailerResponse)
            detailViewModel.apply {
                fetchMovieTrailers(1)
                uiState.observeForever {
                    val actual = ((it as ViewState.HasData<*>).data as DetailViewState.TrailersFetched<*>).data as ArrayList<TrailerRemote>
                    assert(expected == actual)
                }
                verify(moviesRepo, times(1)).fetchMovieTrailers(any())
            }
        }
    }


    @Test
    fun test_GetTrailers_failure() {
        val expected = "No trailers found"
        runBlockingTest {
            whenever(moviesRepo.fetchMovieTrailers(any())).thenReturn(null)
            detailViewModel.apply {
                fetchMovieTrailers(1)
                uiState.observeForever {
                    val actual = (it as ViewState.HasError).error
                    assert(expected == actual)
                }
                verify(moviesRepo, times(1)).fetchMovieTrailers(any())
            }
        }
    }

    @Test
    fun test_GetMovieLikeState() {
        val expected = true
        runBlockingTest {
            whenever(moviesRepo.isMovieLiked(any())).thenReturn(true)
            detailViewModel.apply {
                getLikeState(1)
                uiState.observeForever {
                    val actual = ((it as ViewState.HasData<*>).data as DetailViewState.LikeState).isLiked
                    assert(expected == actual)
                }
                verify(moviesRepo, times(1)).isMovieLiked(any())
            }
        }
    }

    @Test
    fun test_updateLikeStatus_false() {
        val mockMovieEntity = MovieEntity(1, true, "", 3.4, 12, true, 2.3, "test", "", "", "")
        val expected = false
        runBlockingTest {
            whenever(moviesRepo.changeLikeState(1, false)).thenReturn(1)
            detailViewModel.apply {
                updateLikeStatus(mockMovieEntity)
                uiState.observeForever {
                    val actual = ((it as ViewState.HasData<*>).data as DetailViewState.LikeState).isLiked
                    assert(expected == actual)
                }
                verify(moviesRepo, times(1)).changeLikeState(1, false)
            }
        }
    }

}