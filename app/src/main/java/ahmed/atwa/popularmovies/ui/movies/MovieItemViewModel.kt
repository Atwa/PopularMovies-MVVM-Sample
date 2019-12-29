package ahmed.atwa.popularmovies.ui.movies

import ahmed.atwa.popularmovies.data.remote.model.Movie
import androidx.lifecycle.MutableLiveData

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MovieItemViewModel(var movie: Movie, var mListener: MovieItemViewModelListener) {

    var imageUrl = MutableLiveData<String>()

    init {
        imageUrl.value = movie.poster_path
    }

    fun onItemClick() {
        mListener.onItemClick(movie)
    }


    interface MovieItemViewModelListener {
        fun onItemClick(movie: Movie)
    }
}