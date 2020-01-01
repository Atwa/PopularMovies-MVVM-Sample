package ahmed.atwa.popularmovies.presentation.movies

import ahmed.atwa.popularmovies.data.remote.Movie
import ahmed.atwa.popularmovies.presentation.base.BaseViewModel
import ahmed.atwa.popularmovies.presentation.base.BaseViewState
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MoviesFragmentViewModel @Inject constructor(val getMovies: GetMovies) : BaseViewModel<BaseViewState>() {



    init {
        mUiState.value = BaseViewState.loading(true)
        viewModelScope.launch(Dispatchers.IO) {
            getMovies.invoke().collect {
                withContext(Dispatchers.Main) { updateUI(it) }
            }
        }
    }

    private fun updateUI(it: List<Movie>?) {
        if (!it.isNullOrEmpty())
            mUiState.postValue(BaseViewState.hasData(it))
        else
            mUiState.postValue(BaseViewState.errorText("No movies found"))
    }


}