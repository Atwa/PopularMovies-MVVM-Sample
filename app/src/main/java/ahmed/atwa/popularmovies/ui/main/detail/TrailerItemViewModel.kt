package ahmed.atwa.popularmovies.ui.main.detail

import ahmed.atwa.popularmovies.data.api.Movie
import ahmed.atwa.popularmovies.data.api.Trailer
import ahmed.atwa.popularmovies.ui.base.BaseViewModel
import ahmed.atwa.popularmovies.ui.main.MainActivity
import android.databinding.ObservableField
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.content.ActivityNotFoundException
import android.content.Context
import android.net.Uri
import javax.inject.Inject


class TrailerItemViewModel (var trailer: Trailer, var mListener: TrailerItemViewModelListener)  {

   var name: ObservableField<String> = ObservableField(trailer.name)

    fun onPlayVideo(){
        mListener.onItemClick(trailer)
    }


    interface TrailerItemViewModelListener {
        fun onItemClick(trailer: Trailer)
    }
}