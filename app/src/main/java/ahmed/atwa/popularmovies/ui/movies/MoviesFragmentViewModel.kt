package ahmed.atwa.popularmovies.ui.movies

import ahmed.atwa.popularmovies.data.remote.model.Movie
import ahmed.atwa.popularmovies.domain.GetMovies
import ahmed.atwa.popularmovies.ui.base.BaseViewModel
import ahmed.atwa.popularmovies.ui.base.BaseViewState
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
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