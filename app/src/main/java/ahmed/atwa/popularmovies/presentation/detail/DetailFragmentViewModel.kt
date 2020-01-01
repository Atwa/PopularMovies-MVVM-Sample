package ahmed.atwa.popularmovies.presentation.detail

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.data.remote.Movie
import ahmed.atwa.popularmovies.presentation.base.BaseViewModel
import ahmed.atwa.popularmovies.presentation.base.DetailViewState
import ahmed.atwa.popularmovies.presentation.base.BaseViewState
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class DetailFragmentViewModel @Inject constructor(
        val getTrailers: GetTrailers,
        val getLikeState: GetLikeState,
        val changeLikeState: ChangeLikeState) : BaseViewModel<BaseViewState>() {

    private lateinit var movie: Movie

    fun fetchMovieDetails(mMovie: Movie) {
        movie = mMovie
        viewModelScope.launch(Dispatchers.IO) {
            fetchLocalLikeState()
            fetchMovieTrailers()
        }
    }


    private suspend fun fetchLocalLikeState() {
        val likeState = getLikeState(movie.id)
        withContext(Dispatchers.Main) { mUiState.value = DetailViewState.likeState(likeState) }
    }

    private suspend fun fetchMovieTrailers() {
        val trailerList = getTrailers(movie.id)
        withContext(Dispatchers.Main) {
            if (!trailerList.isNullOrEmpty())
                mUiState.value = DetailViewState.trailersFetched(trailerList)
            else
                mUiState.value = DetailViewState.errorText("No trailers found")
        }
    }


    fun onLikeClick() {
        viewModelScope.launch(Dispatchers.IO) {
            changeLikeState(movie.id, isLiked.value!!.not())
            val resId = if (!isLiked.value!!) R.string.movie_liked else R.string.movie_unliked
            isLiked.postValue(isLiked.value!!.not())
            mUiState.postValue(BaseViewState.messageRes(resId))
        }
    }


}

