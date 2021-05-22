package ahmed.atwa.popularmovies.movies.presentation

import ahmed.atwa.popularmovies.movies.data.MovieRepo
import ahmed.atwa.popularmovies.base.BaseViewModel
import ahmed.atwa.popularmovies.config.commons.CoroutineDispatcher
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MoviesViewModel @Inject constructor(private val movieRepo: MovieRepo,
                                          private val dispatcher: CoroutineDispatcher = CoroutineDispatcher()) : BaseViewModel() {


    fun getMovies() {
        viewModelScope.launch(dispatcher.IO) {
            movieRepo.getMovies().collect {
                withContext(dispatcher.Main) {
                    if (!it.isNullOrEmpty())
                        onSuccess(it)
                    else
                        onError("No movies found")
                }
            }
        }
    }


}