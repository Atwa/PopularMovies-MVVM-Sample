package ahmed.atwa.popularmovies.presentation.detail

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.data.entity.MovieEntity
import ahmed.atwa.popularmovies.domain.useCase.ChangeLikeState
import ahmed.atwa.popularmovies.domain.useCase.IsMovieLiked
import ahmed.atwa.popularmovies.domain.useCase.GetTrailers
import ahmed.atwa.popularmovies.presentation.base.BaseViewModel
import ahmed.atwa.popularmovies.presentation.base.DetailViewState
import ahmed.atwa.popularmovies.presentation.base.BaseViewState
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class DetailFragmentViewModel @Inject constructor(
        val getTrailers: GetTrailers,
        val getLikeState: IsMovieLiked,
        val changeLikeState: ChangeLikeState) : BaseViewModel<BaseViewState>() {

    private lateinit var mMovie: MovieEntity

    fun fetchMovieDetails(movie: MovieEntity) {
        mMovie = movie
        viewModelScope.launch(Dispatchers.IO) {
            fetchLocalLikeState()
            fetchMovieTrailers()
        }
    }


    private suspend fun fetchLocalLikeState() {
        val likeState = getLikeState(mMovie.id)
        withContext(Dispatchers.Main) { mUiState.value = DetailViewState.likeState(likeState) }
    }

    private suspend fun fetchMovieTrailers() {
        val trailerList = getTrailers(mMovie.id)
        withContext(Dispatchers.Main) {
            if (!trailerList.isNullOrEmpty())
                mUiState.value = DetailViewState.trailersFetched(trailerList)
            else
                mUiState.value = DetailViewState.errorText("No trailers found")
        }
    }


    fun onLikeClick() {
        viewModelScope.launch(Dispatchers.IO) {
            val newLikeState = mMovie.isFav.not()
            changeLikeState(mMovie.id, newLikeState).also { mMovie.isFav = newLikeState }
            val resId = if (newLikeState) R.string.movie_liked else R.string.movie_unliked
            withContext(Dispatchers.Main) {
                mUiState.value = DetailViewState.messageRes(resId)
                mUiState.value = DetailViewState.likeState(newLikeState)
            }
        }
    }


}

