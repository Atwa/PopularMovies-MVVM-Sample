

package ahmed.atwa.popularmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class ViewModelProviderFactory<V : Any>(private var viewModel: V) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel.javaClass)) {
            return  viewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}