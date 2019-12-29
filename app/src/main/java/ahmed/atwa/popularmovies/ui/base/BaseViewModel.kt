package ahmed.atwa.popularmovies.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

abstract class BaseViewModel() : ViewModel() {

    protected var mUiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = mUiState

}