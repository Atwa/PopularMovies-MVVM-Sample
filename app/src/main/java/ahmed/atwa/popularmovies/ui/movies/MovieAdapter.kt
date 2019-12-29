package ahmed.atwa.popularmovies.ui.movies

import ahmed.atwa.popularmovies.data.remote.model.Movie
import ahmed.atwa.popularmovies.databinding.ItemMovieViewBinding
import ahmed.atwa.popularmovies.ui.base.BaseViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MovieAdapter(var mMoviesList: MutableList<Movie>) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var mListener: MovieAdapterListener


    override fun getItemCount(): Int {
        return mMoviesList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val mMovieViewBinding = ItemMovieViewBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        return MovieViewHolder(mMovieViewBinding)
    }

    fun addItems(mList: List<Movie>) {
        mMoviesList.addAll(mList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mMoviesList.clear()
    }


    interface MovieAdapterListener {

        fun onItemClick(movie: Movie)
    }

    inner class MovieViewHolder(private val mBinding: ItemMovieViewBinding) : BaseViewHolder(mBinding.getRoot()), MovieItemViewModel.MovieItemViewModelListener {


        private lateinit var mMovieItemViewModel: MovieItemViewModel


        override fun onBind(position: Int) {
            val movie = mMoviesList[position]
            mMovieItemViewModel = MovieItemViewModel(movie, this)
            mBinding.viewModel = mMovieItemViewModel

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mBinding.executePendingBindings()
        }

        override fun onItemClick(movie: Movie) {
            mListener.onItemClick(movie)
        }
    }

}
