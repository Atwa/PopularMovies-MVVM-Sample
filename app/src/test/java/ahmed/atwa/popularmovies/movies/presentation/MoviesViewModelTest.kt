package ahmed.atwa.popularmovies.movies.presentation

import ahmed.atwa.popularmovies.CoroutineTestingRule
import ahmed.atwa.popularmovies.detail.data.TrailerRemote
import ahmed.atwa.popularmovies.detail.data.TrailerResponse
import ahmed.atwa.popularmovies.detail.presentation.DetailViewState
import ahmed.atwa.popularmovies.main.presentation.MoviesViewModel
import ahmed.atwa.popularmovies.movies.data.Movie
import ahmed.atwa.popularmovies.movies.data.MovieRepo
import ahmed.atwa.popularmovies.movies.data.MovieResponse
import ahmed.atwa.popularmovies.movies.domain.MovieEntity
import ahmed.atwa.popularmovies.utils.commons.TestDispatcher
import ahmed.atwa.popularmovies.utils.network.ResultType
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.io.IOException

@ExperimentalCoroutinesApi
class MoviesViewModelTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutineTestingRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var moviesRepo: MovieRepo
    private lateinit var moviesViewModel: MoviesViewModel

    private val mockMovieEntity = MovieEntity(1, true, "", 3.4, 12, true, 2.3, "test", "", "", "")
    private val mockMovieRemote = Movie(10, 1, true, 7.8, "", 3.4,
            "test", "", "", "", true, "", "")
    private val mockMovieEntityList = listOf(mockMovieEntity, mockMovieEntity, mockMovieEntity)
    private val mockMovieResponse = MovieResponse(page = 1,
            total_pages = 1,
            total_results = 3,
            results = arrayListOf(mockMovieRemote, mockMovieRemote, mockMovieRemote))

    private val mockTrailer = TrailerRemote("1", "", "", "", "test", "youtube", 23, "trailer")
    private val mockTrailerResponse = TrailerResponse(id = 1, results = arrayListOf(mockTrailer, mockTrailer, mockTrailer))

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)
        moviesRepo = mock()
        moviesViewModel = MoviesViewModel(
                moviesRepo,
                TestDispatcher()
        )
    }

    @Test
    fun testingGetMoviesLocalSuccess_butFailureRemote_thenHasError() {
        runBlockingTest {
            whenever(moviesRepo.fetchMoviesLocal()).thenReturn(mockMovieEntityList)
        }
        moviesViewModel.apply {
            getMovies()
            uiState.observeForever {
                assert(it is MoviesViewState.FetchingMoviesError)
            }
        }
    }

    @Test
    fun testingGetMoviesRemote_success() {
        val expected = mockMovieEntityList
        runBlockingTest {
            whenever(moviesRepo.getPopularMovies()).thenReturn(ResultType.Success(mockMovieResponse))
            whenever(moviesRepo.syncFavWithDb(mockMovieResponse.results)).thenReturn(mockMovieEntityList)
        }
        moviesViewModel.apply {
            getMovies()
            uiState.observeForever {
                val actual = (it as MoviesViewState.FetchingMoviesSuccess).movies
                assert(expected == actual)
            }
        }
    }


    @Test
    fun testingGetMoviesRemote_failure() {
        runBlockingTest {
            whenever(moviesRepo.getPopularMovies()).thenReturn(ResultType.Error(IOException()))
            moviesViewModel.apply {
                getMovies()
                uiState.observeForever {
                    assert(it is MoviesViewState.FetchingMoviesError)
                }
                verify(moviesRepo, times(1)).getPopularMovies()
            }
        }
    }

    @Test
    fun test_GetTrailers_success() {
        val expected = mockTrailerResponse
        runBlockingTest {
            whenever(moviesRepo.fetchMovieTrailers(any())).thenReturn(ResultType.Success(mockTrailerResponse))
            moviesViewModel.apply {
                fetchMovieTrailers(1)
                uiState.observeForever {
                    val actual = ((it as DetailViewState.TrailersFetchedSuccess).trailers)
                    assert(expected.results == actual)
                }
                verify(moviesRepo, times(1)).fetchMovieTrailers(any())
            }
        }
    }


    @Test
    fun test_GetTrailers_failure() {
        runBlockingTest {
            whenever(moviesRepo.fetchMovieTrailers(any())).thenReturn(ResultType.Error(IOException()))
            moviesViewModel.apply {
                fetchMovieTrailers(1)
                uiState.observeForever { assert(it is DetailViewState.TrailersFetchedError) }
                verify(moviesRepo, times(1)).fetchMovieTrailers(any())
            }
        }
    }

    @Test
    fun test_GetMovieLikeState() {
        val expected = true
        runBlockingTest {
            whenever(moviesRepo.isMovieLiked(any())).thenReturn(true)
            moviesViewModel.apply {
                getLikeState(1)
                uiState.observeForever {
                    val actual = ((it as DetailViewState.LikeState).isLiked)
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
            moviesViewModel.apply {
                updateLikeStatus(mockMovieEntity)
                uiState.observeForever {
                    val actual = ((it as DetailViewState.LikeState).isLiked)
                    assert(expected == actual)
                }
                verify(moviesRepo, times(1)).changeLikeState(1, false)
            }
        }
    }

}