package ahmed.atwa.popularmovies.presentation.base

import ahmed.atwa.popularmovies.presentation.commons.toSingleEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

abstract class BaseViewModel() : ViewModel() {

    private val mUiState = MutableLiveData<ViewState>()
    open val uiState: LiveData<ViewState> = mUiState.toSingleEvent()

    protected fun <T : Any> onSuccess(result: T) {
        mUiState.value = ViewState.HasData(result)
    }

    protected fun onError(error: String) {
        mUiState.value = ViewState.HasError(error)
    }


}