package ahmed.atwa.popularmovies.presentation.detail

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.data.remote.Movie
import ahmed.atwa.popularmovies.data.remote.Trailer
import ahmed.atwa.popularmovies.presentation.base.BaseFragment
import ahmed.atwa.popularmovies.presentation.base.BaseViewState
import ahmed.atwa.popularmovies.presentation.base.DetailViewState
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Suppress("UNCHECKED_CAST")
@SuppressLint("ValidFragment")
class DetailFragment(val movie: Movie) : BaseFragment<DetailFragmentViewModel>(), TrailerAdapter.TrailerAdapterListener {


    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mLinearLayoutManager: LinearLayoutManager


    @Inject
    lateinit var mTrailerAdapter: TrailerAdapter

    lateinit var mDetailFragmentViewModel: DetailFragmentViewModel
    lateinit var mListener: DetailFragmentListener

    override fun getLayoutId(): Int = R.layout.fragment_detail
    override fun getViewModel(): DetailFragmentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailFragmentViewModel::class.java)
    override fun getLifeCycleOwner(): LifecycleOwner = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTrailerAdapter.mListener = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getViewModel().uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is BaseViewState.messageRes -> showMessage(getString(it.resId))
                is BaseViewState.hasData<*> -> mTrailerAdapter.addItems(it.data as ArrayList<Trailer>)
            }
        })
    }

    private fun setUp() {
        trailersRecycler.setHasFixedSize(true)
        trailersRecycler.layoutManager = mLinearLayoutManager
        trailersRecycler.itemAnimator = DefaultItemAnimator()
        trailersRecycler.adapter = mTrailerAdapter
        setMovie(movie)
        observeViewState()
    }


    private fun setMovie(movie: Movie) {
        title_tv.text = movie.title
        plot_tv.text = movie.overview
        release_tv.text = "Released in : ${movie.release_date}"
        ratingbar.rating = (movie.vote_average/2).toFloat()
        count_tv.text = movie.vote_count.toString()
        Glide.with(getBaseActivity()).load(movie.poster_path).into(moviePoster)
        // Glide.with(getBaseActivity()).load("http://image.tmdb.org/t/p/w185$moviePoster").into(moviePoster)
        getViewModel().fetchMovieDetails(movie)
    }


    private fun observeViewState() {
        getViewModel().uiState.observe(this, Observer {
            hideLoading()
            when (it) {
                is DetailViewState.errorText ->
                    onError(it.text)
                is DetailViewState.likeState ->
                    like_img.setImageResource(it.imgSrc)
                is DetailViewState.trailersFetched<*> ->
                    mTrailerAdapter.addItems(it.data as ArrayList<Trailer>)

            }
        })
    }



    override fun onItemClick(trailer: Trailer) {
        mListener.onTrailerSelected(trailer)
    }

    interface DetailFragmentListener {
        fun onTrailerSelected(trailer: Trailer)
    }


}