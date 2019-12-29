package ahmed.atwa.popularmovies.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

abstract class BaseViewModel() : ViewModel() {

    var uiState = MutableLiveData<UIState>()


    /*  var isLoading = MutableLiveData<Boolean>()

      init {
          isLoading.value = false
      }
  */

}