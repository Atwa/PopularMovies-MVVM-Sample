package ahmed.atwa.popularmovies.ui.detail

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.data.model.Movie
import ahmed.atwa.popularmovies.data.model.Trailer
import ahmed.atwa.popularmovies.data.repository.MovieRepository
import ahmed.atwa.popularmovies.ui.base.BaseViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class DetailFragmentViewModel(val movieRepository: MovieRepository) : BaseViewModel(movieRepository) {

    private lateinit var movie: Movie

    var trailerListLiveData: MutableLiveData<List<Trailer>> = MutableLiveData()

    var title = MutableLiveData<String>()

    var overview = MutableLiveData<String>()

    var releaseDate = MutableLiveData<String>()

    var imageUrl = MutableLiveData<String?>()

    var voteAverage = MutableLiveData<Double>()

    var voteCount = MutableLiveData<Int>()

    var isLiked = MutableLiveData<Boolean>()

    val toastMessage = MutableLiveData<Int>()


    fun setMovie(mMovie: Movie) {
        movie = mMovie
        title.value = movie.title
        overview.value = movie.overview
        releaseDate.value = movie.release_date
        voteAverage.value = movie.vote_average
        voteCount.value = movie.vote_count
        imageUrl.value = movie.poster_path
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            fetchLocalLikeState()
            fetchMovieTrailers()
            isLoading.postValue(false)
        }
    }

    private fun fetchLocalLikeState() = isLiked.postValue(movieRepository.isMovieLiked(movie.id))

    private suspend fun fetchMovieTrailers() = trailerListLiveData.postValue(movieRepository.fetchTrailersApiCall(movie.id))

    fun onLikeClick() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.changeMovieLikeState(movie.id, isLiked.value!!.not())
            val resId = if (!isLiked.value!!) R.string.movie_liked else R.string.movie_unliked
            isLiked.postValue(isLiked.value!!.not())
            toastMessage.postValue(resId)
        }
    }


}

