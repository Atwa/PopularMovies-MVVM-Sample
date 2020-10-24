package ahmed.atwa.popularmovies.presentation.detail

import ahmed.atwa.popularmovies.data.repository.MovieRepo
import ahmed.atwa.popularmovies.domain.MovieEntity
import ahmed.atwa.popularmovies.presentation.base.BaseViewModel
import ahmed.atwa.popularmovies.presentation.commons.CoroutineDispatcher
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

