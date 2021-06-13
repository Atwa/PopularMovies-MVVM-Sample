package ahmed.atwa.popularmovies.main.presentation

import ahmed.atwa.popularmovies.base.BaseViewModel
import ahmed.atwa.popularmovies.detail.presentation.DetailViewState
import ahmed.atwa.popularmovies.movies.data.Movie
import ahmed.atwa.popularmovies.movies.data.MoviePaging
import ahmed.atwa.popularmovies.movies.data.MovieRepo
import ahmed.atwa.popularmovies.movies.data.MovieResponse
import ahmed.atwa.popularmovies.movies.presentation.MovieModel
import ahmed.atwa.popularmovies.movies.presentation.MoviesViewState
import ahmed.atwa.popularmovies.utils.commons.CoroutineDispatcher
import ahmed.atwa.popularmovies.utils.network.ResultType
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MoviesViewModel @Inject constructor(private val movieRepo: MovieRepo,
                                          private val dispatcher: CoroutineDispatcher = CoroutineDispatcher()) : BaseViewModel() {


    companion object {
        private const val PAGE_SIZE = 20
        const val YOUTUBE_APP_URI = "vnd.youtube:"
        const val YOUTUBE_WEB_URI = "http://www.youtube.com/watch?v="
        const val POSTER_BASE_URL = "http://image.tmdb.org/t/p/w185"
    }

    private var movie: Movie? = null

    val movies: Flow<PagingData<MovieModel>> = getMovieListStream()
            .map { pagingData -> pagingData.map { MovieModel.MovieItem(it) } }
            .map {
                it.insertSeparators { before, after ->
                    if (after == null) return@insertSeparators MovieModel.SeparatorItem("End of list")
                    if (before == null) return@insertSeparators null
                    else null
                }
            }

    private fun getMovieListStream(): Flow<PagingData<Movie>> {
        return Pager(PagingConfig(PAGE_SIZE)) { MoviePaging(movieRepo) }.flow
    }

    fun getLikeState(movieId: Int) {
        viewModelScope.launch(dispatcher.IO) {
            val likeState = movieRepo.isMovieLiked(movieId)
            withContext(dispatcher.Main) { updateViewState(DetailViewState.LikeState(likeState)) }
        }
    }

    fun fetchMovieTrailers(movieId: Int) {
        viewModelScope.launch(dispatcher.IO) {
            val trailerList = movieRepo.fetchMovieTrailers(movieId)
            withContext(dispatcher.Main) {
                if (trailerList is ResultType.Success) updateViewState(DetailViewState.TrailersFetchedSuccess(trailerList.data.results))
                else updateViewState(DetailViewState.TrailersFetchedError)
            }
        }
    }

    fun updateLikeStatus(movie: Movie) {
        viewModelScope.launch(dispatcher.IO) {
            val newLikeState = movieRepo.isMovieLiked(movie.id).not()
            movieRepo.changeLikeState(movie, newLikeState)
            withContext(dispatcher.Main) {
                updateViewState(DetailViewState.LikeState(newLikeState))
            }
        }
    }

    fun setSelectedMovie(movie: Movie) {
        this.movie = movie
    }

    fun getSelectedMovie(): Movie? {
        return movie
    }

}