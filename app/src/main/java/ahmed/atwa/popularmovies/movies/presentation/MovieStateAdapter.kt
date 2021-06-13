package ahmed.atwa.popularmovies.movies.presentation

import ahmed.atwa.popularmovies.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

class MovieStateAdapter(private val retry: () -> Unit) :
        LoadStateAdapter<MovieStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.apply {
            btnRetry.isVisible = loadState !is LoadState.Loading
            tvErrorMessage.isVisible = loadState !is LoadState.Loading
            loadProgress.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error){
                tvErrorMessage.text = loadState.error.localizedMessage
            }

            btnRetry.setOnClickListener {
                retry.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.load_state_layout, parent, false)
        return LoadStateViewHolder(view)
    }

    class LoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var btnRetry = itemView.findViewById(R.id.load_state_retry) as Button
        var loadProgress = itemView.findViewById(R.id.load_state_progress) as ProgressBar
        var tvErrorMessage = itemView.findViewById(R.id.load_state_errorMessage) as TextView

    }
}