package ahmed.atwa.popularmovies.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

abstract class BaseViewModel<T : BaseViewState>() : ViewModel() {

    protected open var mUiState = MutableLiveData<T>()
    open val uiState: LiveData<T> = mUiState

}