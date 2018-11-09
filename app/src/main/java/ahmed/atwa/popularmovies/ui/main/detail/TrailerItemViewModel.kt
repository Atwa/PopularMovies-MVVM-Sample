

package ahmed.atwa.popularmovies.ui.main.detail

import ahmed.atwa.popularmovies.data.api.Trailer
import android.databinding.ObservableField

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class TrailerItemViewModel (var trailer: Trailer, var mListener: TrailerItemViewModelListener)  {

   var name: ObservableField<String> = ObservableField(trailer.name)

    fun onPlayVideo(){
        mListener.onItemClick(trailer)
    }


    interface TrailerItemViewModelListener {
        fun onItemClick(trailer: Trailer)
    }
}