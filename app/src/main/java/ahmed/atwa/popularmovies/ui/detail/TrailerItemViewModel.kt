package ahmed.atwa.popularmovies.ui.detail

import ahmed.atwa.popularmovies.data.model.Trailer
import androidx.lifecycle.MutableLiveData

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class TrailerItemViewModel (var trailer: Trailer, var mListener: TrailerItemViewModelListener)  {

    var name = MutableLiveData<String>()

    init {
        name.value = (trailer.name)
    }

    fun onPlayVideo(){
        mListener.onItemClick(trailer)
    }


    interface TrailerItemViewModelListener {
        fun onItemClick(trailer: Trailer)
    }
}