package ahmed.atwa.popularmovies.detail.presentation

import ahmed.atwa.popularmovies.movies.data.MovieRepo
import ahmed.atwa.popularmovies.movies.domain.MovieEntity
import ahmed.atwa.popularmovies.base.BaseViewModel
import ahmed.atwa.popularmovies.config.commons.CoroutineDispatcher
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class DetailViewModel @Inject constructor(private val movieRepo: MovieRepo,
                                          private val dispatcher: CoroutineDispatcher = CoroutineDispatcher()) : BaseViewModel() {

    fun getLikeState(movieId: Int) {
        viewModelScope.launch(dispatcher.IO) {
            val likeState = movieRepo.isMovieLiked(movieId)
            withContext(dispatcher.Main) { onSuccess(DetailViewState.LikeState(likeState)) }
        }
    }

    fun fetchMovieTrailers(movieId: Int) {
        viewModelScope.launch(dispatcher.IO) {
            val trailerList = movieRepo.fetchMovieTrailers(movieId)
            withContext(dispatcher.Main) {
                if (!trailerList.isNullOrEmpty())
                    onSuccess(DetailViewState.TrailersFetched(trailerList))
                else
                    onError("No trailers found")
            }
        }
    }

    fun updateLikeStatus(movie: MovieEntity) {
        viewModelScope.launch(dispatcher.IO) {
            val newLikeState = movie.isFav.not()
            movieRepo.changeLikeState(movie.id, newLikeState).also { movie.isFav = newLikeState }
            withContext(dispatcher.Main) {
                onSuccess(DetailViewState.LikeState(newLikeState))
            }
        }
    }


}

