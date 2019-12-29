package ahmed.atwa.popularmovies.ui.movies

import ahmed.atwa.popularmovies.domain.GetMovies
import ahmed.atwa.popularmovies.ui.base.BaseViewModel
import ahmed.atwa.popularmovies.ui.base.UIState
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MoviesFragmentViewModel @Inject constructor(val getMovies: GetMovies) : BaseViewModel() {



    init {
        mUiState.value = UIState.loading
        viewModelScope.launch(Dispatchers.IO) {
            val movies = getMovies()
            if (movies.isNullOrEmpty())
                mUiState.postValue(UIState.errorText("No movies found"))
            else
                mUiState.postValue(UIState.hasData(movies))

        }
    }


}