package ahmed.atwa.popularmovies.base

import ahmed.atwa.popularmovies.utils.commons.toSingleEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

abstract class BaseViewModel() : ViewModel() {

    private val mUiState = MutableLiveData<Any>()
    open val uiState: LiveData<Any> = mUiState.toSingleEvent()

    fun <T : Any> updateViewState(result: T) {
        mUiState.value = result
    }

}