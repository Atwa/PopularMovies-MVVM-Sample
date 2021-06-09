package ahmed.atwa.popularmovies.main.presentation

import ahmed.atwa.popularmovies.base.BaseViewModel
import ahmed.atwa.popularmovies.detail.presentation.DetailViewState
import ahmed.atwa.popularmovies.movies.data.MovieRepo
import ahmed.atwa.popularmovies.movies.domain.MovieEntity
import ahmed.atwa.popularmovies.movies.presentation.MoviesViewState
import ahmed.atwa.popularmovies.utils.commons.CoroutineDispatcher
import ahmed.atwa.popularmovies.utils.network.NetworkResult
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MoviesViewModel @Inject constructor(private val movieRepo: MovieRepo,
                                          private val dispatcher: CoroutineDispatcher = CoroutineDispatcher()) : BaseViewModel() {

    val youtubeAppUri = "vnd.youtube:"
    val youtubeWebUri = "http://www.youtube.com/watch?v="
    val posterBaseUrl = "http://image.tmdb.org/t/p/w185"

    private var movie: MovieEntity? = null

    fun getMovies() {
        viewModelScope.launch(dispatcher.IO) {
            val localData = movieRepo.fetchMoviesLocal()
            if (!localData.isNullOrEmpty()) withContext(dispatcher.Main) { updateViewState(MoviesViewState.FetchingMoviesSuccess(localData)) }
            var syncedData:List<MovieEntity>? = null
            movieRepo.fetchMoviesRemote()?.also {
                if (it is NetworkResult.Success) syncedData = movieRepo.syncFavWithDb(it.data.results)
            }
            withContext(dispatcher.Main) {
                if (syncedData != null) updateViewState(MoviesViewState.FetchingMoviesSuccess(syncedData!!))
                else updateViewState(MoviesViewState.FetchingMoviesError)
            }
        }
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
                if (trailerList is NetworkResult.Success) updateViewState(DetailViewState.TrailersFetchedSuccess(trailerList.data.results))
                else updateViewState(DetailViewState.TrailersFetchedError)
            }
        }
    }

    fun updateLikeStatus(movie: MovieEntity) {
        viewModelScope.launch(dispatcher.IO) {
            val newLikeState = movie.isFav.not()
            movieRepo.changeLikeState(movie.id, newLikeState).also { movie.isFav = newLikeState }
            withContext(dispatcher.Main) {
                updateViewState(DetailViewState.LikeState(newLikeState))
            }
        }
    }

    fun setSelectedMovie(movie: MovieEntity) {
        this.movie = movie
    }

    fun getSelectedMovie(): MovieEntity? {
        return movie
    }

}